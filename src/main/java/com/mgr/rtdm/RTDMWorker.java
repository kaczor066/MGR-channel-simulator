package com.mgr.rtdm;

import com.mgr.common.metrics.ScenarioExecutionMetric;
import com.mgr.common.queue.PassScenarioQueue;
import com.mgr.common.scenario.data.Scenario;
import com.mgr.common.worker.Worker;
import com.mgr.rtdm.domain.EventResponseType;
import com.mgr.rtdm.domain.EventType;
import org.perf4j.StopWatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Klasa watku wysylajacego odebrane scenariusze do RTDMa
 *
 * @author michal
 */
@Component
@Scope("prototype")
public class RTDMWorker extends Worker {

    @Autowired
    protected PassScenarioQueue queue;
    @Autowired
    protected RTDMWebServiceClient wsClient;
    @Autowired
    ScenarioExecutionMetric scenarioHistogram;
    private ScenarioToRTDMEventMapper mapper = new ScenarioToRTDMEventMapper();

    /**
     * Metoda wykonywana w petli. Odebrany scenariusz jest mapowany na postac
     * XML zgodna z WSDL RTDM'a Nastepnie taka wiadomosc jest wysylana.
     */
    @Override
    public void doWork() {
        try {
            LOG.info("Trying to execute WS");
            StopWatch timer = new StopWatch();
            Scenario scenario = queue.take();
            if (scenario.getEventName() == null) {
                shutdown();
                return;
            }
            EventType event = mapper.map(scenario);
            scenarioHistogram.updateMappingHistogram(timer.getElapsedTime());
            timer = new StopWatch();
            EventResponseType response = wsClient
                    .postMessageAndWaitForResponse(event);
            scenarioHistogram.updateScenariosHistogram(timer.getElapsedTime());
            LOG.info("Executed WS");
        } catch (InterruptedException e) {
            LOG.error("Exception:", e);
        }

    }

}
