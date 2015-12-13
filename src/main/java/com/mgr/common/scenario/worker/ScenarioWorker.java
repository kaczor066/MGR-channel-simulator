package com.mgr.common.scenario.worker;

import com.mgr.common.queue.PassScenarioQueue;
import com.mgr.common.scenario.data.Scenario;
import com.mgr.common.worker.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
public class ScenarioWorker extends Worker {

    @Autowired
    protected PassScenarioQueue queue;
    protected List<Scenario> data;

    public void setData(List<Scenario> data) {
        this.data = data;
    }

    /**
     * Watek wrzuca scenariusze na kolejke i konczy dzialanie
     */
    @Override
    public void doWork() {
        printData();
        try {
            publishScenario();
        } catch (InterruptedException e) {
            LOG.error("Interrupted when publishing scenarios...", e);
        }
        super.shutdown();

    }

    public void printData() {
        for (Scenario scenario : data) {
            LOG.debug("Scenario:[{}]", scenario);
        }
    }

    private void publishScenario() throws InterruptedException {
        for (Scenario scenario : data)
            queue.put(scenario);
    }
}
