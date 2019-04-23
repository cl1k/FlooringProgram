/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringprogram.service;

/**
 * @author Alexm
 */
public class OrderIdException extends Exception {

    public OrderIdException() {
    }

    public OrderIdException(String message) {
        super(message);
    }

    public OrderIdException(String message, Throwable cause) {
        super(message, cause);
    }

}
