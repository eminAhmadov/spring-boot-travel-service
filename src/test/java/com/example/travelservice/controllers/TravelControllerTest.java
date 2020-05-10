package com.example.travelservice.controllers;

import com.example.travelservice.entities.Travel;
import com.example.travelservice.services.TravelService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TravelController.class)
class TravelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TravelService service;

    @Test
    void getAll() throws Exception {
        List<Travel> travels = Arrays.asList(
                new Travel(
                        "c175afac-480e-4d08-8f97-92b822febea6",
                        "Emin Ahmadov",
                        "MALE",
                        "Toronto",
                        "Baku",
                        "One bag",
                        "www.facebook.com/eminAhmadov",
                        "eminAhmadov",
                        "+36202704921",
                        "2020-06-04"
                ), new Travel(
                        "a937e35f-09aa-462c-a6c8-0264f9e153de",
                        "Lala Ahmadova",
                        "FEMALE",
                        "Baku",
                        "Toronto",
                        "Two bags",
                        "www.facebook.com/lalaAhmadova",
                        "lalaAhmadova",
                        "+36202704922",
                        "2020-04-23"
                ));
        when(service.getAll(0, 10)).thenReturn(travels);

        mockMvc.perform(get("/getAll")
                .param("offset", "0")
                .param("limit", "10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].userId").value("c175afac-480e-4d08-8f97-92b822febea6"))
                .andExpect(jsonPath("$[1].userId").value("a937e35f-09aa-462c-a6c8-0264f9e153de"))
                .andExpect(jsonPath("$[0].name").value("Emin Ahmadov"))
                .andExpect(jsonPath("$[1].name").value("Lala Ahmadova"));

        verify(service, times(1)).getAll(0, 10);
        verifyNoMoreInteractions(service);
    }

    @Test
    void getAllForUserWithUserId() throws Exception {
        List<Travel> travels = Arrays.asList(
                new Travel(
                        "c175afac-480e-4d08-8f97-92b822febea6",
                        "Emin Ahmadov",
                        "MALE",
                        "Toronto",
                        "Baku",
                        "One bag",
                        "www.facebook.com/eminAhmadov",
                        "eminAhmadov",
                        "+36202704921",
                        "2020-06-04"
                ));
        when(service.getAllForUserWithUserId(UUID.fromString("c175afac-480e-4d08-8f97-92b822febea6"), 0, 10))
                .thenReturn(travels);

        mockMvc.perform(get("/getAllForUserWithUserId")
                .param("userId", "c175afac-480e-4d08-8f97-92b822febea6")
                .param("offset", "0")
                .param("limit", "10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].userId").value("c175afac-480e-4d08-8f97-92b822febea6"))
                .andExpect(jsonPath("$[0].name").value("Emin Ahmadov"));

        verify(service, times(1))
                .getAllForUserWithUserId(UUID.fromString("c175afac-480e-4d08-8f97-92b822febea6"), 0, 10);
        verifyNoMoreInteractions(service);
    }
}