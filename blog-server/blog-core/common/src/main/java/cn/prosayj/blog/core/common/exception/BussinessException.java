package cn.prosayj.blog.core.common.exception;

import cn.prosayj.blog.core.common.constants.enums.ErrorEnum;
import lombok.Data;

/**
 * 自定义异常
 *
 * @author prosayj@gmail.com
 * @date 2020-05-21 下午 01:48
 * @since 1.0.0
 */
@Data
public class BussinessException extends RuntimeException {
    private String msg;
    private int code = 500;

    public BussinessException() {
        super(ErrorEnum.UNKNOWN.getMsg());
        this.msg = ErrorEnum.UNKNOWN.getMsg();
    }


    public BussinessException(ErrorEnum eEnum, Throwable e) {
        super(eEnum.getMsg(), e);
        this.msg = eEnum.getMsg();
        this.code = eEnum.getCode();
    }

    public BussinessException(ErrorEnum eEnum) {
        this.msg = eEnum.getMsg();
        this.code = eEnum.getCode();
    }

    public BussinessException(String exception) {
        this.msg = exception;
    }

}
