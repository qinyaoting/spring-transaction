package org.pavlov.springapp.mvc.javascriptex1.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pavlov.springapp.mvc.javascriptex1.model.EnergyMeter;
import org.pavlov.springapp.mvc.javascriptex1.repository.EnergyMeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class AppTests {
    private MockMvc mockMvc;
    private EnergyMeter energyMeter;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    protected EnergyMeterRepository energyMeterRepository;


    @Before
    public void setup() {
        energyMeter = new EnergyMeter();
        energyMeter.setId(0);
        energyMeter.setEnergyMeterName("NP73E.1-11-1");
        energyMeter.setPhaseNumber(3);

        //this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void simple() throws Exception {

        energyMeterRepository.insert(energyMeter);

//        mockMvc.perform(get("/"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("hello"));
    }
}
