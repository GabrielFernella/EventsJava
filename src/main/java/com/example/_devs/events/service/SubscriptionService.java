package com.example._devs.events.service;

import com.example._devs.events.dto.SubscriptionRankingByUser;
import com.example._devs.events.dto.SubscriptionRankingItem;
import com.example._devs.events.dto.SubscriptionResponse;
import com.example._devs.events.model.User;

import java.util.List;

public interface SubscriptionService {

    public SubscriptionResponse createNewSubscription(String eventName, User user, Integer userId);

    public List<SubscriptionRankingItem> getCompleteRanking(String prettyName);

    public SubscriptionRankingByUser getRankingByUser(String prettyName, Integer userId);
}
