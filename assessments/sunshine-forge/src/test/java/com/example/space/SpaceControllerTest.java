package com.example.space;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.transaction.Transactional;

import java.util.Arrays;

import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by localadmin on 7/20/17.
 */



@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SpaceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SpaceRepository repository;


    //Post
    @Transactional
    @Rollback
    @Test
    public void createNewSpaceReturnsNewSpace() throws Exception {

//        //Setup
//        final SpaceModel space = new SpaceModel();

        final MockHttpServletRequestBuilder request = post("/spaces")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"IdrisCorpSpace\",\"memory\":\"512\",\"disk\":\"15000\"}");


        //exercise
        final ResultActions resultActions = mockMvc.perform(request);

        //assert
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("IdrisCorpSpace")))
                .andExpect(jsonPath("$.memory", is(512)))
                .andExpect(jsonPath("$.disk", is(15000)));
    }


    //List
    @Transactional
    @Rollback
    @Test
    public void listReturnsExistingSpaces() throws Exception {

        //setup
        final SpaceModel space1 = new SpaceModel();
        space1.setName("AliciaCorpSpace");
        space1.setDisk(15000);
        space1.setMemory(512);

        final SpaceModel space2 = new SpaceModel();
        space2.setName("IdrisCorpSpace");
        space2.setDisk(15000);
        space2.setMemory(512);
        //Exercise
        repository.save(Arrays.asList(space1, space2));

        final MockHttpServletRequestBuilder request = get("/spaces");

        final ResultActions resultAction = mockMvc.perform(request);

        resultAction.andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(2)))
                .andExpect(jsonPath("$[0].name", is("AliciaCorpSpace")))
                .andExpect(jsonPath("$[0].memory", is(512)))
                .andExpect(jsonPath("$[0].disk", is(15000)));

    }

    //GetOne
    @Transactional
    @Rollback
    @Test
    public void getReturnsAnExistingSpace() throws Exception {

        //setup
        final SpaceModel space1 = new SpaceModel();
        space1.setName("AliciaCorpSpace");
        space1.setDisk(15000);
        space1.setMemory(512);

        //Exercise
        repository.save(space1);
        Long id1 = space1.getId();

        final MockHttpServletRequestBuilder request = get("/spaces/{id1}", id1);

        final ResultActions resultAction = mockMvc.perform(request);

        resultAction.andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("AliciaCorpSpace")))
                .andExpect(jsonPath("$.memory", is(512)))
                .andExpect(jsonPath("$.disk", is(15000)));

    }

    //GetOne
    @Transactional
    @Rollback
    @Test
    public void updateReturnsAnUpdatedSpaceIfItExists() throws Exception {

        //setup
        final SpaceModel space1 = new SpaceModel();
        space1.setName("AliciaCorpSpace");
        space1.setDisk(15000);
        space1.setMemory(512);

        //Exercise
        repository.save(space1);
        Long id1 = space1.getId();

        final MockHttpServletRequestBuilder request = put("/spaces/{id1}", id1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"AliciasMySpace\",\"memory\":\"600\",\"disk\":\"15000\"}");

        final ResultActions resultAction = mockMvc.perform(request);

        resultAction.andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("AliciasMySpace")))
                .andExpect(jsonPath("$.memory", is(600)))
                .andExpect(jsonPath("$.disk", is(15000)));
    }

    //Delete
    @Transactional
    @Rollback
    @Test
    public void deleteSpaceReturnsOkIfItExists() throws Exception {

        //setup
        final SpaceModel space1 = new SpaceModel();
        space1.setName("AliciaCorpSpace");
        space1.setDisk(15000);
        space1.setMemory(512);

        //Exercise
        repository.save(space1);
        Long id1 = space1.getId();

        final MockHttpServletRequestBuilder request = delete("/spaces/{id1}", id1);

        final ResultActions resultAction = mockMvc.perform(request);

        resultAction.andExpect(status().isOk());

    }

    //Delete
    @Transactional
    @Rollback
    @Test
    public void deleteSpaceReturns404IfItDoesNotExist() throws Exception {

        //setup
        final SpaceModel space1 = new SpaceModel();
        space1.setName("AliciaCorpSpace");
        space1.setDisk(15000);
        space1.setMemory(512);

        //Exercise
        repository.save(space1);

        final MockHttpServletRequestBuilder request = delete("/spaces/100");

        final ResultActions resultAction = mockMvc.perform(request);

        resultAction.andExpect(status().isNotFound());

    }
}