package ru.drogunov.springcource.music;

import org.springframework.stereotype.Component;
import ru.drogunov.springcource.Music;

import java.util.Stack;

@Component("classicMusic")
public class ClassicMusic implements Music {
    
    static volatile int trigger = 0;
    int id;
    
    ClassicMusic() {
        trigger++;
        id = trigger;
    }
    
    @Override
    public String getSong() {
        return id + " Hungary Rhapsody " + trigger;
    }
}
