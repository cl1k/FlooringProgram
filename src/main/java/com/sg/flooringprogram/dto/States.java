package com.sg.flooringprogram.dto;

import java.math.BigDecimal;

/**
 * @author Alexm
 */
public class States {

    private String stateAbbr;
    private BigDecimal taxRate;

    public States() {
    }

    public States(String state) {
        this.stateAbbr = state;
    }

    public States(String state, BigDecimal taxRate) {
        this.stateAbbr = state;
        this.taxRate = taxRate;
    }

    public String getState() {
        return stateAbbr;
    }

    public void setState(String state) {
        this.stateAbbr = state;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }


}
