package com.example.task3.config;

import com.example.task3.model.Ticket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;

@Configuration
public class TicketsConfig {

    @Bean
    public ArrayList<Ticket> tickets() {
        return new ArrayList<>(
            Arrays.asList(
                new Ticket(1245, "2017-09-01 19:20", 0),
                new Ticket(1246, "2017-09-01 15:42", 1),
                new Ticket(1250, "2017-09-01 16:32", 2)
            )
        );
    }
}
