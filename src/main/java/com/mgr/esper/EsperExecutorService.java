package com.mgr.esper;

import com.mgr.common.queue.PassScenarioQueue;
import com.mgr.common.scenario.data.Scenario;
import com.mgr.common.worker.ChannelSimulatorExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Klasa egzekutora waztkow wysylajacych komunikaty do aplikacji opakowujacej
 * silnik Esper
 *
 * @author michal
 */
@Component
public class EsperExecutorService extends ChannelSimulatorExecutorService {

    @Autowired
    EsperWorkerFactory workerFactory;
    @Value("${mode.workers}")
    Integer workers;
    @Autowired
    protected PassScenarioQueue queue;

    /**
     * Ustawienie fabryki watkow i uruchomienie metody inicjalizujacej
     */
    @PostConstruct
    public void startUp() {
        super.workerFactory = workerFactory;
        super.init(workers);
    }

    /**
     * Wyslanie "zatrutych" wiadomosci w celu zakonczenia pracy watkow
     */
    @Override
    protected void sendPoisonousRequests() {
        for (int i = 0; i < workers; i++) {
            try {
                queue.put(new Scenario(null, null));
            } catch (InterruptedException e) {
                i--;
            }
        }

    }

}
