package club.anlan.lanlife.lanapp.domain;


/**
 * 请求结果
 *
 * @author lan
 * @version 1.0
 * @date 2021/4/17 16:28
 */
public class ResultMessage<T> {

    /**
     * 成功与否
     */
    private boolean success;

    /**
     * 结果编码
     */
    private String code;

    /**
     * 返回数据
     */
    private String errMsg;

    /**
     * 返回结果数据
     */
    private T data;

    public ResultMessage() {
    }

    public ResultMessage(boolean success, String code, String errMsg, T data) {
        this.success = success;
        this.code = code;
        this.errMsg = errMsg;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
