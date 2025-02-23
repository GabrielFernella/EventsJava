package com.example._devs.events.controller;

import com.example._devs.events.dto.ErrorMessage;
import com.example._devs.events.dto.SubscriptionResponse;
import com.example._devs.events.exception.EventNotFoundException;
import com.example._devs.events.exception.SubscriptionConflictException;
import com.example._devs.events.exception.UserIndicatorNotFoundException;
import com.example._devs.events.model.User;
import com.example._devs.events.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubscriptionController {

    @Autowired
    private SubscriptionService service;

    @PostMapping({"/subscription/{prettyName}", "/subscription/{prettyName}/{userId}"})
    public ResponseEntity<?> createSubscription(@PathVariable String prettyName, @RequestBody User subscriber, @PathVariable(required = false) Integer userId){
        try{
            SubscriptionResponse res = service.createNewSubscription(prettyName, subscriber, userId);
            if (res != null){
                return ResponseEntity.ok(res);
            }

        }catch(EventNotFoundException ex){
            return ResponseEntity.status(404).body(new ErrorMessage(ex.getMessage()));
        }
        catch(SubscriptionConflictException ex){
            return ResponseEntity.status(409).body(new ErrorMessage(ex.getMessage()));
        }
        catch(UserIndicatorNotFoundException ex){
            return ResponseEntity.status(404).body(new ErrorMessage(ex.getMessage()));
        }
        return ResponseEntity.badRequest().build();
    }
}
