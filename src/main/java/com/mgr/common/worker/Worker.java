package com.mgr.common.worker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.mgr.common.worker.WorkerStatus.*;

/**
 * Abstrakcyjna klasa workera. Implementuje metode run poprzez uruchamianie
 * abstrakcyjnej metody doRun w petli
 *
 * @author michal
 */
public abstract class Worker implements Runnable {

    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    private volatile WorkerStatus status = NEW;

    @Override
    public void run() {
        while (status == STARTED) {
            try {
                doWork();
            } catch (Exception e) {
                LOG.error("Unhandled exception in worker thread.", e);
            }
        }
        status = STOPPED;
    }

    public abstract void doWork();

    public synchronized void shutdown() {
        LOG.info("Shutting down the worker.");
        status = STOPPING;
    }

    public synchronized void start() {
        LOG.info("Starting the worker.");
        status = STARTED;
    }

    @Override
    public String toString() {
        return "Worker thread " + Thread.currentThread().getName() + " status="
                + status.toString();
    }

    public String getName() {
        return Thread.currentThread().getName();
    }

    public WorkerStatus getWorkerStatus() {
        return status;
    }
}
