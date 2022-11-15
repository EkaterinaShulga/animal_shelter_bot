package pro.sky.java.course6.animal_shelter_bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AnimalShelterBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnimalShelterBotApplication.class, args);
    }

}
