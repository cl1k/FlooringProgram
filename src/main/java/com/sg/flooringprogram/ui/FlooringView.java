package com.sg.flooringprogram.ui;

import com.sg.flooringprogram.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Alexm
 */
public class FlooringView {
    
    UserIO io;
    
    public FlooringView(UserIO io) {
        this.io = io;
    }

    public String exitMessage() {
        return io.readString("Are you sure you want to exit? (Y/N)");
    }

    public String confirmSave() {
        return io.readString("Order complete. Would you like to save? Y/N");
    }
    
    public void displayOrdersBanner(LocalDate dateFinder) {
        io.print("Orders for date: " + dateFinder + "***");
    }

    public LocalDate getOrderDate() {
       return io.readDate();
    }
    
    public void displayOrdersList(List<Order> ordersList) {
        for (Order currentOrder : ordersList) {
            io.print("Order: " + (currentOrder.getOrderId() + ", " +
            currentOrder.getCustomerName() + ", " +currentOrder.getStateName() + 
                    ", " + currentOrder.getProductName() + ", " + currentOrder.getArea())); 
        }
        io.readString("Press enter to continue.");
    }

    public int printMenuGetSelection() {
        io.print("=== Main Menu ===");
        io.print("1. List Orders By Date");
        io.print("2. Add New Order");
        io.print("3. Edit Existing Order");
        io.print("4. Remove Order");
        io.print("5. Save and Exit");
        
        return io.readInt("Select from above options.",1,5);
    }

    public void displayAddOrder() {
        io.print("=== Add New Order Info ===");
    }

    public Order addOrderData() {
        
        Order currentOrder = new Order();
        
        String customerName = io.readString("Enter customer name.");
        BigDecimal area = io.readDecimal("Enter area.");
        String stateSelect = io.readString("Enter customer state.");
        String productSelect = io.readString("Enter customers product choice.");
       
        currentOrder.setCustomerName(customerName);
        currentOrder.setStateName(stateSelect);
        currentOrder.setProductName(productSelect);
        currentOrder.setArea(area);
        return currentOrder;
    }

    public void displayAddOrderSuccess() {
        io.print("* Order Entered Successfully! *");
        io.readString("Press enter to continue...");
    }

    public void unknownCommand() {
        io.print("Command not recognized.");
        io.readString("Press enter to continue...");
    }

    public void displayRemoveOrderBanner() {
        io.print("=== Remove Order ===");
    }

    public void displayRemoveSuccess() {
        io.print("* Order Removed Successfully! *");
    }
    
    public int getOrderId() {
        return io.readInt("Enter order ID to select an order.");
    }

    public void displayEditOrderBanner() {
        io.print("=== Edit Order ===");
    }
    
    public void displayEditSuccess() {
        io.print("Edit entered successfully!");
        io.readString("Press enter to continue...");
    }

    public void displayOrder(Order order) {
        io.print("Id: " + order.getOrderId());
        io.print("Customer: " + order.getCustomerName()); 
        io.print("State: " + order.getStateName());
        io.print("Product Type: " + order.getProductName());
        io.print("Project Area: " + order.getArea());
        io.print("");
        io.print("Material Cost/SqFt: $" + order.getMaterialCostSqFt());
        io.print("Material Cost Total: $" + order.getMaterialCostTotal());
        io.print("Labor Cost/SqFt: $" + order.getLaborCostSqFt());
        io.print("Labor Cost Total: $" + order.getLaborCostTotal());
        io.print("Tax Rate: " + order.getStateName() + "%");
        io.print("Tax Total: $" + order.getStateTax()); 
        io.print("Total Cost: $" + order.getTotalCost());
        
        io.readString("Press enter to continue...");
    }

    public int getSubAndPrintSelect() {
        io.print("=== Edit Order ===");
        io.print("1. Customer Name");
        io.print("2. Customer State");
        io.print("3. Product Type");
        io.print("4. Order Area");
        io.print("5. Exit Menu");
        
        return io.readInt("Select a field to edit.");
    }

    public String editString(String value) {
        return io.readString("Enter new information for " + value + ".");
    }

    public BigDecimal editArea() {
        return io.readDecimal("Enter new information for order area.");
    }

    public void errorMessage(String message) {
        io.print(io.readString(message));
    }

    public void displayUnsavedOrder() {
        io.print("Order unsaved. Returning to menu.");
    }

    public String setMode() {
        String input = io.readString("Run Program in Training mode? (Y/N)");
        return input;

    }
}
