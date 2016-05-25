package com.icap.primecalc.controller;

import com.icap.primecalc.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
public class PrimeServiceControllerTestIT {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testRequestWithDefaultMediaType() throws Exception {
        String expectedJson = "{\"Initial\":5,\"Primes\":[2,3,5]}";

        mockMvc
                .perform(get("/primes/5"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));
    }

    @Test
    public void testJsonRequestResponse() throws Exception {
        String expectedJson = "{\"Initial\":10,\"Primes\":[2,3,5,7]}";

        mockMvc
                .perform(get("/primes/10").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));
    }

    @Test
    public void testXmlRequestResponse() throws Exception {
        String expectedXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
                + "<primesResponse initial=\"10\" primes=\"2 3 5 7\"/>";

        mockMvc
                .perform(get("/primes/10").accept(MediaType.APPLICATION_XML))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_XML))
                .andExpect(content().xml(expectedXml));
    }

    @Test
    public void testJsonRequestResponse2AsInput() throws Exception {
        String expectedJson = "{\"Initial\":2,\"Primes\":[2]}";

        mockMvc
                .perform(get("/primes/2").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));
    }

    @Test
    public void testInvalidRequest() throws Exception {
        mockMvc
                .perform(get("/primes/10a").accept(MediaType.APPLICATION_XML))
                .andExpect(status().is(400));
    }

    @Test
    public void testInvalidRequestWithoutLimit() throws Exception {
        mockMvc
                .perform(get("/primes").accept(MediaType.APPLICATION_XML))
                .andExpect(status().is(404));
    }

    @Test
    public void testInvalidRequestWithNegativeInput() throws Exception {
        mockMvc
                .perform(get("/primes/-1").accept(MediaType.APPLICATION_XML))
                .andExpect(status().is(400));
    }

    @Test
    public void testInvalidRequestWithInvalidInput() throws Exception {
        mockMvc
                .perform(get("/primes/1").accept(MediaType.APPLICATION_XML))
                .andExpect(status().is(400));
    }

    @Test
    public void testInvalidRequestWithInvalidInputMaxInt() throws Exception {
        long overMaxInt = Integer.MAX_VALUE + 1L;
        mockMvc
                .perform(get("/primes/" + overMaxInt).accept(MediaType.APPLICATION_XML))
                .andExpect(status().is(400));
    }

}