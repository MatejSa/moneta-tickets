package com.example.task3.model;

public class Ticket {

    int ticketId;
    String dateTime;
    int order;

    public Ticket(int ticketId, String dateTime, int order) {
        this.ticketId = ticketId;
        this.dateTime = dateTime;
        this.order = order;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
