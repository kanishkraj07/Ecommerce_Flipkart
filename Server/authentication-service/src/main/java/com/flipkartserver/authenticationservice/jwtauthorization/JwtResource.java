package com.flipkartserver.authenticationservice.jwtauthorization;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authorize")
public class JwtResource {

    @Autowired
    private JwtService jwtService;

    @GetMapping("/validateToken")
    public String validateAuthToken(@RequestParam("token") String token) throws Exception {
        try{
            jwtService.validateAuthToken(token);
            return "Token Verified";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
