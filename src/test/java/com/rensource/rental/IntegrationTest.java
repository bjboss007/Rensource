package com.rensource.rental;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class IntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void testStatus() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/videos/"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andReturn();

        Assert.assertEquals("application/json",
                mvcResult.getResponse().getContentType());

        Assert.assertEquals(200,
                mvcResult.getResponse().getStatus());

    }

    @Test
    public void testGetVideo() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/videos/{id}", 1L))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.genre").value("Drama"))
                .andReturn();

        Assert.assertEquals("application/json",
                mvcResult.getResponse().getContentType());

        Assert.assertEquals(200,
                mvcResult.getResponse().getStatus());

    }

}
