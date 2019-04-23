package com.sg.flooringprogram.dao;

import com.sg.flooringprogram.dto.Order;
import com.sg.flooringprogram.dto.Products;
import com.sg.flooringprogram.dto.States;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
/**
 *
 * @author Alexm
 */
public interface FlooringDao {
    
    Integer findMaxKey();
    
    List<Order> listOrders(LocalDate date) throws IOException;
    
    Order getOrderById(Integer orderId);
    
    Order enterNewOrder(Integer orderId, Order order) throws IOException;
    
    Order editOrder(Integer orderId, Order value) throws IOException;
    
    Order removeOrder(Integer orderId);
    
    void loadOrderMap(LocalDate fileFinder) throws FileNotFoundException;
    
    void saveNewOrders() throws IOException;
    
    void saveOrderToDateFile(LocalDate fileFinder) throws IOException;
    
    void loadStatesList() throws FileNotFoundException;

    void loadProductsList() throws FileNotFoundException;

    States getStateByName(String state);
    
    Products getProductByName(String product);
  
    BigDecimal getStateTaxInfo(String state);
    
}
