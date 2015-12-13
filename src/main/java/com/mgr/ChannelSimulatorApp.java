package com.mgr;

import com.mgr.common.scenario.ScenarioDao;
import com.mgr.common.scenario.worker.ScenarioExecutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Glowna klasa aplikacji ChannelSimulator. Aplikacja sluzy do symulacji ruchu z
 * kanalow. Wiadomosci moze wysylac do silnika Esper lub RTDM. Dla silnika Esper
 * wiadomosci maja format JSON. Dla silnika RTDM wiadomosci maja format XML.
 *
 * @author michal
 */
public class ChannelSimulatorApp {

    private static final Logger LOG = LoggerFactory
            .getLogger(ChannelSimulatorApp.class);

    /**
     * Metoda main. Program startuje z dwoma parametrami. Jest to tryb pracy.
     * Mozliwe wartosci to rtdm i esper. Drugim jest grupa scenariuszy do
     * wykonania. Na poczatku wstaje kontener Springa, nastepnie rozpoczynane
     * jest skanowanie pakietow w poszukiwaniu konfiguracji i komponentow. Na
     * koncu uruchamiana jest metoda start.
     *
     * @param args
     */
    public static void main(String[] args) {
        String mode = args.length == 0 ? "rtdm" : args[0];
        String scenario = args.length == 1 ? "TEST" : args[1];

        Set<String> allowedModes = new HashSet<>(Arrays.asList("rtdm", "esper"));

        if (!allowedModes.contains(mode)) {
            LOG.error("Nieobslugiwany tryb pracy: {}", mode);
            System.exit(1);
        }
        String packages = "com.mgr.config,com.mgr." + mode;

        try (AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
                packages.split(","))) {
            start(applicationContext, scenario);
            LOG.info("Channel Simulator Started");
            applicationContext.registerShutdownHook();
        } catch (Exception e) {
            LOG.info("Error: ", e);
            System.exit(1);
        }
    }

    /**
     * Metoda wybiera scenariusze ze wskazanej grupy z bazy oraz uruchamia
     * egzekutora.
     *
     * @param applicationContext
     * @param scenario
     */
    public static void start(
            AnnotationConfigApplicationContext applicationContext,
            String scenario) {
        ScenarioExecutorService starter = applicationContext
                .getBean(ScenarioExecutorService.class);
        ScenarioDao scenarioDao = applicationContext.getBean(ScenarioDao.class);
        starter.start(scenarioDao.selectGroup(scenario));
    }

}
