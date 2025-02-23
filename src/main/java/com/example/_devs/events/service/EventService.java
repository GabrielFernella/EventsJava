package com.example._devs.events.service;

import com.example._devs.events.model.Event;

import java.util.List;

public interface EventService {

    Event addNewEvent(Event event);

    List<Event> getAllEvents();

    Event getByPrettyName(String prettyName);

}
