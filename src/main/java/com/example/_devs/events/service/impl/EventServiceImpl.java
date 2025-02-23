package com.example._devs.events.service.impl;

import com.example._devs.events.model.Event;
import com.example._devs.events.repository.EventRepository;
import com.example._devs.events.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    EventRepository eventRepository;

    @Override
    public Event addNewEvent(Event event) {
        event.setPrettyName(event.getTitle().toLowerCase().replaceAll(" ", "-"));
        return eventRepository.save(event);
    }

    @Override
    public List<Event> getAllEvents() {
        return (List<Event>) eventRepository.findAll();
    }

    @Override
    public Event getByPrettyName(String prettyName) {
        return eventRepository.findByPrettyName(prettyName);
    }
}
