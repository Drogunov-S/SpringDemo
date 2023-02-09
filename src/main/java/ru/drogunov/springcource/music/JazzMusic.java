package ru.drogunov.springcource.music;

import org.springframework.stereotype.Component;
import ru.drogunov.springcource.Music;
@Component
public class JazzMusic implements Music {
    private JazzMusic() {
        System.out.println("Music constructor");
    }
    
    public static JazzMusic get() {
        System.out.println("static Music method");
        return new JazzMusic();
    }
    
    @Override
    public String getSong() {
        return "Jazz music";
    }
}
