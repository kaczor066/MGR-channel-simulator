package com.mgr.rtdm;

import com.mgr.common.scenario.data.Scenario;
import com.mgr.common.worker.Worker;
import com.mgr.common.worker.WorkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Fabryka watkow wysylajacych wiadomosci do RTDM'a
 *
 * @author michal
 */
@Component
public class RTDMWorkerFactory implements WorkerFactory {

    @Autowired
    ApplicationContext appContext;

    /**
     * Metoda tworzaca nowy watek
     */
    @Override
    public Worker newWorker() {
        Worker worker = appContext.getBean(RTDMWorker.class);
        return worker;
    }

    @Override
    public void close() {

    }

    @Override
    public void setData(List<Scenario> data) {
        // TODO Auto-generated method stub

    }

}
