package com.bbpl.server2.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bbpl.server2.model.Operation;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OperationControllerTest {


    @Autowired
    private MockMvc mvc;
    private JSONObject model;

    @Before
    public void setup() throws Exception {
        model = new JSONObject();
        model.put("operation", Operation.ADD.toString());
        JSONArray data = new JSONArray();
        data.put(1);
        data.put(2);
        model.put("data", data);
    }

    @Test
    public void testProcess() throws Exception {
        String result = mvc.perform(
                post("/operation/process").content(model.toString()).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        JSONObject jsonResult = new JSONObject(result);

        Assert.assertEquals("Should return result of 3", 3, jsonResult.getInt("result"));
    }

    @Test
    public void testProcessEmptyRequestPayload() throws Exception {
        mvc.perform(
                post("/operation/process").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testProcessWrongMediaType() throws Exception {
        mvc.perform(
                post("/operation/process").content(model.toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    public void testProcessWrongMethod() throws Exception {
        mvc.perform(
                get("/operation/process").content(model.toString()).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }

}