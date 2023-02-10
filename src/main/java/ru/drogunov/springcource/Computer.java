package ru.drogunov.springcource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.drogunov.springcource.music.Genre;
import ru.drogunov.springcource.processor.Benchmark;

import java.util.Random;


public class Computer {
    
    private Long id = 1L;
    private final MusicPlayer musicPlayer;
    //No Autowired
    public Computer(MusicPlayer musicPlayer) {
        this.musicPlayer = musicPlayer;
    }
    @Override
    public String toString() {
        Genre[] values = Genre.values();
        Genre genre = values[new Random().nextInt(values.length)];
        
        return "Computer " +
                id +
                ": " + musicPlayer.playMusic();
    }
    
    public MusicPlayer getMusicPlayer() {
        return musicPlayer;
    }
}
