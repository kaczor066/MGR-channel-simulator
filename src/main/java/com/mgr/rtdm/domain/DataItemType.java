//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0.5-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.04.19 at 07:43:12 PM CEST 
//


package com.mgr.rtdm.domain;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for DataItemType complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="DataItemType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element ref="{http://www.sas.com/xml/schema/sas-svcs/rtdm-1.1}String"/>
 *         &lt;element ref="{http://www.sas.com/xml/schema/sas-svcs/rtdm-1.1}Int"/>
 *         &lt;element ref="{http://www.sas.com/xml/schema/sas-svcs/rtdm-1.1}Float"/>
 *         &lt;element ref="{http://www.sas.com/xml/schema/sas-svcs/rtdm-1.1}DateTime"/>
 *       &lt;/choice>
 *       &lt;attribute name="name" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="Name_1"/>
 *             &lt;enumeration value="Name_2"/>
 *             &lt;enumeration value="Name_3"/>
 *             &lt;enumeration value="Name_4"/>
 *             &lt;enumeration value="Name_5"/>
 *             &lt;enumeration value="Name_6"/>
 *             &lt;enumeration value="Name_7"/>
 *             &lt;enumeration value="Name_8"/>
 *             &lt;enumeration value="Name_9"/>
 *             &lt;enumeration value="Name_10"/>
 *             &lt;enumeration value="Name_11"/>
 *             &lt;enumeration value="Name_12"/>
 *             &lt;enumeration value="Name_13"/>
 *             &lt;enumeration value="Name_14"/>
 *             &lt;enumeration value="Name_15"/>
 *             &lt;enumeration value="Name_16"/>
 *             &lt;enumeration value="Name_17"/>
 *             &lt;enumeration value="Name_18"/>
 *             &lt;enumeration value="Name_19"/>
 *             &lt;enumeration value="Name_20"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataItemType", propOrder = {
        "string",
        "_int",
        "_float",
        "dateTime"
})
public class DataItemType {

    @XmlElement(name = "String")
    protected com.mgr.rtdm.domain.String string;
    @XmlElement(name = "Int")
    protected Int _int;
    @XmlElement(name = "Float")
    protected Float _float;
    @XmlElement(name = "DateTime")
    protected DateTime dateTime;
    @XmlAttribute(required = true)
    protected java.lang.String name;

    /**
     * Gets the value of the string property.
     *
     * @return possible object is
     * {@link com.mgr.rtdm.domain.String }
     */
    public com.mgr.rtdm.domain.String getString() {
        return string;
    }

    /**
     * Sets the value of the string property.
     *
     * @param value allowed object is
     *              {@link com.mgr.rtdm.domain.String }
     */
    public void setString(com.mgr.rtdm.domain.String value) {
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

    /**
     * Gets the value of the name property.
     *
     * @return possible object is
     * {@link java.lang.String }
     */
    public java.lang.String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value allowed object is
     *              {@link java.lang.String }
     */
    public void setName(java.lang.String value) {
        this.name = value;
    }

}
