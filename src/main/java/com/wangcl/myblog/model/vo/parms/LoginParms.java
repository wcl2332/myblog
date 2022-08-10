package com.wangcl.myblog.model.vo.parms;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

@Data
public class LoginParms {
    private String loginName;
    private String passWord;

}
