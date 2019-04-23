/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringprogram.dao;

/**
 *
 * @author Alexm
 */
public interface FlooringAuditDao {

    void writeAuditEntry(String entry) throws DataPersistenceException;

}
