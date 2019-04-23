package com.sg.flooringprogram.controller;

import com.sg.flooringprogram.dao.DataPersistenceException;
import com.sg.flooringprogram.dto.Order;
import com.sg.flooringprogram.service.FlooringService;
import com.sg.flooringprogram.service.OrderIdException;
import com.sg.flooringprogram.service.OrderValidationException;
import com.sg.flooringprogram.service.ProductValidationException;
import com.sg.flooringprogram.service.StateValidationException;
import com.sg.flooringprogram.ui.FlooringView;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Alexm
 */
public class FlooringController {

    FlooringService service;
    FlooringView view;

    public FlooringController(FlooringService service, FlooringView view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        boolean keepGoing = true;
        service.trainingSelect(view.setMode());
        service.programStart();


        while (keepGoing) {
            int menuSelection = getMenuSelection();

            switch (menuSelection) {
                case 1:
                    listOrdersByDate();
                    break;
                case 2:
                    addOrder();
                    break;
                case 3:
                    editOrder();
                    break;
                case 4:
                    removeOrder();
                    break;
                case 5:
                    String input = view.exitMessage();
                    if (input.equalsIgnoreCase("Y")) {
                        System.exit(0);
                    } else {
                        break;
                    }
                    break;
                default:
                    unknownCommand();
                    break;
            }
        }
    }

    private void listOrdersByDate() {
        try {
            LocalDate dateFinder = view.getOrderDate();
            view.displayOrdersBanner(dateFinder);
            List<Order> ordersList = service.listOrders(dateFinder);
            view.displayOrdersList(ordersList);
        } catch (IOException ex) {
            view.errorMessage("No orders found for given date.");
        }
    }

    private int getMenuSelection() {
        return view.printMenuGetSelection();
    }

    private void addOrder() {
        try {
            view.displayAddOrder();
            Order newOrder = service.completeOrder(view.addOrderData());
            String input = view.confirmSave();
            if (input.equalsIgnoreCase("Y")) {
                try {
                    newOrder.setOrderId(service.findMaxKey() + 1);
                    service.enterNewOrder(newOrder.getOrderId(), newOrder);
                    view.displayAddOrderSuccess();
                } catch (OrderIdException | IOException | DataPersistenceException ex) {
                    view.errorMessage("File not found. Order failed to save.");
                }
            } else if (!input.equalsIgnoreCase("Y")) {
                view.displayUnsavedOrder();
            }
        } catch (OrderValidationException | StateValidationException |
                ProductValidationException ex) {
            view.errorMessage("Failed to validate order. All fields must contain valid order data.");
        }
    }

    private void removeOrder() {
        try {
            view.displayRemoveOrderBanner();
            LocalDate dateFinder = view.getOrderDate();
            view.displayOrdersBanner(dateFinder);
            List<Order> ordersList = service.listOrders(dateFinder);
            view.displayOrdersList(ordersList);
            service.removeOrder(view.getOrderId());
            view.displayRemoveSuccess();
            service.saveOrderToDateFile(dateFinder);
        } catch (IOException e) {
            view.errorMessage("File not found. Order removal failed.");
        }
    }

    private void editOrder() {
        try {
            view.displayEditOrderBanner();
            LocalDate dateFinder = view.getOrderDate();
            view.displayOrdersBanner(dateFinder);
            List<Order> ordersList = service.listOrders(dateFinder);
            view.displayOrdersList(ordersList);
            Order order = service.getOrderById(view.getOrderId());
            view.displayOrder(order);
            int subSelect = getSubSelection();

            switch (subSelect) {
                case 1:
                    String newName = view.editString("Customer Name");
                    order.setCustomerName(newName);
                    service.completeOrder(order);
                    service.saveOrderToDateFile(dateFinder);
                    break;
                case 2:
                    String newState = view.editString("Customer State");
                    order.setStateName(newState);
                    service.completeOrder(order);
                    service.saveOrderToDateFile(dateFinder);
                    break;
                case 3:
                    String newProduct = view.editString("Customer Product");
                    order.setProductName(newProduct);
                    service.completeOrder(order);
                    service.saveOrderToDateFile(dateFinder);
                    break;
                case 4:
                    BigDecimal newArea = view.editArea();
                    order.setArea(newArea);
                    service.completeOrder(order);
                    service.saveOrderToDateFile(dateFinder);
                    break;
                case 5:
                    service.completeOrder(order);
                    service.saveOrderToDateFile(order.getOrderDate());
                    view.displayEditSuccess();
                    break;
                default:
                    unknownCommand();
                    break;
            }
        } catch (OrderIdException | IOException ex) {
            view.errorMessage("Order failed to save to date file. Order not saved!");
        } catch (OrderValidationException | StateValidationException | ProductValidationException ex) {
            view.errorMessage("Failed to validate order. All fields must contain valid order data.");
        }
    }

    private void unknownCommand() {
        view.unknownCommand();
    }

    private int getSubSelection() {
        return view.getSubAndPrintSelect();
    }

}
