/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringprogram.dao;

import com.sg.flooringprogram.dto.Order;
import com.sg.flooringprogram.dto.Products;
import com.sg.flooringprogram.dto.States;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alexm
 */
public class FlooringDaoStubImpl implements FlooringDao {
    
    States onlyState = new States();
    Products onlyProduct = new Products();
    Order onlyOrder;
    
    List<Order> orderList = new ArrayList<>();
    
    public FlooringDaoStubImpl() {
        onlyOrder = new Order(1);
        onlyOrder.setCustomerName("TestPerson");
        onlyOrder.setStateName("OH");
        onlyOrder.setProductName("Laminate");
        onlyOrder.setArea(new BigDecimal ("55.5"));
        
        orderList.add(onlyOrder);
    }
    
    @Override
    public Integer findMaxKey() {
        return orderList.size();
    }

    @Override
    public List<Order> listOrders(LocalDate date) throws IOException {
        return orderList;
    }

    @Override
    public Order getOrderById(Integer orderId) {
        if (orderId == 1) {
            return onlyOrder;
        } else {
            return null;
        }
    }

    @Override
    public Order enterNewOrder(Integer orderId, Order order) throws IOException {
        if (orderId.equals(onlyOrder.getOrderId())) {
            return onlyOrder;
        } else { 
            return null;
        }
    }

    @Override
    public Order editOrder(Integer orderId, Order value) throws IOException {
         if (orderId.equals(onlyOrder.getOrderId())) {
            return onlyOrder;
        } else { 
            return null;
        }
    }

    @Override
    public Order removeOrder(Integer orderId) {
         if (orderId.equals(onlyOrder.getOrderId())) {
            return onlyOrder;
        } else { 
            return null;
        }
    }

    @Override
    public void loadOrderMap(LocalDate fileFinder) throws FileNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveNewOrders() throws IOException {
//        does nothing
    }

    @Override
    public void saveOrderToDateFile(LocalDate fileFinder) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadStatesList() throws FileNotFoundException {
        
    }

    @Override
    public void loadProductsList() throws FileNotFoundException {
    }

    @Override
    public States getStateByName(String state) {
        onlyState.setState("IN");
        onlyState.setTaxRate(new BigDecimal(5.15));
        return onlyState;
    }

    @Override
    public Products getProductByName(String product) {
        onlyProduct.setProductType("Laminate");
        onlyProduct.setLaborCostSqFt(new BigDecimal("2.15"));
        onlyProduct.setMaterialCostSqFt(new BigDecimal("2.25"));
        return onlyProduct;
    }

    @Override
    public BigDecimal getStateTaxInfo(String state) {
        return onlyState.getTaxRate();
    }
    
}
