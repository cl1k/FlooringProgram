package com.sg.flooringprogram.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Alexm
 */
public class Order {

    private Integer orderId;
    private String customerName;

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    private String stateName;
    private String productName;
    private BigDecimal materialCostSqFt;
    private BigDecimal laborCostSqFt;
    private BigDecimal stateTax;
    private BigDecimal materialCostTotal;
    private BigDecimal laborCostTotal;
    private BigDecimal taxTotal;
    private BigDecimal totalCost;
    private LocalDate orderDate;
    private BigDecimal area;
    public Products product;
    public States state;

    public Order() {
    }

    public Order(Integer orderId, String customerName, BigDecimal area,
                 BigDecimal materialCostTotal, BigDecimal laborCostTotal,
                 BigDecimal taxTotal, BigDecimal totalCost, States state,
                 Products product, LocalDate orderDate, String stateName,
                 String productName) {

        this.orderId = orderId;
        this.customerName = customerName;
        this.area = area;
        this.materialCostTotal = materialCostTotal;
        this.laborCostTotal = laborCostTotal;
        this.taxTotal = taxTotal;
        this.totalCost = totalCost;
        this.product = product;
        this.state = state;
        this.orderDate = orderDate;
        this.stateName = stateName;
        this.productName = productName;
    }

    public Order(Integer orderId) {
        this.orderId = orderId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigDecimal getTaxTotal() { return taxTotal; }

    public void setTaxTotal(BigDecimal taxTotal) {
        this.taxTotal = taxTotal;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal costTotal) {
        this.totalCost = costTotal;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
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

    public BigDecimal getStateTax() {
        return stateTax;
    }

    public void setStateTax(BigDecimal stateTax) {
        this.stateTax = stateTax;
    }

    public BigDecimal getMaterialCostTotal() { return materialCostTotal; }

    public void setMaterialCostTotal(BigDecimal materialCostTotal) {
        this.materialCostTotal = materialCostTotal;
    }

    public BigDecimal getLaborCostTotal() {
        return laborCostTotal;
    }

    public void setLaborCostTotal(BigDecimal laborCostTotal) {
        this.laborCostTotal = laborCostTotal;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.orderId);
        hash = 97 * hash + Objects.hashCode(this.customerName);
        hash = 97 * hash + Objects.hashCode(this.stateName);
        hash = 97 * hash + Objects.hashCode(this.productName);
        hash = 97 * hash + Objects.hashCode(this.materialCostSqFt);
        hash = 97 * hash + Objects.hashCode(this.laborCostSqFt);
        hash = 97 * hash + Objects.hashCode(this.stateTax);
        hash = 97 * hash + Objects.hashCode(this.materialCostTotal);
        hash = 97 * hash + Objects.hashCode(this.laborCostTotal);
        hash = 97 * hash + Objects.hashCode(this.taxTotal);
        hash = 97 * hash + Objects.hashCode(this.totalCost);
        hash = 97 * hash + Objects.hashCode(this.orderDate);
        hash = 97 * hash + Objects.hashCode(this.area);
        hash = 97 * hash + Objects.hashCode(this.product);
        hash = 97 * hash + Objects.hashCode(this.state);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (!Objects.equals(this.orderId, other.orderId)) {
            return false;
        }
        if (!Objects.equals(this.customerName, other.customerName)) {
            return false;
        }
        if (!Objects.equals(this.stateName, other.stateName)) {
            return false;
        }
        if (!Objects.equals(this.productName, other.productName)) {
            return false;
        }
        if (!Objects.equals(this.materialCostSqFt, other.materialCostSqFt)) {
            return false;
        }
        if (!Objects.equals(this.laborCostSqFt, other.laborCostSqFt)) {
            return false;
        }
        if (!Objects.equals(this.stateTax, other.stateTax)) {
            return false;
        }
        if (!Objects.equals(this.materialCostTotal, other.materialCostTotal)) {
            return false;
        }
        if (!Objects.equals(this.laborCostTotal, other.laborCostTotal)) {
            return false;
        }
        if (!Objects.equals(this.taxTotal, other.taxTotal)) {
            return false;
        }
        if (!Objects.equals(this.totalCost, other.totalCost)) {
            return false;
        }
        if (!Objects.equals(this.orderDate, other.orderDate)) {
            return false;
        }
        if (!Objects.equals(this.area, other.area)) {
            return false;
        }
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        return true;
    }

}
