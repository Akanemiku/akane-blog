package github.akanemiku.akaneblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AkaneBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(AkaneBlogApplication.class, args);
    }

}
