package com.blueteam.notifier.service;

import com.twilio.sdk.Twilio;
import com.twilio.sdk.creator.api.v2010.account.MessageCreator;
import com.twilio.sdk.resource.api.v2010.account.Message;
import com.twilio.sdk.type.PhoneNumber;
import org.springframework.stereotype.Service;

@Service
public class SmsService {
    private static final String ACCOUNT_SID = "AC2267645b6d40d4a63f869278a6d2daeb";
    private static final String AUTH_TOKEN = "63d7afce40c28b8a698cb33ffb8aa181";
    private static final String TWILIO_NUMBER = "+12055286027";

    public void sendSms(String phoneNumber, String smsText) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        MessageCreator message = Message.create(ACCOUNT_SID,
                new PhoneNumber(phoneNumber),
                new PhoneNumber(TWILIO_NUMBER),
                smsText);
        message.execute();
    }
}
