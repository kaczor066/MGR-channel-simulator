package com.mgr.rtdm;

import com.mgr.rtdm.domain.EventResponseType;
import com.mgr.rtdm.domain.EventType;
import com.mgr.rtdm.domain.ObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBElement;

/**
 * Klient web service'u wystawianego przez aplikacje RTDM
 *
 * @author michal
 */
@Component
public class RTDMWebServiceClient extends WebServiceGatewaySupport {

    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    Jaxb2Marshaller RTDMmarshaller;
    @Value("${rtdm.ws.uri}")
    String URI;

    @PostConstruct
    public void init() {
        setDefaultUri(URI);
        setMarshaller(RTDMmarshaller);
        setUnmarshaller(RTDMmarshaller);
    }

    /**
     * Wysylanie wiadomosci i czekanie na odpowiedz (RTDM dziala tylko w trybie
     * request/reply
     *
     * @param event
     * @return
     */
    public EventResponseType postMessageAndWaitForResponse(EventType event) {
        LOG.debug("Sending event: {}", event);
        ObjectFactory objFactory = new ObjectFactory();
        JAXBElement response = (JAXBElement) getWebServiceTemplate()
                .marshalSendAndReceive(objFactory.createEvent(event),
                        new SoapActionCallback(URI));
        return (EventResponseType) response.getValue();
    }
}
