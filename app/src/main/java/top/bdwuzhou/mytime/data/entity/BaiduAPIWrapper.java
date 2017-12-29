package top.bdwuzhou.mytime.data.entity;

import lombok.Data;

/**
 * @author wuzhou
 * @date 2017/12/14
 */

@Data
public class BaiduAPIWrapper<T> {
    private int status;
    private T result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
