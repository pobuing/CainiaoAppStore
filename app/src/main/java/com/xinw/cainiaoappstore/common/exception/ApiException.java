package com.xinw.cainiaoappstore.common.exception;

/**
 * byD9ing on 2017/8/16.
 * Describe:
 * good luck
 */

public class ApiException extends BaseException {
    public ApiException(int code, String displayMessage) {
        super(code, displayMessage);
    }
}
