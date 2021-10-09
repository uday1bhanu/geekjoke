package com.uday.geekjoke.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class GeekJokeController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static List<String> geekJokes;
    private static Map<String, String> env;

    static {
        geekJokes = new ArrayList<String>() {{
            add("IPV4 WALKS INTO A BAR: GIVE ME A LARGE CIDR, I'M EXHAUSTED!");
            add("QA ENGINEER WALKS INTO A BAR. ORDERS A BEER. ORDERS 0 BEERS. ORDERS 999999999 BEERS. ORDERS A LIZARD. ORDERS -1 BEERS. ORDERS A SFDELJKNESV");
            add("I KNOW A JOKE ABOUT UDP BUT YOU MIGHT NOT GET IT");
            add("THE BOX SAID 'REQUIRES WINDOWS VISTA OR BETTER'. SO I INSTALLED LINUX");
            add("1F U C4N R34D TH1S U R34LLY N33D T0 G37 L41D");
            add("AN INFINITE CROWD OF MATHEMATICIANS ENTERS A BAR. THE FIRST ONE ORDERS A PINT, THE SECOND ONE A HALF PINT, THE THIRD ONE A QUARTER PINT… “I UNDERSTAND”, SAYS THE BARTENDER – AND POURS TWO PINTS");
            add("HAND OVER THE CALCULATOR, FRIENDS DON’T LET FRIENDS DERIVE DRUNK");
            add("REAL MEN DON’T USE BACKUPS, THEY POST THEIR STUFF ON A PUBLIC FTP SERVER AND LET THE REST OF THE WORLD MAKE COPIES");
            add("BUGS COME IN THROUGH OPEN WINDOWS");
        }};
        env = System.getenv();
    }
    /**
     * Get geekjoke
     *
     * @return return random geekjoke
     */
    @RequestMapping(value = "/geekjoke", method = RequestMethod.GET)
    public String geekjoke(){
        Map<String, String> env = System.getenv();
        String dataCenter = env.get("DC_NAME");
        String cluster = env.get("CLUSTER_NAME");
        String verion = env.get("VERSION");
        String geekJoke = dataCenter + "-" + cluster + "-"+verion + "-"+geekJokes.get((int)(Math.random()*(geekJokes.size())));
        logger.info(geekJoke);
        return geekJoke;
    }
}
