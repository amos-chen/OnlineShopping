package com.taotao.exception;

/**
 * Created by ${chenlunwei} on 2017/5/12.
 */
public class DataInsertFailException extends TaotaoException{

    public DataInsertFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataInsertFailException(String message) {
        super(message);
    }
}
