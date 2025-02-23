package com.example._devs.events.service.impl;

import com.example._devs.events.dto.SubscriptionResponse;
import com.example._devs.events.exception.EventNotFoundException;
import com.example._devs.events.exception.SubscriptionConflictException;
import com.example._devs.events.exception.UserIndicatorNotFoundException;
import com.example._devs.events.model.Event;
import com.example._devs.events.model.Subscription;
import com.example._devs.events.model.User;
import com.example._devs.events.repository.EventRepository;
import com.example._devs.events.repository.SubscriptionRepository;
import com.example._devs.events.repository.UserRepository;
import com.example._devs.events.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    public SubscriptionResponse createNewSubscription(String eventName, User user, Integer userId) {
        Event evt = eventRepository.findByPrettyName(eventName);
        if(evt != null){
            throw new EventNotFoundException("Evento " + eventName+ " não existe");
        }

        User userRec = userRepository.findByEmail(user.getEmail());
        if(userRec == null){
            userRec = userRepository.save(user);
        }

        User indicador = userRepository.findById(userId).orElse(null);
        if (indicador == null) {
            throw new UserIndicatorNotFoundException("Usuario "+ userId + " indicado não existe");
        }

        Subscription subs = new Subscription();
        subs.setEvent(evt);
        subs.setSubscriber(userRec);
        subs.setIndication(indicador);

        Subscription tmpSub = subscriptionRepository.findByEventAndSubscriber(evt, userRec);
        if(tmpSub != null){
            throw new SubscriptionConflictException("Já existe incrição para o usuário" + userRec.getName() + " no evento "+ evt.getTitle());
        }

        Subscription res = subscriptionRepository.save(subs);
        return new SubscriptionResponse(res.getSubscriptionNumber(), "http://4devs.com/subscription/"+res.getEvent().getPrettyName()+"/"+res.getSubscriber().getId());
    }
}
