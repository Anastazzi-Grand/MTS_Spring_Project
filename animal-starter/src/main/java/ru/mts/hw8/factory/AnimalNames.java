package ru.mts.hw8.factory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class AnimalNames {
    @Value("${animal.cat.names}")
    private String[] catNames;
    @Value("${animal.dog.names}")
    private String[] dogNames;
    @Value("${animal.wolf.names}")
    private String[] wolfNames;
    @Value("${animal.shark.names}")
    private String[] sharkNames;
    private Random random = new Random();
    public String getDogName(){
        return dogNames[random.nextInt(dogNames.length)];
    }
    public String getCatName(){
        return catNames[random.nextInt(catNames.length)];
    }
    public String getWolfName(){
        return wolfNames[random.nextInt(wolfNames.length)];
    }
    public String getSharkName(){
        return sharkNames[random.nextInt(sharkNames.length)];
    }
}
