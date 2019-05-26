package com.example.demo;

import com.example.demo.controller.EmployeeController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerWebLayerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test()
    public void whenTestMvcController_thenRetrieveExpectedResult() throws Exception {

        // TODO: Create test
        //this.mockMvc.perform();
                //get("/employee").andExpect( );
    }
}
