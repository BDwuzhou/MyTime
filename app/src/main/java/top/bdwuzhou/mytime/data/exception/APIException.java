package top.bdwuzhou.mytime.data.exception;

/**
 * @author wuzhou
 * @date 2017/12/8
 */

public class APIException extends Exception {
    private int code;
    private String message;

    public APIException(Throwable cause, int code) {
        super(cause);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
