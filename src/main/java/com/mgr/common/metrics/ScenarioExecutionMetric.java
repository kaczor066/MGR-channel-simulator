package com.mgr.common.metrics;

import com.codahale.metrics.Histogram;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Slf4jReporter;
import com.codahale.metrics.UniformReservoir;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

import static com.codahale.metrics.MetricRegistry.name;

/**
 * Klasa odpowiadajaca za zliczanie metryk, histogramow itp.
 *
 * @author michal
 */
@Component
public class ScenarioExecutionMetric {

    Logger LOG = LoggerFactory.getLogger(getClass());
    @Autowired
    private MetricRegistry metricRegistry;

    private Slf4jReporter reporter;
    private Histogram scenariosHistogram;

    private Histogram mappingHistogram;

    /**
     * Metoda inicjalizujaca
     */
    @PostConstruct
    public void setUp() {
        scenariosHistogram = new Histogram(new UniformReservoir());
        mappingHistogram = new Histogram(new UniformReservoir());
        metricRegistry.register(
                name(ScenarioExecutionMetric.class,
                        "ScenariosExecutionsHistogram"), scenariosHistogram);
        metricRegistry.register(
                name(ScenarioExecutionMetric.class,
                        "MappingExecutionsHistogram"), mappingHistogram);
        reporter = Slf4jReporter.forRegistry(metricRegistry).outputTo(LOG)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS).build();
        reporter.start(Integer.MAX_VALUE, TimeUnit.MINUTES);

    }

    /**
     * Dodanie kolejnego wyniku do histograma uruchomien scenariuszy
     *
     * @param value
     */
    public void updateScenariosHistogram(long value) {
        scenariosHistogram.update(value);
        reporter.report();
    }

    /**
     * Dodanie kolejnego wyniku do histograma czasow mapowan
     *
     * @param value
     */
    public void updateMappingHistogram(long value) {
        mappingHistogram.update(value);
        reporter.report();
    }

    @Override
    public String toString() {
        return scenariosHistogram.toString();
    }

    @PreDestroy
    public void close() {
        reporter.report();
        reporter.stop();
    }
}
