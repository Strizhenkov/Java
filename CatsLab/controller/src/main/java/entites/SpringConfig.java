package entites;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean(name = "controllerUnit")
    public ControllerUnit getControllerUnit() {
        ControllerUnit controllerUnit = new ControllerUnit("");
        controllerUnit.LoadCommand("");
        return controllerUnit;
    }
}
