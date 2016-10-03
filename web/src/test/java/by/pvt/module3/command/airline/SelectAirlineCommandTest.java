package by.pvt.module3.command.airline;

import by.pvt.module3.controller.AirlineController;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class SelectAirlineCommandTest {

    Map<String, String> paramMap = new HashMap<String, String>();
    Model model = new ExtendedModelMap();

    @Autowired
    AirlineController airlineController;

    @BeforeClass
    public static void setUpClass() {
    }

    @Before
    public void setUp() throws Exception {
        paramMap.put("id", "1");
    }

    @After
    public void tearDown() {
        paramMap.clear();
        model.asMap().clear();
    }

    @Test
    public void testExecute(){
        String pageActual = airlineController.perform(paramMap, model);
        String pageExpected = "/jsp/airlines.jsp";
        Assert.assertEquals("AirlineController don't get the expected page", pageExpected, pageActual);
    }


}