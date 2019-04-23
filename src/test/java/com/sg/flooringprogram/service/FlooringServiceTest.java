/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringprogram.service;

import com.sg.flooringprogram.dao.FlooringAuditDao;
import com.sg.flooringprogram.dao.FlooringAuditDaoFileImpl;
import com.sg.flooringprogram.dao.FlooringDao;
import com.sg.flooringprogram.dao.FlooringDaoStubImpl;
import com.sg.flooringprogram.dto.Order;
import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Alexm
 */
public class FlooringServiceTest {
    
    private FlooringService service;
    
    public FlooringServiceTest() {
        FlooringDao dao = new FlooringDaoStubImpl();
        FlooringAuditDao audit = new FlooringAuditDaoFileImpl();
        service = new FlooringService(dao, audit);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testEnterNewOrder() throws Exception {
        Order order = new Order(2);
        order.setCustomerName("Other");
        order.setStateName("IN");
        order.setArea(new BigDecimal(2));
        order.setProductName("Laminate");
        service.completeOrder(order);
        service.enterNewOrder(order.getOrderId(), order);
    }
    
    @Test
    public void testEnterDuplicateOrder() throws Exception {
        Order order = new Order(2);
        order.setCustomerName("SameID");
        order.setStateName("IN");
        order.setArea(new BigDecimal(66.6));
        order.setProductName("Laminate");
        service.completeOrder(order);
        try {
            service.enterNewOrder(order.getOrderId(), order);
            Assert.fail("Expected DuplicateIDException was not thrown.");
        } catch (OrderIdException e) {
            return;
        }
    }
    
    @Test
    public void testInvalidData() throws Exception {
        Order order = new Order(3);
        order.setCustomerName("BadData");
        order.setStateName("Flobbirty");
        order.setProductName("Laminate");
        order.setArea(new BigDecimal(88));
        try {
            service.completeOrder(order);
            Assert.fail("Expected Validation Exception was not thrown.");
        } catch (OrderValidationException e) {
            return;
        }
    }
    
}
