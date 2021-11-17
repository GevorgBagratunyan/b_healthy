package com.blueteam.notifier.service;

import com.twilio.sdk.Twilio;
import com.twilio.sdk.creator.api.v2010.account.MessageCreator;
import com.twilio.sdk.resource.api.v2010.account.Message;
import com.twilio.sdk.type.PhoneNumber;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    public void sendSms(String phoneNumber, String smsText) {
        Twilio.init(TwilioConfigs.ACCOUNT_SID, TwilioConfigs.AUTH_TOKEN);
        MessageCreator message = Message.create(TwilioConfigs.ACCOUNT_SID,
                new PhoneNumber(phoneNumber),
                new PhoneNumber(TwilioConfigs.TWILIO_NUMBER),
                smsText);
        message.execute();
    }
}
