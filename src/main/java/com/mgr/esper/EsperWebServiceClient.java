package com.mgr.esper;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Klient RESTowego web service'u wystawianego przez aplikacje opakowujaca
 * silnik Esper
 *
 * @author michal
 */
@Component
@Scope("prototype")
public class EsperWebServiceClient extends WebServiceGatewaySupport {

    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    private HttpClient client = new HttpClient();
    @Value("${esper.ws.uri}")
    String URI;

    /**
     * Metoda wysylajaca wiadomosc
     *
     * @param message
     */
    public void postMessage(EsperMessageWrapper message) {
        PostMethod post = new PostMethod(URI + "/" + message.getEventName());
        try {
            post.setRequestEntity(new StringRequestEntity(message.getPayload(),
                    "application/json", StandardCharsets.UTF_8.toString()));
            client.executeMethod(post);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
