package github.akanemiku.akaneblog.exception;

import github.akanemiku.akaneblog.enums.ErrorEnum;
import lombok.Getter;

@Getter
public class InternalException extends RuntimeException {
    private Integer code;

    public InternalException(){super();}

    public InternalException(ErrorEnum errorEnum){
        super(errorEnum.getMessage());
        this.code = errorEnum.getCode();
    }

    public InternalException(Integer code,String message){
        super(message);
        this.code = code;
    }

}
