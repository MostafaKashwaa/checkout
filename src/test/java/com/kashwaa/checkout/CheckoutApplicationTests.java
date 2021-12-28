package com.kashwaa.checkout;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CheckoutApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void checkoutWithNoBodyReturnsCode_400() throws Exception {
        this.mockMvc.perform(post("/checkout"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(400))
                .andReturn();
    }

    @Test
    void checkoutRespondsWith_200_ForValidBasket() throws Exception {
        this.mockMvc
                .perform(post("/checkout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createBasketJson(150, true)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(200));
    }

    @Test
    void checkoutRespondsWith_422_ForInvalidBasket() throws Exception {
        this.mockMvc
                .perform(post("/checkout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createBasketJson(10, true)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(422));

    }

    private String createBasketJson(int totalPrice, boolean allAvailable) {
        return "{" +
                "    \"items\": [" +
                "        {" +
                "            \"name\": \"item1\"," +
                "            \"price\": 15.99," +
                "            \"available\": true" +
                "        }," +
                "        {" +
                "            \"name\": \"item2\"," +
                "            \"price\": " + (totalPrice - 15.99) + "," +
                "            \"available\": " + allAvailable +
                "        }" +
                "    ]" +
                "}";
    }
}
