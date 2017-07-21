package com.example.app;

import static org.junit.Assert.*;

/**
 * Created by localadmin on 7/20/17.
 */

import com.example.space.SpaceModel;
import com.example.space.SpaceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AppControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SpaceRepository repository;

    @Transactional
    @Rollback
    @Test
    public void createAnAppWithinSpace () throws Exception {

        //Setup
        final SpaceModel space1 = new SpaceModel();
        space1.setName("AliciaCorpSpace");
        space1.setDisk(15000);
        space1.setMemory(512);

        repository.save(space1);

        Long spaceId = space1.getId();



        // pull id from space
        // set space id in end point


        final MockHttpServletRequestBuilder request = post("/spaces/{spaceId}/apps", spaceId)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"appName\":\"HealthApp\",\"diskAllocation\":\"15\",\"memoryAllocation\":\"10\"}");


        //exercise
        final ResultActions resultActions = mockMvc.perform(request);

        //assert
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.appName", is("HealthApp")))
                .andExpect(jsonPath("$.diskAllocation", is(15)))
                .andExpect(jsonPath("$.memoryAllocation", is(10)));

    }


}