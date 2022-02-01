package com.example.task3.api;

import com.example.task3.model.Ticket;
import com.example.task3.service.TicketService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TicketApplicationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper jackson;
    @Autowired
    private TicketService ticketService;


    @BeforeEach
    public void setup() {
        ticketService.setTickets(
            new ArrayList<>(
                Arrays.asList(
                    new Ticket(1245, "2017-09-01 19:20", 0),
                    new Ticket(1246, "2017-09-01 15:42", 1),
                    new Ticket(1250, "2017-09-01 16:32", 2)
                )
            )
        );
    }

    @Test
    public void getTicketsTest() throws Exception {
        String response = mockMvc.perform(get("/tickets"))
            .andExpect(status().isOk())
            .andReturn().getResponse().getContentAsString();

        List<Ticket> tickets = jackson.readValue(response, new TypeReference<>() {});
        assertThat(tickets).hasSize(3);

        checkTicketHasValues(tickets.get(0), 1245, "2017-09-01 19:20", 0);
        checkTicketHasValues(tickets.get(1), 1246, "2017-09-01 15:42", 1);
        checkTicketHasValues(tickets.get(2), 1250, "2017-09-01 16:32", 2);
    }

    @Test
    public void addNewTicketTest() throws Exception {
        String response = mockMvc.perform(post("/tickets"))
            .andExpect(status().isOk())
            .andReturn().getResponse().getContentAsString();

        Ticket ticket = jackson.readValue(response, Ticket.class);
        assertThat(ticket.getTicketId()).isEqualTo(1251);
        assertThat(ticket.getDateTime()).isNotNull();
        assertThat(ticket.getOrder()).isEqualTo(3);

        List<Ticket> tickets = ticketService.getAll();
        assertThat(tickets).hasSize(4);
    }

    @Test
    public void getActualTicketTest() throws Exception {
        String response = mockMvc.perform(get("/tickets/actual"))
            .andExpect(status().isOk())
            .andReturn().getResponse().getContentAsString();

        Ticket ticket = jackson.readValue(response, Ticket.class);
        checkTicketHasValues(ticket, 1245, "2017-09-01 19:20", 0);
    }

    @Test
    public void deleteLastTicketTest() throws Exception {
        mockMvc.perform(delete("/tickets"))
            .andExpect(status().isOk());

        List<Ticket> tickets = ticketService.getAll();
        assertThat(tickets).hasSize(2);
        checkTicketHasValues(tickets.get(0), 1245, "2017-09-01 19:20", 0);
        checkTicketHasValues(tickets.get(1), 1246, "2017-09-01 15:42", 1);
    }

    private void checkTicketHasValues(Ticket ticket, int ticketId, String date, int order) {
        assertThat(ticket.getTicketId()).isEqualTo(ticketId);
        assertThat(ticket.getDateTime()).isEqualTo(date);
        assertThat(ticket.getOrder()).isEqualTo(order);
    }
}
