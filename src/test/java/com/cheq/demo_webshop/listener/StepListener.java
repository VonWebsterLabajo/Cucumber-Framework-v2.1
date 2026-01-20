package com.cheq.demo_webshop.listener;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestStepStarted;
import io.cucumber.plugin.event.PickleStepTestStep;

/**
* Cucumber event listener to track the current step being executed.
*/
public class StepListener implements ConcurrentEventListener {
    public static String currentStep;

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestStepStarted.class, this::onStepStarted);
    }

    private void onStepStarted(TestStepStarted event) {
        // Check if the test step is a Cucumber step
        if (event.getTestStep() instanceof PickleStepTestStep) {
            PickleStepTestStep step = (PickleStepTestStep) event.getTestStep();
            // Set currentStep to the text of the step
            currentStep = step.getStep().getText();
        }
    }


}
 