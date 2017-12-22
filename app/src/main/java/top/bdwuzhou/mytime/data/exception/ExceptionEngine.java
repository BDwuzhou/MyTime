package top.bdwuzhou.mytime.data.exception;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.text.ParseException;

import retrofit2.HttpException;

/**
 * @author wuzhou
 * @date 2017/12/8
 */

public class ExceptionEngine {
    private static final int INPUT_INVALID = 400;
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public static Throwable handelException(Throwable cause) {
        APIException exception;
        if (cause instanceof HttpException) {
            HttpException httpException = (HttpException) cause;
            exception = new APIException(httpException, httpException.code());
            switch (httpException.code()) {
                case INPUT_INVALID:
                    exception.setMessage("参数有误");
                    break;
                case UNAUTHORIZED:
                    exception.setMessage("登录过期或未登录");
                    break;
                case FORBIDDEN:
                    exception.setMessage("账号或密码错误");
                    break;
                case NOT_FOUND:
                    exception.setMessage("地址找不到");
                    break;
                case REQUEST_TIMEOUT:
                    exception.setMessage("请求超时");
                    break;
                case INTERNAL_SERVER_ERROR:
                case SERVICE_UNAVAILABLE:
                    exception.setMessage("服务器暂时不可用");
                    break;
                case BAD_GATEWAY:
                case GATEWAY_TIMEOUT:
                    exception.setMessage("网络错误");
                    break;
                default:
                    break;
            }
        } else if (cause instanceof JSONException || cause instanceof JsonParseException || cause instanceof
                ParseException) {
            exception = new APIException(cause, 0);
            exception.setMessage("解析错误");
        } else {
            exception = new APIException(cause, -1);
            exception.setMessage("未知错误");
        }
        return exception;
    }
}
