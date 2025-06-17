package com.example.demo.controller;

import com.example.demo.WebSecurityConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for the {@link OrderController}
 */
@Import(WebSecurityConfiguration.class)
@WebMvcTest({OrderController.class})
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {

    }

    @Test
    public void getAll() throws Exception {
        mockMvc.perform(get("/orders/allOrders")
                        .param("pageNumber", "0")
                        .param("pageSize", "0")
                        .param("sort", "")
                        .with(csrf())
                        .with(user("user").password("user").roles("USER")))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
