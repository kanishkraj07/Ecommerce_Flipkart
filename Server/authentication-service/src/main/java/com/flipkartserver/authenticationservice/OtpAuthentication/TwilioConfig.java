package com.flipkartserver.authenticationservice.OtpAuthentication;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
@ConfigurationProperties(prefix = "twilio")
public class TwilioConfig {
    private String accountSid, authToken, phoneNumber;

    public TwilioConfig(String accountSid, String authToken, String phoneNumber) {
        this.accountSid = accountSid;
        this.authToken = authToken;
        this.phoneNumber = phoneNumber;
    }

    public TwilioConfig() {
    }

    public String getAccountSid() {
        return accountSid;
    }

    public void setAccountSid(String accountSid) {
        this.accountSid = accountSid;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
