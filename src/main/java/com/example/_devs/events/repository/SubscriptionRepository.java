package com.example._devs.events.repository;

import com.example._devs.events.model.Event;
import com.example._devs.events.model.Subscription;
import com.example._devs.events.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {
    public Subscription findByEventAndSubscriber(Event evt, User user);
}
