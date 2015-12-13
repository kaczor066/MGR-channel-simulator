package com.mgr.common.scenario.worker;

import com.mgr.common.scenario.data.Scenario;
import com.mgr.common.worker.ChannelSimulatorExecutorService;
import com.mgr.common.worker.Worker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.google.common.collect.Sets.newLinkedHashSet;
import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * Egzakutor watkow odpowiedajacych za odebranie scenariusza z bazy i wrzucenia
 * go na kolejke
 *
 * @author michal
 */
@Component
public class ScenarioExecutorService extends ChannelSimulatorExecutorService {

    private final static Logger LOG = LoggerFactory
            .getLogger(ScenarioExecutorService.class);

    @Autowired
    ScenarioWorkerFactory workerFactory;
    @Value("${scenario.workers}")
    Integer workers;

    /**
     * Metoda inicjalizujaca. Przypisanie fabryki workerow do egzekutora.
     */
    @PostConstruct
    public void startUp() {
        super.workerFactory = workerFactory;
    }

    @Override
    protected void sendPoisonousRequests() {

    }

    /**
     * Uruchomienie egzekutora. Inicjalizacja watkow i przypisanie ich do puli.
     *
     * @param scenarios
     */
    public void start(List<Scenario> scenarios) {
        workersPool = newLinkedHashSet();
        workersExecutorService = newFixedThreadPool(1);
        workerFactory.setData(scenarios);
        workersPool.add(workerFactory.newWorker());

        for (Worker worker : workersPool) {
            worker.start();
            workersExecutorService.submit(worker);
            LOG.info("Worker created and started.");
        }
        workersExecutorService.shutdown();
        try {
            workersExecutorService.awaitTermination(Integer.MAX_VALUE,
                    TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            LOG.debug("Error while finishing...", e);
        }
        LOG.debug("ScenarioExecutor shut down.");
    }

}
