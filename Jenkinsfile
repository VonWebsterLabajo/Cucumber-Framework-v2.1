pipeline {
 agent any

 tools {
  jdk 'jdk21'
  maven 'maven'
 }

 triggers {
  githubPush()
 }

 parameters {
  string(name: 'DATAPROVIDER_THREAD_COUNT', defaultValue: '1')
  booleanParam(name: 'HEADLESS', defaultValue: true)
 }

 environment {
  APP_REPO = 'https://github.com/VonWebsterLabajo/calculator-demo.git'
  APP_DIR = 'app'
  TEST_DIR = 'tests'
  ALLURE_VERSION = '2.29.0'
 }

 stages {

  stage('Checkout Repositories') {
   steps {
    echo 'Cloning app and test repositories'
    dir("${APP_DIR}") {
     git branch: 'main', url: "${APP_REPO}"
    }
    dir("${TEST_DIR}") {
     checkout scm
    }
   }
  }

  stage('Install Allure CLI') {
   steps {
    sh '''
     if [ ! -d "$WORKSPACE/allure" ]; then
      wget https://github.com/allure-framework/allure2/releases/download/${ALLURE_VERSION}/allure-${ALLURE_VERSION}.zip
      unzip -o allure-${ALLURE_VERSION}.zip
      mv allure-${ALLURE_VERSION} $WORKSPACE/allure
     fi
    '''
   }
  }

  stage('Run Maven Tests') {
   steps {
    dir("${TEST_DIR}") {
     catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
      sh '''
       mvn clean test \
       -Dheadless=${HEADLESS} \
       -Ddataproviderthreadcount=${DATAPROVIDER_THREAD_COUNT}
      '''
     }
    }
   }
  }

  stage('Generate Allure Report') {
   steps {
    dir("${TEST_DIR}") {
     sh '''
      if [ -d "target/allure-results" ]; then
       $WORKSPACE/allure/bin/allure generate target/allure-results \
       --clean \
       --single-file \
       -o target/allure-report
      else
       echo "No allure-results found"
      fi
     '''
    }
   }
  }

  stage('Archive Results') {
   steps {
    dir("${TEST_DIR}") {
     archiveArtifacts artifacts: 'target/allure-report/**', fingerprint: true
     junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
    }
   }
  }
  
  stage('Deploy to GitHub Pages') {
	 when {
	  expression { currentBuild.currentResult == 'SUCCESS' }
	 }
	 steps {
	  timeout(time: 15, unit: 'MINUTES') {
	   input message: 'Deploy to GitHub Pages?', ok: 'Deploy'
	  }
	
	  dir("${APP_DIR}") {
	   withCredentials([usernamePassword(credentialsId: 'GITHUB_PAT', usernameVariable: 'GIT_USER', passwordVariable: 'GIT_PASS')]) {
	    sh '''
	     git config user.email "jenkins@local"
	     git config user.name "Jenkins CI"
	
	     mkdir -p /tmp/deploy-src
	     rsync -av --exclude='.git' --exclude='.github' ./ /tmp/deploy-src/
	
	     git fetch origin
	     git clean -fdx
	
	     if git ls-remote --exit-code --heads origin gh-pages; then
	      git checkout gh-pages || git checkout -b gh-pages origin/gh-pages
	     else
	      git checkout --orphan gh-pages
	     fi
	
	     git rm -rf . || true
	     cp -r /tmp/deploy-src/* .
	
	     git add .
	     git commit -m "CD: Deploy build ${BUILD_NUMBER}" || true
	     git push -f https://${GIT_USER}:${GIT_PASS}@github.com/VonWebsterLabajo/calculator-demo.git gh-pages
	
	     echo "Deployed to GitHub Pages"
	    '''
	   }
	  }
	 }
	}
 }
 
 post {
  always {
   echo 'Pipeline finished'
  }
 }
}
