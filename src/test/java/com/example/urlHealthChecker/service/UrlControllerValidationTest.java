package com.example.urlHealthChecker.service;

import com.example.urlHealthChecker.controller.UrlController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UrlController.class)
public class UrlControllerValidationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UrlService urlService;

    @Test
    @DisplayName("Hatalı URL formatı gönderildiğinde 400 Bad Request dönmeli")
    void whenInvalidUrl_thenReturns400() throws Exception {
        String invalidJson = "{\"url\": \"bu-bir-url-degil\"}";
        mockMvc.perform(post("/api/urls")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Boş URL gönderildiğinde 400 Bad Request dönmeli")
    void whenEmptyUrl_thenReturns400() throws Exception {
        String emptyJson = "{\"url\": \"\"}";

        mockMvc.perform(post("/api/urls")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(emptyJson))
                .andExpect(status().isBadRequest());
    }
}
