package com.mgr.esper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mgr.common.scenario.data.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

import static com.google.common.base.Throwables.propagate;

/**
 * Klasa odpowiadajaca za zmapowanie scenariusza odebranego z kolejki na
 * EsperMessageWrapper, z payload w formacie JSON
 *
 * @author michal
 */
public class ScenarioToEsperEventMapper {

    private final JsonNodeFactory nodeFactory;
    private final ObjectMapper objectMapper;
    private static final Logger LOG = LoggerFactory
            .getLogger(ScenarioToEsperEventMapper.class);

    public ScenarioToEsperEventMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.nodeFactory = objectMapper.getNodeFactory();
    }

    ;

    public EsperMessageWrapper map(Scenario scenario) {

        EsperMessageWrapper event = new EsperMessageWrapper();
        event.setPayload(generatePayload(scenario));
        event.setEventName(scenario.getEventName());
        return event;
    }

    private java.lang.String generatePayload(Scenario scenario) {
        ObjectNode rootNode = nodeFactory.objectNode();
        ObjectNode eventNode = rootNode.putObject("event");
        putAll(eventNode, scenario);
        try {
            return objectMapper.writeValueAsString(rootNode);
        } catch (JsonProcessingException e) {
            throw propagate(e);
        }

    }

    private void putAll(ObjectNode eventNode, Scenario scenario) {
        generateStrings(eventNode, scenario);
        generateIntegers(eventNode, scenario);
        generateFloats(eventNode, scenario);
        generateDates(eventNode, scenario);
    }

    private void generateDates(ObjectNode eventNode, Scenario scenario) {

        addData(eventNode,
                createDateParam(scenario.getDateName1(), scenario.getDateVal1()));
        addData(eventNode,
                createDateParam(scenario.getDateName2(), scenario.getDateVal2()));
        addData(eventNode,
                createDateParam(scenario.getDateName3(), scenario.getDateVal3()));
        addData(eventNode,
                createDateParam(scenario.getDateName4(), scenario.getDateVal4()));
        addData(eventNode,
                createDateParam(scenario.getDateName5(), scenario.getDateVal5()));
    }

    private void generateFloats(ObjectNode eventNode, Scenario scenario) {
        addData(eventNode,
                createFloatParam(scenario.getFloatName1(),
                        scenario.getFloatVal1()));
        addData(eventNode,
                createFloatParam(scenario.getFloatName2(),
                        scenario.getFloatVal2()));
        addData(eventNode,
                createFloatParam(scenario.getFloatName3(),
                        scenario.getFloatVal3()));
        addData(eventNode,
                createFloatParam(scenario.getFloatName4(),
                        scenario.getFloatVal4()));
        addData(eventNode,
                createFloatParam(scenario.getFloatName5(),
                        scenario.getFloatVal5()));

    }

    private void generateIntegers(ObjectNode eventNode, Scenario scenario) {
        addData(eventNode,
                createIntegerParam(scenario.getIntName1(),
                        scenario.getIntVal1()));
        addData(eventNode,
                createIntegerParam(scenario.getIntName2(),
                        scenario.getIntVal2()));
        addData(eventNode,
                createIntegerParam(scenario.getIntName3(),
                        scenario.getIntVal3()));
        addData(eventNode,
                createIntegerParam(scenario.getIntName4(),
                        scenario.getIntVal4()));
        addData(eventNode,
                createIntegerParam(scenario.getIntName5(),
                        scenario.getIntVal5()));

    }

    private void generateStrings(ObjectNode eventNode, Scenario scenario) {
        addData(eventNode,
                createStringParam(scenario.getCharName1(),
                        scenario.getCharVal1()));
        addData(eventNode,
                createStringParam(scenario.getCharName2(),
                        scenario.getCharVal2()));
        addData(eventNode,
                createStringParam(scenario.getCharName3(),
                        scenario.getCharVal3()));
        addData(eventNode,
                createStringParam(scenario.getCharName4(),
                        scenario.getCharVal4()));
        addData(eventNode,
                createStringParam(scenario.getCharName5(),
                        scenario.getCharVal5()));

    }

    private void addData(ObjectNode eventNode,
                         EsperMessageWrapper createStringParam) {
        if (createStringParam != null)
            eventNode.put(createStringParam.eventName,
                    createStringParam.getPayload());

    }

    private EsperMessageWrapper createStringParam(java.lang.String name,
                                                  java.lang.String val) {
        EsperMessageWrapper data = null;
        if (!(name == null || name.equals(""))) {
            data = new EsperMessageWrapper();
            data.setEventName(name);
            data.setPayload(val);
        }
        return data;
    }

    private EsperMessageWrapper createIntegerParam(java.lang.String name,
                                                   BigDecimal val) {
        EsperMessageWrapper data = null;
        if (!(name == null || name.equals(""))) {
            data = new EsperMessageWrapper();
            data.setEventName(name);
            data.setPayload(java.lang.String.valueOf(val.longValue()));
        }
        return data;
    }

    private EsperMessageWrapper createFloatParam(java.lang.String name,
                                                 BigDecimal val) {
        EsperMessageWrapper data = null;
        if (!(name == null || name.equals(""))) {
            data = new EsperMessageWrapper();
            data.setEventName(name);
            data.setPayload(java.lang.String.valueOf(val.doubleValue()));
        }
        return data;
    }

    private EsperMessageWrapper createDateParam(java.lang.String name,
                                                java.sql.Timestamp val) {
        EsperMessageWrapper data = null;
        if (!(name == null || name.equals(""))) {
            data = new EsperMessageWrapper();
            data.setEventName(name);
            data.setPayload(val.toString());
        }
        return data;
    }
}