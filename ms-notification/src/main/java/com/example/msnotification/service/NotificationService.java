package com.example.msnotification.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class NotificationService {

    @Value("${TWILIO_ACCOUNT_SID}")
    String Sid;

    @Value("${TWILIO_AUTH_TOKEN}")
    String authToken;

    @Value("${TWILIO_PHONE_NUMBER}")
    String fromNumber;


    @PostConstruct
    private void  setup(){
        Twilio.init(Sid,authToken);

    }

    public String sendSms(String smsNumber,String smsMessage){

        Message message =
                Message.creator(
                        new PhoneNumber(smsNumber),
                        new PhoneNumber(fromNumber),
                        smsMessage).create();
        return message.getStatus().toString();
    }

}
