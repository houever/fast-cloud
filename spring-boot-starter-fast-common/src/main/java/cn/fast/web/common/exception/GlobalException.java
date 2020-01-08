package cn.fast.web.common.exception;

import cn.fast.web.common.result.ResultEnum;
import lombok.Data;

@Data
public class GlobalException extends RuntimeException {

    private ResultEnum resultEnum;

    public GlobalException(ResultEnum resultEnum) {
        this.resultEnum = resultEnum;
    }
}
