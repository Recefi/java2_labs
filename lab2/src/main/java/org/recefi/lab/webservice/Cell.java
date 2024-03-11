
package org.recefi.lab.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="ownerId" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
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
    "ownerId"
})
public class Cell {

    protected int rowIdx;
    protected int colIdx;
    protected int ownerId;

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
     * Gets the value of the ownerId property.
     * 
     */
    public int getOwnerId() {
        return ownerId;
    }

    /**
     * Sets the value of the ownerId property.
     * 
     */
    public void setOwnerId(int value) {
        this.ownerId = value;
    }

}
