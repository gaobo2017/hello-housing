package com.ejavashop.vo.member;

public class FrontCheckPwdVO {
    private boolean correct;     //支付密码是否正确
    private Integer pwdErrCount; //支付密码输错个数

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public Integer getPwdErrCount() {
        return pwdErrCount;
    }

    public void setPwdErrCount(Integer pwdErrCount) {
        this.pwdErrCount = pwdErrCount;
    }

}
