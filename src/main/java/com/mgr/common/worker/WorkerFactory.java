package com.mgr.common.worker;

import com.mgr.common.scenario.data.Scenario;

import java.util.List;

/**
 * Interfejs ktory powinna implementowac fabryka nowych workerow
 *
 * @author michal
 */
public interface WorkerFactory {

    Worker newWorker();

    void close();

    void setData(List<Scenario> data);

}
