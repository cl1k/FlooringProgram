package com.sg.flooringprogram.dao;

import com.sg.flooringprogram.dto.Order;
import com.sg.flooringprogram.dto.Products;
import com.sg.flooringprogram.dto.States;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.valueOf;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Alexm
 */
public class FlooringDaoFileImpl implements FlooringDao {
    
    private static final String DELIMITER = "::";
    private static final String STATES_FILE = "states.txt";
    private static final String PRODUCTS_FILE = "products.txt";
    private final Map<Integer, Order> orderMap = new HashMap<>();
    private final Map<String, Products> productMap = new HashMap<>();
    private final Map<String, States> stateMap = new HashMap<>();

    @Override
    public Integer findMaxKey() {
        int maxKey = 0;
        List keyList = new ArrayList<>(orderMap.keySet());
        for (int i = 0; i < keyList.size();i++) {
            if (maxKey < valueOf(i)) {
                maxKey = valueOf(i);
            }
        }
        return maxKey;
    }

    @Override
    public Order getOrderById(Integer objectId ) {
        return orderMap.get(objectId);
    }

    @Override
    public Order enterNewOrder(Integer objectId, Order order) throws IOException { 
        Order newOrder = orderMap.put(objectId, order);
        saveNewOrders();
        return newOrder;
    }

    @Override
    public Order removeOrder(Integer orderId) {
        Order removedOrder = orderMap.remove(orderId);
        return removedOrder;
    }

    @Override
    public Order editOrder(Integer orderId, Order value) throws IOException {
        Order editOrder = orderMap.replace(orderId, value);
        return editOrder;
    }
    
    @Override
    public List<Order> listOrders(LocalDate date) throws IOException {
        orderMap.clear();
        loadOrderMap(date);
        return new ArrayList<>(orderMap.values());
    }
    
    @Override
    public States getStateByName(String state) {
        return stateMap.get(state);
    }

    @Override
    public BigDecimal getStateTaxInfo(String state) {
        return stateMap.get(state).getTaxRate();
    }
    
    @Override
    public Products getProductByName(String product) {
        return productMap.get(product);
    }
    
    @Override
    public void loadOrderMap(LocalDate fileFinder) throws FileNotFoundException {
        
        Scanner scanner;
        String date = fileFinder.format(DateTimeFormatter.ofPattern("MMddYYYY"));
        
        try {
            scanner = new Scanner(new BufferedReader(
                    new FileReader("Orders_" + date + ".txt" )));
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Failed to load file into memory.");
        }
        String currentLine;
        String[] currentTokens;
        
        while(scanner.hasNextLine()) {
            
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Order currentOrder = new Order(Integer.parseInt(currentTokens[0]));
            currentOrder.setCustomerName(currentTokens[1]);
            currentOrder.setStateName(currentTokens[2]);
            currentOrder.setStateTax(new BigDecimal(currentTokens[3]));
            currentOrder.setMaterialCostTotal(new BigDecimal(currentTokens[4]));
            currentOrder.setLaborCostTotal(new BigDecimal(currentTokens[5]));
            currentOrder.setTaxTotal(new BigDecimal(currentTokens[6]));
            currentOrder.setTotalCost(new BigDecimal(currentTokens[7]));
            currentOrder.setArea(new BigDecimal(currentTokens[8]));
            currentOrder.setProductName(currentTokens[9]);
            currentOrder.setMaterialCostSqFt(new BigDecimal(currentTokens[10]));
            currentOrder.setLaborCostSqFt(new BigDecimal(currentTokens[11]));
            currentOrder.setOrderDate(fileFinder);
            
            orderMap.put(currentOrder.getOrderId(), currentOrder);
        }
        scanner.close();
    }

    @Override
    public void saveNewOrders() throws IOException {
        PrintWriter out;
        
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MMddYYYY"));
        
        try {
            out = new PrintWriter(new FileWriter("Orders_" + date + ".txt"));
        } catch (IOException e) {
            throw new IOException("Write to file failed. Orders not saved!");
        }
        List<Order> orderList = new ArrayList<>(orderMap.values());
        for (Order currentOrder : orderList) {
            out.println(currentOrder.getOrderId() + DELIMITER
            + currentOrder.getCustomerName() + DELIMITER
            + currentOrder.getStateName() + DELIMITER
            + currentOrder.getStateTax()+ DELIMITER
            + currentOrder.getMaterialCostTotal() + DELIMITER
            + currentOrder.getLaborCostTotal() + DELIMITER
            + currentOrder.getTaxTotal() + DELIMITER
            + currentOrder.getTotalCost() + DELIMITER
            + currentOrder.getArea() + DELIMITER
            + currentOrder.getProductName() + DELIMITER
            + currentOrder.getMaterialCostSqFt() + DELIMITER
            + currentOrder.getLaborCostSqFt());
            
            out.flush();
        }
        out.close();
    }
    
    @Override
    public void saveOrderToDateFile(LocalDate fileFinder) throws IOException {
        PrintWriter out;
        
        String date = fileFinder.format(DateTimeFormatter.ofPattern("MMddYYYY"));
        
        try {
            out = new PrintWriter(new FileWriter("Orders_" + date + ".txt"));
        } catch (IOException e) {
            throw new IOException("Write to file failed. Orders not saved!");
        }
        List<Order> orderList = new ArrayList<>(orderMap.values());
        for (Order currentOrder : orderList) {
            out.println(currentOrder.getOrderId() + DELIMITER
            + currentOrder.getCustomerName() + DELIMITER
            + currentOrder.getStateName() + DELIMITER
            + currentOrder.getStateTax() + DELIMITER
            + currentOrder.getMaterialCostTotal() + DELIMITER
            + currentOrder.getLaborCostTotal() + DELIMITER
            + currentOrder.getTaxTotal() + DELIMITER
            + currentOrder.getTotalCost() + DELIMITER
            + currentOrder.getArea() + DELIMITER
            + currentOrder.getProductName() + DELIMITER
            + currentOrder.getMaterialCostSqFt() + DELIMITER
            + currentOrder.getMaterialCostSqFt());
            
            out.flush();
        }
        out.close();
    }
    
    @Override
    public void loadStatesList() throws FileNotFoundException {
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(STATES_FILE)));
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Failed to load states file into memory.");
        }
        
        String currentLine;
        String[] currentTokens;
        while (scanner.hasNextLine()) {
        currentLine = scanner.nextLine();
        currentTokens = currentLine.split(DELIMITER);
        States currentState = new States(currentTokens[0]);
        currentState.setTaxRate(new BigDecimal(currentTokens[1]));
        
        stateMap.put(currentState.getState(),currentState);
        }
        scanner.close();
    }
    

    @Override
    public void loadProductsList() throws FileNotFoundException {
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(PRODUCTS_FILE)));
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Failed to load products file into memory.");
        }
        
        String currentLine;
        String[] currentTokens;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Products currentProduct = new Products(currentTokens[0]);
            currentProduct.setMaterialCostSqFt(new BigDecimal(currentTokens[1]));
            currentProduct.setLaborCostSqFt(new BigDecimal(currentTokens[2]));

            productMap.put(currentProduct.getProductType(),currentProduct);
        }
    }

}
