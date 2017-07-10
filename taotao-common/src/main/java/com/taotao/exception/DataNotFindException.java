package com.taotao.exception;

/**
 * Created by ${chenlunwei} on 2017/5/12.
 */
public class DataNotFindException extends TaotaoException{

    public DataNotFindException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataNotFindException(String message) {
        super(message);
    }
}
