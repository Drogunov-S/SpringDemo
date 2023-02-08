package ru.drogunov.springcource.music;

import ru.drogunov.springcource.Music;

import java.util.Stack;

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
