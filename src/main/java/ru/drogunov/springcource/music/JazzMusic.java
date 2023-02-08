package ru.drogunov.springcource.music;

import ru.drogunov.springcource.Music;

public class JazzMusic implements Music {
    private JazzMusic() {
        System.out.println("constructor");
    }
    
    public static JazzMusic get() {
        System.out.println("static");
        return new JazzMusic();
    }
    
    @Override
    public String getSong() {
        return "Jazz music";
    }
}
