package com.manli.manli_java.eenum;

public enum ErrorCodeEnum {
    OK(0, "成功"),
    PHONE_NUMBER_FORMAT_ERROR(-1, "手机号格式错误"),
    SEND_VERIFY_CODE_FAIL(-2, "验证码发送失败"),
    MISS_PHONE_NUMBER(-3, "缺少手机号"),
    MISS_TOKEN(-4, "缺少token"),
    REQUIRE_LOGIN(-5, "没有登陆"),
    VERIFY_CODE_ERROR(-6, "验证码错误"),
    ONLY_IMAGES(-7, "只能上传图片"),
    MISS_PARAMETER(-8, "缺少参数"),
    PHONE_NUMBER_NO_REGISTER(-9, "手机号没有注册"),
    MISS_BAD_EFFECT_ID(-10, "缺少不良反应id"),
    FILE_NOT_EXIST(-11, "文件不存在，无法删除"),
    MEDICINDE_PLANT_NOT_EXIST(-12, "不存在此服药计划"),
    MISS_BCRABL_IS(-13, "缺少bcr_abl的日期范围和is值"),
    TKI_DATE_FORMAT_ERROR(-14, "tki服药日期格式错误"),
    BCSABL_FORMAT_ERROR(-15, "bcs_abl日期格式错误"),
    MISS_CHECK_DATE(-16, "上传缺少检查日期参数"),
    MEDICINE_SIGN_NOT_EXIST(-17, "不存在此服药签到"),
    ONLY_ONE_MEDICINE_PLANT_PER_MEDICINE(-18, "同一种药只能添加一种服药计划"),
    REQUIRE_MAIN_MEDINCE_PLAN(-19, "用户必须设置至少一个主服药计划"),
    PARSE_JSON_ERROR(-20, "JSON数据解析失败"),
    MEDICINE_PLAN_DATE_ERROR(-21, "服药计划和服药时间设置时间不匹配"),
    MISS_SEX_BIRTHDAY_TEL_NAME(-22, "请填写性别,出生日期,联系电话,姓名"),
    MISS_CONFIRM_DATE_CML_FIRST_DATE(-23, "请填写确诊时间,cml期,首次服药时间,是否合并化疗"),
    MISS_TKI_INFO(-24, "请填写tki相关信息"),
    MISS_SUGGESTION_CONTENT(-25, "请填写建议内容"),
    SUGGESTION_NOT_EXIST(-26, "找不到此建议"),
    CANNOT_DEL_SUGGESTION_NOT_FOR_YOU(-27, "不能删除非本人提交的建议"),
    INVALID_NEWS_ID(-28, "非法资讯id"),
    CANNOT_DEL_ELECTRONIC_RECORD_NOT_FOR_YOU(-29, "不能删除非本人提交的记录"),
    INVALID_ELECTRONIC_RECORD_GROUP_ID(-30, "非法电子档案groupId"),
    NOT_MEMBER(-31, "用户不是会员"),
    MEMEBER_HISTORY_SUMMARY_ID_ERROR(-32, "病史小结summaryId与会员名不匹配"),
    MEMEBER_FOLLOWUP_PLAN_ID_ERROR(-33, "随访计划与会员名不匹配"),
    PRIMARY_MEDICINE_DATE_NOT_SET(-34, "用户还未设置主服药计划"),
    TKI_VALUE_ERROR(-35, "tki的值从以下值中选择:尼洛替尼, 达沙替尼 ,伊马替尼"),
    TKI_LABEL_ERROR(-36, "tkiLabel的值从以下值中选择:尼洛替尼(达希纳), 达沙替尼(依尼舒，施达赛), 伊马替尼(格尼可，格列卫，昕维)");


    private int    error;
    private String errmsg;

    private ErrorCodeEnum(int error, String errmsg) {
        this.error = error;
        this.errmsg = errmsg;
    }

    public int getError() {
        return error;
    }

    public String getErrmsg() {
        return errmsg;
    }


}


