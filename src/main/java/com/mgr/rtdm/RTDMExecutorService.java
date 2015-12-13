package com.mgr.rtdm;

import com.mgr.common.queue.PassScenarioQueue;
import com.mgr.common.scenario.data.Scenario;
import com.mgr.common.worker.ChannelSimulatorExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Egzekutor watkow wysylajacych komunikaty do RTDM'a
 *
 * @author michal
 */
@Component
public class RTDMExecutorService extends ChannelSimulatorExecutorService {

    @Autowired
    RTDMWorkerFactory workerFactory;
    @Value("${mode.workers}")
    Integer workers;
    @Autowired
    protected PassScenarioQueue queue;

    /**
     * Ustawienie odpowiedniej fabryki oraz inicjacja watkow
     */
    @PostConstruct
    public void startUp() {
        super.workerFactory = workerFactory;
        super.init(workers);
    }

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
