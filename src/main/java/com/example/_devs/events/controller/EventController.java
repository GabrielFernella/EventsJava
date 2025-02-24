package com.example._devs.events.controller;

import com.example._devs.events.exception.EventNotFoundException;
import com.example._devs.events.model.Event;
import com.example._devs.events.service.EventService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@Tag(name = "Events", description = "Endpoints para gerenciamento dos events")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/")
    public Event addNewEvent(@RequestBody Event event){
        return eventService.addNewEvent(event);
    }

    @GetMapping("/")
    public List<Event> getAllElements(){
        return eventService.getAllEvents();
    }

    @GetMapping("/{prettyName}")
    public ResponseEntity<Event> getEventByPrettyName(@PathVariable String prettyName){
        Event event = eventService.getByPrettyName(prettyName);
        if(event !=null){
            return ResponseEntity.ok().body(event);
        }
//        return ResponseEntity.notFound().build();
        throw new EventNotFoundException("Event not found");

    }
}
