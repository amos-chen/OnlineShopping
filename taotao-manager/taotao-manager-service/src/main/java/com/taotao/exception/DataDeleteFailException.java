package com.taotao.exception;

/**
 * Created by ${chenlunwei} on 2017/5/12.
 */
public class DataDeleteFailException extends TaotaoException{

    public DataDeleteFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataDeleteFailException(String message) {
        super(message);
    }
}
