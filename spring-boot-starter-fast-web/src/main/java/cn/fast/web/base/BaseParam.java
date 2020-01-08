package cn.fast.web.base;

import lombok.Data;

@Data
public class BaseParam {

    private String sort;
    private String order;
    private String startTime;
    private String endTime;
    private Long current;
    private Long size;

}
