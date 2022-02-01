package com.example.task3.service;

import com.example.task3.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    private ArrayList<Ticket> tickets;

    @Autowired
    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<Ticket> getAll() {
        return tickets;
    }

    public Ticket addNew() {
        Ticket newTicket = new Ticket(
            getNextTicketId(),
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
            tickets.size()
        );
        tickets.add(newTicket);
        return newTicket;
    }

    public Ticket getActual() {
        return tickets.get(0);
    }

    public void deleteLast() {
        tickets.remove(tickets.size() - 1);
    }

    private int getNextTicketId() {
        return !tickets.isEmpty() ? tickets.get(tickets.size() - 1).getTicketId() + 1 : 1;
    }
}
