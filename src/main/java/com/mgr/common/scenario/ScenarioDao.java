package com.mgr.common.scenario;

import com.mgr.common.scenario.data.Scenario;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Klasa zawierajacy metody dostepu do tabeli scenariuszy
 *
 * @author michal
 */
@Repository
public class ScenarioDao {

    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Zapisanie scenariusza w bazie
     *
     * @param scenario
     */
    @Transactional
    public void insert(Scenario scenario) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(scenario);
        session.getTransaction().commit();
        LOG.debug("Scenario saved. Scenario:[{}]", scenario);
    }

    /**
     * Wybranie wszystkich scenariuszy
     *
     * @return
     */
    @Transactional
    public List<Scenario> selectAll() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Scenario.class);
        List<Scenario> scenarios = (List<Scenario>) criteria.list();
        session.getTransaction().commit();
        logSelectedScenarios(scenarios);
        return scenarios;
    }

    /**
     * Wybranie konkretnej grupy scenariuszy
     *
     * @param scenarioGroup
     * @return
     */
    @Transactional
    public List<Scenario> selectGroup(String scenarioGroup) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Scenario.class);
        criteria.add(Restrictions.eq("id.scenarioGroup", scenarioGroup));
        List<Scenario> scenarios = (List<Scenario>) criteria.list();
        session.getTransaction().commit();
        logSelectedScenarios(scenarios);
        return scenarios;
    }

    private void logSelectedScenarios(List<Scenario> scenarios) {
        for (Scenario scenario : scenarios)
            LOG.debug("Selected scenario:[{}]", scenario);
    }

}
