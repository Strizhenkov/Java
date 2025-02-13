package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean(name = "dataBaseUnit")
    public DataBaseUnit getDataBaseUnit() {
        DataBaseUnit dataBaseUnit = new DataBaseUnit("");
        return dataBaseUnit;
    }
}
