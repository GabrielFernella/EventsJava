package com.example._devs.events.service;

import com.example._devs.events.dto.SubscriptionResponse;
import com.example._devs.events.model.User;

public interface SubscriptionService {

    public SubscriptionResponse createNewSubscription(String eventName, User user, Integer userId);
}
