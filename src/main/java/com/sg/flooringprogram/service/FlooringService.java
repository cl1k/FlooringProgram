package com.sg.flooringprogram.service;

import com.sg.flooringprogram.dao.DataPersistenceException;
import com.sg.flooringprogram.dao.FlooringAuditDao;
import com.sg.flooringprogram.dao.FlooringDao;
import com.sg.flooringprogram.dto.Order;
import com.sg.flooringprogram.dto.Products;
import com.sg.flooringprogram.dto.States;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Alexm
 */
public class FlooringService {

    FlooringDao dao;
    FlooringAuditDao audit;

    boolean training = false;

    public FlooringService(FlooringDao dao, FlooringAuditDao audit) {
        this.dao = dao;
        this.audit = audit;
    }

    public boolean trainingSelect(String select) {
        if (select.equalsIgnoreCase("Y")) {
            return true;
        } else return false;
    }

    public Integer findMaxKey() {
        return dao.findMaxKey();
    }

    public List<Order> listOrders(LocalDate date) throws IOException {
        dao.listOrders(date);
        if (dao.listOrders(date).isEmpty()) {
            throw new IOException("No orders found for " + date + "."
                    + "Enter valid order date.");
        } else {
            return dao.listOrders(date);
        }
    }

    public Order getOrderById(Integer orderId) throws OrderIdException {
        dao.getOrderById(orderId);
        if (dao.getOrderById(orderId) == null) {
            throw new OrderIdException("Order ID not found. Please enter a valid ID.");
        } else {
            return dao.getOrderById(orderId);
        }
    }

    private void calcOrder(Order order) {
        order.setMaterialCostTotal(order.getMaterialCostSqFt().multiply(order.getArea()
        ).setScale(2, RoundingMode.HALF_UP));
        order.setLaborCostTotal(order.getLaborCostSqFt().multiply(order.getArea())
                .setScale(2, RoundingMode.HALF_UP));
        order.setTaxTotal(order.getStateTax().divide(new BigDecimal("100.00"))
                .multiply((order.getLaborCostTotal().add(order.getMaterialCostTotal())))
                .setScale(2, RoundingMode.HALF_UP));
        order.setTotalCost(order.getLaborCostTotal().add(order.getMaterialCostTotal()
                .add(order.getTaxTotal())));
    }

    private void getTaxInfo(Order order) throws StateValidationException {
        States customerState = dao.getStateByName(order.getStateName());
        if (customerState == null) {
            throw new StateValidationException("State information not found.");
        }
        order.setStateName(customerState.getState());
        order.setStateTax(customerState.getTaxRate());
    }

    private void getMaterialCosts(Order order) throws ProductValidationException {
        Products customerProduct = dao.getProductByName(order.getProductName());
        if (customerProduct == null) {
            throw new ProductValidationException("Product information not found.");
        }
        order.setProductName(customerProduct.getProductType());
        order.setLaborCostSqFt(customerProduct.getLaborCostSqFt());
        order.setMaterialCostSqFt(customerProduct.getMaterialCostSqFt());
    }

    private void validateOrder(Order order) throws OrderValidationException {
        String fieldMissing = "";
        if (order.getCustomerName().isEmpty() || order.getCustomerName() == null) {
            fieldMissing += "Customer name missing. All fields are required.";
        }
        if (order.getStateName().isEmpty() || order.getStateName() == null) {
            fieldMissing += "State abbreviation missing. All fields are required.";
        }
        if (order.getProductName().isEmpty() || order.getProductName() == null) {
            fieldMissing += "Product type missing. All fields are required.";
        }
        if (order.getArea().compareTo(BigDecimal.ZERO) == 0 || order.getArea() == null) {
            fieldMissing += "Area field empty. All fields are required.";
        }
        if (!fieldMissing.isEmpty()) {
            throw new OrderValidationException(fieldMissing);
        }
    }

    public Order completeOrder(Order order) throws OrderValidationException,
            StateValidationException, ProductValidationException {

        validateOrder(order);
        getTaxInfo(order);
        getMaterialCosts(order);
        calcOrder(order);

        return order;
    }

    public Order enterNewOrder(Integer orderId, Order order) throws IOException, OrderIdException, DataPersistenceException {
        dao.enterNewOrder(orderId, order);
        if (dao.getOrderById(orderId) != null) {
            throw new OrderIdException("Duplicate ID detected. Order will not be saved.");
        } else {
            audit.writeAuditEntry("Order #:" + order.getOrderId());
            dao.saveNewOrders();
            return dao.enterNewOrder(orderId, order);
        }
    }

    public Order editOrder(Integer orderId, Order value) throws IOException, OrderIdException {
        dao.editOrder(orderId, value);
        if (dao.getOrderById(orderId) == null) {
            throw new OrderIdException("Order ID not found. Enter a valid ID.");
        } else {
            return dao.editOrder(orderId, value);
        }
    }

    public Order removeOrder(Integer orderId) {
        return dao.removeOrder(orderId);
    }

    public void saveNewOrders() throws IOException {
        if (training = false) {
            dao.saveNewOrders();
        } else {
//            do nothing
        }
    }

    public void saveOrderToDateFile(LocalDate fileFinder) throws IOException {
        if (training = false) {
            dao.saveOrderToDateFile(fileFinder);
        } else {
//            do nothing
        }
    }

    public void programStart() {
        try {
            dao.loadProductsList();
            dao.loadStatesList();
        } catch (FileNotFoundException ex) {
            ex.getMessage();
        }
    }

}
