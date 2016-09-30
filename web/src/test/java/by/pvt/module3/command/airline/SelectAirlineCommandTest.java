package by.pvt.module3.command.airline;

import by.pvt.module3.controller.AirlineController;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
@Transactional
public class SelectAirlineCommandTest {

    Map<String, String> paramMap;
    Model model;
//    private Integer id;

    @Autowired
    AirlineController airlineController;

    @Before
    public void setUp() throws Exception {
        paramMap = new HashMap<String, String>();
        model = new ExtendedModelMap();
        paramMap.put("id", "1");
//        BaseDAO<Airline> daoAirline = new BaseDAO<Airline>(Airline.class);
//        id = daoAirline.add(new Airline());
//        String[] tmp = {id.toString()};
//        request.getParameterMap().put(Airline.ID, tmp);
    }

    @After
    public void tearDown() {
//        BaseDAO<Airline> daoAirline = new BaseDAO<Airline>(Airline.class);
//        if(id != null)
//            daoAirline.delete(id);
    }

    @Test
    @Autowired
    public void testExecute(){
        if (paramMap == null) return;
        String pageExist = (airlineController.perform(paramMap, model));
        String pageWaiting = "/jsp/airlines.jsp";
        Assert.assertEquals(pageExist, pageWaiting);
    }


}