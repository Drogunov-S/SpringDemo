package ru.drogunov.springcource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Computer {
    
    private Long id = 1L;
    private final MusicPlayer musicPlayer;
    //No Autowired
    public Computer(MusicPlayer musicPlayer) {
        this.musicPlayer = musicPlayer;
    }
    
    @Override
    public String toString() {
        return "Computer " +
                id +
                ": " + musicPlayer.playMusic();
    }
}
