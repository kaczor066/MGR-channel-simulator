package com.mgr.common.queue;

/**
 * Klasa wewnetrznej kolejki programu. Kolejka sluzy do przekazywania scenariuszy pomiedzy watkami pobierajacymi je z bazy, a wysylajacymi do silnika
 */

import com.mgr.common.scenario.data.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class PassScenarioQueue {

    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    protected BlockingQueue<Scenario> queue = new LinkedBlockingQueue<>();

    /**
     * Metoda pobierania scenariusza z kolejki
     *
     * @return
     * @throws InterruptedException
     */
    public Scenario take() throws InterruptedException {
        Scenario sd = queue.take();
        LOG.debug("Scenario taken from Queue. Scenario:[{}]", sd);
        return sd;
    }

    /**
     * Metoda wrzucania scenariusza na kolejke
     *
     * @param sd
     * @throws InterruptedException
     */
    public void put(Scenario sd) throws InterruptedException {
        LOG.debug("Scenario inserted to Queue. Scenario:[{}]", sd);
        queue.put(sd);
    }

    /**
     * Stworzenie nowej kolejki
     */
    public void refresh() {
        LOG.info("Refreshing. {} requests will not be processed.", queue.size());
        queue = new LinkedBlockingQueue<>();
    }

}
