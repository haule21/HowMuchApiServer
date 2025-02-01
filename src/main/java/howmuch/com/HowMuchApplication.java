package howmuch.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//@SpringBootApplication
//public class HowMuchApplication extends SpringBootServletInitializer {
//
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(HowMuchApplication.class);
//    }
//
//    public static void main(String[] args) {
//        SpringApplication.run(HowMuchApplication.class, args);
//    }
//}

@SpringBootApplication
public class HowMuchApplication {

    public static void main(String[] args) {
        SpringApplication.run(HowMuchApplication.class, args);
    }
}