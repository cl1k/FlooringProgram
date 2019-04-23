package com.sg.flooringprogram.dto;

import java.math.BigDecimal;

/**
 * @author Alexm
 */
public class Products {

    private String productType;
    private BigDecimal materialCostSqFt;
    private BigDecimal laborCostSqFt;

    public Products() {
    }

    public Products(String productType) {
        this.productType = productType;
    }

    public Products(String productType, BigDecimal materialCostSqft, BigDecimal laborCostSqFt) {
        this.productType = productType;
        this.materialCostSqFt = materialCostSqft;
        this.laborCostSqFt = laborCostSqFt;

    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getMaterialCostSqFt() {
        return materialCostSqFt;
    }

    public void setMaterialCostSqFt(BigDecimal materialCostSqFt) {
        this.materialCostSqFt = materialCostSqFt;
    }

    public BigDecimal getLaborCostSqFt() {
        return laborCostSqFt;
    }

    public void setLaborCostSqFt(BigDecimal laborCostSqFt) {
        this.laborCostSqFt = laborCostSqFt;
    }


}