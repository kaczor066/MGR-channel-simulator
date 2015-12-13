package com.mgr.rtdm;

import com.mgr.common.scenario.data.Scenario;
import com.mgr.rtdm.domain.*;
import com.mgr.rtdm.domain.Float;
import com.mgr.rtdm.domain.String;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.UUID;

/**
 * Klasa dostarczajaca metody mapowania odebranego z kolejki scenariusza na
 * wiadomosc obiekt EventType EventType to klasa wygenerowana automatycznie na
 * podstawie WSDLa wystawianego przez RTDMa Za pomoca JAXb EventType jest
 * zamieniany na XML
 *
 * @author michal
 */
public class ScenarioToRTDMEventMapper {

    private static final Logger LOG = LoggerFactory
            .getLogger(ScenarioToRTDMEventMapper.class);

    public ScenarioToRTDMEventMapper() {
    }

    ;

    public EventType map(Scenario scenario) {

        EventType event = new EventType();
        event.setBody(generateBody(scenario));
        event.setHeader(generateHeader(scenario));
        event.setName(scenario.getEventName());
        return event;
    }

    private HeaderType generateHeader(Scenario scenario) {
        HeaderType header = new HeaderType();
        header.setClientTimeZoneID("Europe\\Warsaw");
        header.setIdentity(UUID.randomUUID().toString());
        return header;
    }

    private BodyType generateBody(Scenario scenario) {

        BodyType body = new BodyType();
        generateStrings(body, scenario);
        generateIntegers(body, scenario);
        generateFloats(body, scenario);
        generateDates(body, scenario);
        return body;
    }

    private void generateDates(BodyType body, Scenario scenario) {

        body.addData(createDateParam(scenario.getDateName1(),
                scenario.getDateVal1()));
        body.addData(createDateParam(scenario.getDateName2(),
                scenario.getDateVal2()));
        body.addData(createDateParam(scenario.getDateName3(),
                scenario.getDateVal3()));
        body.addData(createDateParam(scenario.getDateName4(),
                scenario.getDateVal4()));
        body.addData(createDateParam(scenario.getDateName5(),
                scenario.getDateVal5()));
    }

    private void generateFloats(BodyType body, Scenario scenario) {
        body.addData(createFloatParam(scenario.getFloatName1(),
                scenario.getFloatVal1()));
        body.addData(createFloatParam(scenario.getFloatName2(),
                scenario.getFloatVal2()));
        body.addData(createFloatParam(scenario.getFloatName3(),
                scenario.getFloatVal3()));
        body.addData(createFloatParam(scenario.getFloatName4(),
                scenario.getFloatVal4()));
        body.addData(createFloatParam(scenario.getFloatName5(),
                scenario.getFloatVal5()));

    }

    private void generateIntegers(BodyType body, Scenario scenario) {
        body.addData(createIntegerParam(scenario.getIntName1(),
                scenario.getIntVal1()));
        body.addData(createIntegerParam(scenario.getIntName2(),
                scenario.getIntVal2()));
        body.addData(createIntegerParam(scenario.getIntName3(),
                scenario.getIntVal3()));
        body.addData(createIntegerParam(scenario.getIntName4(),
                scenario.getIntVal4()));
        body.addData(createIntegerParam(scenario.getIntName5(),
                scenario.getIntVal5()));

    }

    private void generateStrings(BodyType body, Scenario scenario) {
        body.addData(createStringParam(scenario.getCharName1(),
                scenario.getCharVal1()));
        body.addData(createStringParam(scenario.getCharName2(),
                scenario.getCharVal2()));
        body.addData(createStringParam(scenario.getCharName3(),
                scenario.getCharVal3()));
        body.addData(createStringParam(scenario.getCharName4(),
                scenario.getCharVal4()));
        body.addData(createStringParam(scenario.getCharName5(),
                scenario.getCharVal5()));

    }

    private DataItemType createStringParam(java.lang.String name,
                                           java.lang.String val) {
        DataItemType data = null;
        if (!(name == null || name.equals(""))) {
            data = new DataItemType();
            data.setName(name);
            String item = new String();
            item.setVal(val);
            data.setString(item);
        }
        return data;
    }

    private DataItemType createIntegerParam(java.lang.String name,
                                            BigDecimal val) {
        DataItemType data = null;
        if (!(name == null || name.equals(""))) {
            data = new DataItemType();
            data.setName(name);
            Int item = new Int();
            item.setVal(val == null ? null : val.longValue());
            data.setInt(item);
        }
        return data;
    }

    private DataItemType createFloatParam(java.lang.String name, BigDecimal val) {
        DataItemType data = null;
        if (!(name == null || name.equals(""))) {
            data = new DataItemType();
            data.setName(name);
            Float item = new Float();
            item.setVal(val == null ? null : val.doubleValue());
            data.setFloat(item);
        }
        return data;
    }

    private DataItemType createDateParam(java.lang.String name,
                                         java.sql.Timestamp val) {
        DataItemType data = null;
        if (!(name == null || name.equals(""))) {
            data = new DataItemType();
            data.setName(name);
            DateTime item = new DateTime();
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTimeInMillis(val.getTime());
            try {
                item.setVal(DatatypeFactory.newInstance()
                        .newXMLGregorianCalendar(cal));
            } catch (DatatypeConfigurationException e) {
                LOG.error("Exception: ", e);
            }
            data.setDateTime(item);
        }
        return data;
    }
}