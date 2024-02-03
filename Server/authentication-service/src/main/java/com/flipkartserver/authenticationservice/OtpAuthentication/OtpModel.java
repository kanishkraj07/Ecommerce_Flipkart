package com.flipkartserver.authenticationservice.OtpAuthentication;

import org.springframework.stereotype.Component;

@Component
public class OtpModel {
    private long mobile_no;
    private int otp_no;

    public OtpModel(long mobile_no, int otp_no) {
        this.mobile_no = mobile_no;
        this.otp_no = otp_no;
    }

    public OtpModel() {
    }

    public long getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(long mobile_no) {
        this.mobile_no = mobile_no;
    }

    public int getOtp_no() {
        return otp_no;
    }

    public void setOtp_no(int otp_no) {
        this.otp_no = otp_no;
    }
}
