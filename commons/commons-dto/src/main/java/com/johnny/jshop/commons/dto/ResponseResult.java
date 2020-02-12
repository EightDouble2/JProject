package com.johnny.jshop.commons.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用数据传输对象
 * <p>
 * Description: 
 * </p>
 *
 * @class ResponseResult
 * @author JohnnyHao
 * @date 2020-02-11 
 */ 
@Data
public class ResponseResult<T> implements Serializable {

    private static final long serialVersionUID = 3468352004150968551L;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 消息
     */
    private String message;

    /**
     * 返回对象
     */
    private T data;

    public ResponseResult() {
        super();
    }

    public ResponseResult(Integer code) {
        super();
        this.code = code;
    }

    public ResponseResult(Integer code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public ResponseResult(Integer code, Throwable throwable) {
        super();
        this.code = code;
        this.message = throwable.getMessage();
    }

    public ResponseResult(Integer code, T data) {
        super();
        this.code = code;
        this.data = data;
    }

    public ResponseResult(Integer code, String message, T data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ResponseResult<?> other = (ResponseResult<?>) obj;
        if (data == null) {
            if (other.data != null) {
                return false;
            }
        } else if (!data.equals(other.data)) {
            return false;
        }
        if (message == null) {
            if (other.message != null) {
                return false;
            }
        } else if (!message.equals(other.message)) {
            return false;
        }
        if (code == null) {
            if (other.code != null) {
                return false;
            }
        } else if (!code.equals(other.code)) {
            return false;
        }
        return true;
    }

    /**
     * 通用状态码
     * <p>
     * Description: 
     * </p>
     *
     * @class ResponseResult
     * @author JohnnyHao
     * @date 2020-02-11 
     */ 
    public enum CodeStatus {
        /*请求成功*/
        OK(20000, "请求成功"),

        /*请求失败*/
        FAIL(20002, "请求失败"),

        /*熔断请求*/
        BREAKING(20004, "熔断请求"),

        /*非法请求*/
        ILLEGAL_REQUEST(50000, "非法请求"),

        /*非法令牌*/
        ILLEGAL_TOKEN(50008, "非法令牌"),

        /*其他客户登录*/
        OTHER_CLIENTS_LOGGED_IN(50012, "其他客户登录"),

        /*令牌已过期*/
        TOKEN_EXPIRED(50014, "令牌已过期");

        private final int value;
        private final String reasonPhrase;

        private CodeStatus(int value, String reasonPhrase) {
            this.value = value;
            this.reasonPhrase = reasonPhrase;
        }

        public int value() {
            return this.value;
        }

        public String getReasonPhrase() {
            return this.reasonPhrase;
        }

        @Override
        public String toString() {
            return this.value + " " + this.name();
        }
    }
}
