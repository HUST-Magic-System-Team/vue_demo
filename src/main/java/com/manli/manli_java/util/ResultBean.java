package com.manli.manli_java.util;

import com.manli.manli_java.eenum.ErrorCodeEnum;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ResultBean implements Serializable {
    public static final ResultBean OK = new ResultBean(new HashMap<>());
    private static final long   serialVersionUID = 1L;
    private static final int    SUCCESS          = 0;
    private static final String SUCCESS_MSG      = "";
    //返回码
    public int error = 0;

    //消息提示
    public String errmsg = "";

    //数据
    public Map<String, Object> data = new HashMap<>();

    public ResultBean(Map<String, Object> data) {
        super();

        this.error = ResultBean.SUCCESS;
        this.errmsg = ResultBean.SUCCESS_MSG;
        this.data = data;
    }

    public ResultBean(ErrorCodeEnum eenum) {
        super();
        this.error = eenum.getError();
        this.errmsg = eenum.getErrmsg();
        this.data = new HashMap<>();
        ;
    }

    public ResultBean(int error, String errmsg, Map<String, Object> data) {
        this.error = error;
        this.errmsg = errmsg;
        this.data = data;
    }

}
