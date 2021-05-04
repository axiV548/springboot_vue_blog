package com.axiv548.util;

import lombok.Data;

/**
 * AjaxResponse
 *
 * @date 2021/2/26 16:12
 */

@Data
public class AjaxResponse {

    private int code;
    private String message;
    private Object data;
    private String other;

    public AjaxResponse() {}

    public static AjaxResponse success(Object obj, String msg) {
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setCode(200);
        ajaxResponse.setMessage(msg);
        ajaxResponse.setData(obj);
        return ajaxResponse;
    }

    public static AjaxResponse success(Object obj, String msg, String other) {
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setCode(200);
        ajaxResponse.setMessage(msg);
        ajaxResponse.setData(obj);
        ajaxResponse.setOther(other);
        return ajaxResponse;
    }

    public static AjaxResponse success(String msg) {
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setCode(200);
        ajaxResponse.setMessage(msg);
        return ajaxResponse;
    }


    public static AjaxResponse success(int code, String msg) {
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setCode(code);
        ajaxResponse.setMessage(msg);
        return ajaxResponse;
    }


}
