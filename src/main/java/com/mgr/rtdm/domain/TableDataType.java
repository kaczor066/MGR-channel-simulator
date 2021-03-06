//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0.5-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.04.19 at 07:43:12 PM CEST 
//


package com.mgr.rtdm.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Only one of the following sequenced elements should be
 * included with element TableType.Row.Col below. This schema
 * does not make this constraint visible for reasons previously
 * noted.
 * <p/>
 * <p/>
 * <p>Java class for TableDataType complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="TableDataType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.sas.com/xml/schema/sas-svcs/rtdm-1.1}String" minOccurs="0"/>
 *         &lt;element ref="{http://www.sas.com/xml/schema/sas-svcs/rtdm-1.1}Int" minOccurs="0"/>
 *         &lt;element ref="{http://www.sas.com/xml/schema/sas-svcs/rtdm-1.1}Float" minOccurs="0"/>
 *         &lt;element ref="{http://www.sas.com/xml/schema/sas-svcs/rtdm-1.1}Boolean" minOccurs="0"/>
 *         &lt;element ref="{http://www.sas.com/xml/schema/sas-svcs/rtdm-1.1}DateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TableDataType", propOrder = {
        "string",
        "_int",
        "_float",
        "_boolean",
        "dateTime"
})
public class TableDataType {

    @XmlElement(name = "String")
    protected String string;
    @XmlElement(name = "Int")
    protected Int _int;
    @XmlElement(name = "Float")
    protected Float _float;
    @XmlElement(name = "Boolean")
    protected Boolean _boolean;
    @XmlElement(name = "DateTime")
    protected DateTime dateTime;

    /**
     * Gets the value of the string property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getString() {
        return string;
    }

    /**
     * Sets the value of the string property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setString(String value) {
        this.string = value;
    }

    /**
     * Gets the value of the int property.
     *
     * @return possible object is
     * {@link Int }
     */
    public Int getInt() {
        return _int;
    }

    /**
     * Sets the value of the int property.
     *
     * @param value allowed object is
     *              {@link Int }
     */
    public void setInt(Int value) {
        this._int = value;
    }

    /**
     * Gets the value of the float property.
     *
     * @return possible object is
     * {@link Float }
     */
    public Float getFloat() {
        return _float;
    }

    /**
     * Sets the value of the float property.
     *
     * @param value allowed object is
     *              {@link Float }
     */
    public void setFloat(Float value) {
        this._float = value;
    }

    /**
     * Gets the value of the boolean property.
     *
     * @return possible object is
     * {@link Boolean }
     */
    public Boolean getBoolean() {
        return _boolean;
    }

    /**
     * Sets the value of the boolean property.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setBoolean(Boolean value) {
        this._boolean = value;
    }

    /**
     * Gets the value of the dateTime property.
     *
     * @return possible object is
     * {@link DateTime }
     */
    public DateTime getDateTime() {
        return dateTime;
    }

    /**
     * Sets the value of the dateTime property.
     *
     * @param value allowed object is
     *              {@link DateTime }
     */
    public void setDateTime(DateTime value) {
        this.dateTime = value;
    }

}
