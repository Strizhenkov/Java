package org.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App
{
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        DataBaseUnit dataBaseUnit = (DataBaseUnit) context.getBean("dataBaseUnit");
        DataStructure data = new DataStructure();
        data.LoadCatData(dataBaseUnit.ConnectAndGetCatData());
        data.LoadUserData(dataBaseUnit.ConnectAndGetUserData());
    }
}
