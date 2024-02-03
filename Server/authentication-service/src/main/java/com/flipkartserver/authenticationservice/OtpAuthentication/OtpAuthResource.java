package com.flipkartserver.authenticationservice.OtpAuthentication;

import com.flipkartserver.authenticationservice.jwtauthorization.JwtService;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@EnableConfigurationProperties(TwilioConfig.class)
@Produces(MediaType.APPLICATION_JSON)
@RestController
@RequestMapping("/otp")
public class OtpAuthResource {

    private OtpAuthService otpAuthService;
    private OtpAuthDao otpDao;


    OtpAuthResource(OtpAuthService otpAuthService, OtpAuthDao otpDao) {
        this.otpAuthService = otpAuthService;
        this.otpDao = otpDao;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendOtp(@RequestParam("mobileNo") String mobileNo) {
        return otpAuthService.generateAndSendOtp(Long.parseLong(mobileNo));
    }

    @PostMapping("/validate")
    public ResponseEntity<String> validateOtp(@RequestBody OtpModel userEnteredOtp, @RequestParam("isToRegister") Boolean isToRegister) {
        return otpAuthService.validateUserOtp(userEnteredOtp, isToRegister);
    }
}
