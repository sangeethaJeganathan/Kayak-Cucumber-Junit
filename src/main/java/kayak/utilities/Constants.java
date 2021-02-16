package kayak.utilities;

import org.apache.log4j.Logger;

public class Constants {

    Logger log = Logger.getLogger(getClass().getSimpleName());

    public String getKayakUrl()
    {
        String sURL ="https://www.kayak.com/";
        log.info("The Web application is "+sURL);
        return sURL;
    }

}
