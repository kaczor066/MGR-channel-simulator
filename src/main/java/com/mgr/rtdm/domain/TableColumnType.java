//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0.5-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.04.19 at 07:43:12 PM CEST 
//


package com.mgr.rtdm.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for TableColumnType complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="TableColumnType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="name" use="required" type="{http://www.sas.com/xml/schema/sas-svcs/rtdm-1.1}NameType" />
 *       &lt;attribute name="type" use="required" type="{http://www.sas.com/xml/schema/sas-svcs/rtdm-1.1}DataTypes" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TableColumnType")
public class TableColumnType {

    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected java.lang.String name;
    @XmlAttribute(required = true)
    protected DataTypes type;

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

    /**
     * Gets the value of the type property.
     *
     * @return possible object is
     * {@link DataTypes }
     */
    public DataTypes getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     *
     * @param value allowed object is
     *              {@link DataTypes }
     */
    public void setType(DataTypes value) {
        this.type = value;
    }

}