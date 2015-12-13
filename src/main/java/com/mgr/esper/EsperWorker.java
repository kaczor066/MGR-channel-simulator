package com.mgr.esper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mgr.common.metrics.ScenarioExecutionMetric;
import com.mgr.common.queue.PassScenarioQueue;
import com.mgr.common.scenario.data.Scenario;
import com.mgr.common.worker.Worker;
import org.perf4j.StopWatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Klasa watku, ktory bedzie odbieral scenariusze z kolejki, tlumaczyl je na
 * format JSON oraz wysylal do aplikacji opakowujacej silnik Esper
 *
 * @author michal
 */
@Component
@Scope("prototype")
public class EsperWorker extends Worker {

    @Autowired
    protected PassScenarioQueue queue;
    @Autowired
    ApplicationContext context;
    protected EsperWebServiceClient wsClient;
    @Autowired
    ScenarioExecutionMetric scenarioHistogram;
    @Autowired
    ObjectMapper objectMapper;
    private ScenarioToEsperEventMapper mapper;

    /**
     * Metoda wykonywana w petli. Watek pobiera kolejny scenariusz z kolejki.
     * Mapuje go na EsperMessageWrapper. Wysyla do aplikacji opakowujacej silnik
     * Esper.
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
            EsperMessageWrapper event = mapper.map(scenario);
            scenarioHistogram.updateMappingHistogram(timer.getElapsedTime());
            timer = new StopWatch();
            wsClient.postMessage(event);
            scenarioHistogram.updateScenariosHistogram(timer.getElapsedTime());
            LOG.info("Executed WS");
        } catch (InterruptedException e) {
            LOG.error("Exception:", e);
        }

    }

    @PostConstruct
    public void init() {
        mapper = new ScenarioToEsperEventMapper(objectMapper);
        wsClient = context.getBean(EsperWebServiceClient.class);
    }

}
