package com.example.task3.api;

import com.example.task3.model.Ticket;
import com.example.task3.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/tickets")
    public ResponseEntity<List<Ticket>> tickets() {
        return ResponseEntity.ok(ticketService.getAll());
    }

    @PostMapping("/tickets")
    public ResponseEntity<Ticket> addTicket() {
        return ResponseEntity.ok(ticketService.addNew());
    }

    @GetMapping("/tickets/actual")
    public ResponseEntity<Ticket> getActual() {
        return ResponseEntity.ok(ticketService.getActual());
    }

    @DeleteMapping("/tickets")
    public ResponseEntity deleteLast() {
        ticketService.deleteLast();
        return ResponseEntity.ok().build();
    }
}
