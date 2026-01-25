package com.example.kafkapayment.channel;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PaymentChannelFactory {

    private final Map<String, PaymentChannel> channels;

    public PaymentChannelFactory(Map<String, PaymentChannel> channels) {
        this.channels = channels;
    }

    public PaymentChannel getChannel(String channel) {
        return channels.get(channel);
    }
}
