
package org.recefi.lab.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for cell complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="cell"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="rowIdx" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="colIdx" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="owner" type="{http://server.lab.recefi.org/}ownerEnum" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cell", propOrder = {
    "rowIdx",
    "colIdx",
    "owner"
})
public class Cell {

    protected int rowIdx;
    protected int colIdx;
    @XmlSchemaType(name = "string")
    protected OwnerEnum owner;

    /**
     * Gets the value of the rowIdx property.
     * 
     */
    public int getRowIdx() {
        return rowIdx;
    }

    /**
     * Sets the value of the rowIdx property.
     * 
     */
    public void setRowIdx(int value) {
        this.rowIdx = value;
    }

    /**
     * Gets the value of the colIdx property.
     * 
     */
    public int getColIdx() {
        return colIdx;
    }

    /**
     * Sets the value of the colIdx property.
     * 
     */
    public void setColIdx(int value) {
        this.colIdx = value;
    }

    /**
     * Gets the value of the owner property.
     * 
     * @return
     *     possible object is
     *     {@link OwnerEnum }
     *     
     */
    public OwnerEnum getOwner() {
        return owner;
    }

    /**
     * Sets the value of the owner property.
     * 
     * @param value
     *     allowed object is
     *     {@link OwnerEnum }
     *     
     */
    public void setOwner(OwnerEnum value) {
        this.owner = value;
    }

}
