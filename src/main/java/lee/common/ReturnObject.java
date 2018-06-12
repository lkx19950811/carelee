package lee.common;

/**
 * @author leon
 * @date 2018-06-08 16:54
 * @desc 返回统一类
 */
public class ReturnObject {
    /**
     * 错误代码 返回0为正确
     */
    private Code code;
    /**
     * 返回消息
     */
    private String message;
    /**
     * 返回Object
     */
    private Object object;

    public ReturnObject(Code code, String message, Object object) {
        this.code = code;
        this.message = message;
        this.object = object;
    }
    public ReturnObject(){

    }
    public static ReturnObject re(Code code, String message, Object object){
        return new ReturnObject(code,message,object);
    }
    public static ReturnObject re(Code code, String message){
        ReturnObject object = new ReturnObject();
        object.setCode(code);
        object.setMessage(message);
        return object;
    }
    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
