/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thi.bt.knn.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author sangnk
 */

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends Exception  {
    public BadRequestException(
            String message
    ) {
        super(message);
    }
}
