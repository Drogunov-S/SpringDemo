package ru.drogunov.springcource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.drogunov.springcource.music.ClassicMusic;
import ru.drogunov.springcource.music.JazzMusic;
import ru.drogunov.springcource.music.RockMusic;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MusicPlayer {
    private final ClassicMusic classicMusic;
    private final RockMusic rockMusic;
    private final JazzMusic jazzMusic;
    
    @Autowired
    public MusicPlayer(ClassicMusic classicMusic, RockMusic rockMusic, JazzMusic jazzMusic) {
        this.classicMusic = classicMusic;
        this.rockMusic = rockMusic;
        this.jazzMusic = jazzMusic;
    }
    
    //    private List<Music> musicList = new ArrayList<>();
    private String name;
    private int volume;
    
    public String playMusic() {
        String playing = "\n Playing: ";
        return playing +
                classicMusic.getSong() +
                playing +
                rockMusic.getSong() +
                playing +
                jazzMusic.getSong();
    }
    
    
    
    /*
    public String playMusic() {
        return musicList.stream()
                .map(music ->  String.format("Playing: %s%n", music.getSong()))
                .collect(Collectors.joining());
    }*/
    
    
    
}
