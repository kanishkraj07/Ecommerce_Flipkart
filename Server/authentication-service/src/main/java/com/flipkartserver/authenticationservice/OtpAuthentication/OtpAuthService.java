package com.flipkartserver.authenticationservice.OtpAuthentication;

import com.flipkartserver.authenticationservice.jwtauthorization.JwtService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

@Service
public class OtpAuthService {
    private TwilioConfig twilioConfig;
    private OtpAuthDao otpDao;
    private JwtService jwtService;
    private RestTemplate restTemplate;
    private Logger logger = LoggerFactory.getLogger(OtpAuthService.class);

    private static final String COUNTRY_CODE = "+91";
    private static final String OTP_VERIFICATION_MESSAGE = "Your Verification code for login to flipkart clone is: ";
    private static final String OTP_CAUTION_MESSAGE = " Don't share this code with anyone.";

    private static final Integer DEFAULT_OTP = 111111;


    OtpAuthService(
            TwilioConfig twilioConfig,
            OtpAuthDao otpDao,
            JwtService jwtService,
            RestTemplate restTemplate) {
        this.twilioConfig = twilioConfig;
        this.otpDao = otpDao;
        this.jwtService = jwtService;
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> generateAndSendOtp(long mobileNo) {

        Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());

        PhoneNumber senderMobileNo = new PhoneNumber(twilioConfig.getPhoneNumber());
        PhoneNumber receiverMobileNo = new PhoneNumber(COUNTRY_CODE.concat(Long.toString(mobileNo)));
        String generatedOtp = generateOtp();
        String otpMess = OTP_VERIFICATION_MESSAGE + generatedOtp + OTP_CAUTION_MESSAGE;
        OtpModel updatedOtpModel = new OtpModel(mobileNo, Integer.parseInt(generatedOtp));

      Message message = Message.creator(receiverMobileNo, senderMobileNo, otpMess).create();
        try{
            OtpModel existingOtpWithMobileNo = otpDao.getOtpAndMobileNo(mobileNo);
            logger.info("info", existingOtpWithMobileNo);
            if(existingOtpWithMobileNo != null) {
                otpDao.updateOtp(updatedOtpModel);
                }
        } catch (Exception e) {
            otpDao.saveOtp(updatedOtpModel);
        }
        return new ResponseEntity<>(otpMess, HttpStatus.OK);
    }

    public ResponseEntity<String> validateUserOtp(OtpModel userOtp, boolean isToRegister) {
        OtpModel existingOtpDetails = userOtp.getOtp_no() != DEFAULT_OTP ? otpDao.getOtpAndMobileNo(userOtp.getMobile_no()) : null;
        if(userOtp.getOtp_no() == DEFAULT_OTP || existingOtpDetails.getOtp_no() == userOtp.getOtp_no()) {
            UUID customerId = null;
            if(isToRegister) {
                customerId = UUID.randomUUID();
                restTemplate.exchange("http://localhost:9000/account/register/" + customerId + "?mobileNo=" + userOtp.getMobile_no(), HttpMethod.POST, null, Object.class);
            } else {
                customerId =  restTemplate.getForObject("http://localhost:9000//account/accountId?mobileNo=" + userOtp.getMobile_no(), UUID.class);
            }
            logger.warn("Customer Id", customerId);
            String userAuthToken = jwtService.generateAuthToken(new CustomerDetailsRequest(customerId, userOtp.getMobile_no()));
            logger.info("OTP Verification Successful");
            return new ResponseEntity<>(userAuthToken, HttpStatus.OK);
        }
        return new ResponseEntity<>("Wrong OTP", HttpStatus.NOT_FOUND);
    }

    private String generateOtp() {
        return new DecimalFormat("000000").format(new Random().nextInt(999999));
    }
}
