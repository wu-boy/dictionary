package cn.wu.demo.dictionary.config;

import java.io.Serializable;

/**
 * @author wusq
 * @date 2020/8/5
 */
public class HttpResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Integer STATUS_CODE_200 = 200;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 结果数据
     */
    private Object data;

    public HttpResult(){
        code = STATUS_CODE_200;
    }

    public HttpResult(Object data){
        code = STATUS_CODE_200;
        this.data = data;
    }

    public HttpResult(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
