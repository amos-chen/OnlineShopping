package com.taotao.exception;

/**
 * Created by ${chenlunwei} on 2017/5/12.
 */
public class TaotaoException extends RuntimeException {

    public TaotaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public TaotaoException(String message) {
        super(message);
    }
}
