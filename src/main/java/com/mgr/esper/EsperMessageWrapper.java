package com.mgr.esper;

/**
 * Wraper wiadomosci wysylanej do silnika Esper
 *
 * @author michal
 */
public class EsperMessageWrapper {
    String payload;
    String eventName;

    public String getPayload() {
        return payload;
    }

    /**
     * Ustaw tresc
     *
     * @param payload
     */
    public void setPayload(String payload) {
        this.payload = payload;
    }

    /**
     * Pobierz nazwe eventu
     *
     * @return
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * Ustaw nazwe eventu
     *
     * @param eventName
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}
