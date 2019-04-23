/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringprogram.dao;

import com.sg.flooringprogram.dto.Order;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Alexm
 */

public class FlooringDaoFileImplTest {

    static FlooringDaoFileImpl dao = new FlooringDaoFileImpl();

    public FlooringDaoFileImplTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws IOException{
         LocalDate date = LocalDate.of(1800, 01, 01);
         List<Order> ordersList = dao.listOrders(date);
         for (Order order : ordersList) {
         dao.removeOrder(order.getOrderId());
         }
         
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testEnterNewOrder() throws IOException {


        Order order = new Order(3);
        order.setCustomerName("Test");
        order.setMaterialCostTotal(new BigDecimal("100"));
        order.setLaborCostTotal(new BigDecimal("50"));
        order.setTaxTotal(new BigDecimal("25"));
        order.setTotalCost(new BigDecimal("175"));
        order.setArea(new BigDecimal("66.6"));
        order.setStateName("OH");
        order.setStateTax(new BigDecimal("6.25"));
        order.setProductName("Wood");
        order.setMaterialCostSqFt(new BigDecimal("5.15"));
        order.setLaborCostSqFt(new BigDecimal("4.75"));
        dao.enterNewOrder(order.getOrderId(), order);

        Order fromDao = dao.getOrderById(order.getOrderId());

        assertEquals(order, fromDao);
    }

    /**
     * Test of getStateTaxInfo method, of class FlooringDaoFileImpl. Also tests
     * load states
     */
    @Test
    public void testGetStateTaxInfo() throws Exception {
        dao.loadStatesList();
        String state = "MI";
        dao.getStateTaxInfo(state);

    }

    /**
     * Test of getMaterialCost method, of class FlooringDaoFileImpl. Also tests
     * load products
     */
    @Test
    public void testGetMaterialCost() throws Exception {
        dao.loadProductsList();
        String product = "Carpet";
        dao.getProductByName(product).getMaterialCostSqFt();
    }

    /**
     * Test of getLaborCost method, of class FlooringDaoFileImpl.
     */
    @Test
    public void testGetLaborCost() throws Exception {
        String product = "Wood";
        dao.getProductByName(product).getLaborCostSqFt();
    }

    @Test
    public void testRemoveOrder() throws IOException {
        LocalDate date = LocalDate.of(1800, 01, 01);
        dao.loadOrderMap(date);
        dao.removeOrder(1);
        dao.saveOrderToDateFile(date);
    }

    /**
     * Test of listOrders method, of class FlooringDaoFileImpl.
     *
     * @throws java.io.IOException
     */
    @Test
    public void testListOrders() throws IOException {
        LocalDate date = LocalDate.of(1800, 01, 01);


        Order order = new Order(3);
        order.setCustomerName("Test");
        order.setMaterialCostTotal(new BigDecimal("100"));
        order.setLaborCostTotal(new BigDecimal("50"));
        order.setTaxTotal(new BigDecimal("25"));
        order.setTotalCost(new BigDecimal("175"));
        order.setArea(new BigDecimal ("66.6"));
        order.setStateName("OH");
        order.setStateTax(new BigDecimal("6.25"));
        order.setProductName("Wood");
        order.setMaterialCostSqFt(new BigDecimal("5.15"));
        order.setLaborCostSqFt(new BigDecimal("4.75"));

        dao.enterNewOrder(order.getOrderId(), order);
        dao.saveOrderToDateFile(date);
        assertEquals(1, dao.listOrders(date).size());
    }

}
