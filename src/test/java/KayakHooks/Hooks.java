package KayakHooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import kayak.utilities.TestBase;

public class Hooks extends TestBase {

    @Before
    public void setUpTheBrowser() throws Exception {
        String sBrowserType = "chrome";
        initAllDependents(sBrowserType);
    }

    @After
    public void tearDown()
    {
        closeAllDependents();
    }
}
