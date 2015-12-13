package com.mgr.common.scenario.worker;

import com.mgr.common.scenario.data.Scenario;
import com.mgr.common.worker.Worker;
import com.mgr.common.worker.WorkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Fabryka produkujaca watki zczytujace scenariusze
 *
 * @author michal
 */
@Component
public class ScenarioWorkerFactory implements WorkerFactory {

    @Autowired
    ApplicationContext appContext;
    protected List<Scenario> data;

    /**
     * Metoda zwracajaca nowa instancje workera. Do danego workera przypisywane
     * sa scenariusze ktore pa wrzucic na kolejke.
     */
    @Override
    public Worker newWorker() {
        Worker worker = appContext.getBean(ScenarioWorker.class);
        ((ScenarioWorker) worker).setData(data);
        return worker;
    }

    @Override
    public void close() {

    }

    @Override
    public void setData(List<Scenario> data) {
        this.data = data;
        Collections.sort(this.data, new SeqComparator());
    }

}

/**
 * Pomocnicza klasa wykorzystywana przy sortowaniu scenariuszy. Tak by miec
 * pewnosc ze wykonaja sie w odpowiedniej kolejnosci.
 *
 * @author michal
 */
class SeqComparator implements Comparator<Scenario> {

    @Override
    public int compare(Scenario o1, Scenario o2) {
        return o1.getId().getScenarioSeqNum()
                .compareTo(o2.getId().getScenarioSeqNum());
    }

}