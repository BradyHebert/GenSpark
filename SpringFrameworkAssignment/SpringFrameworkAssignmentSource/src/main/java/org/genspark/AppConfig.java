package org.genspark;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConfig {
    @Bean("Student")
    public Student Student(){
        List<Phone> ph = new ArrayList<>(List.of(new Phone("(555) 555 - 5555")));
        Address add = new Address("Beaumont", "TX", "United States of America", "77708");
        return new Student(1,"Brady",ph,add);
    }
}
