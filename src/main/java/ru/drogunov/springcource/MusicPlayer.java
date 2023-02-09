package ru.drogunov.springcource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.drogunov.springcource.music.Genre;

import java.util.List;
import java.util.Random;

@Component
@Scope("prototype")
public class MusicPlayer {
    private final Music rockMusic;
    private final Music jazzMusic;
    private final Music classicMusic;
    @Value("${musicPlayer.name}")
    private String name;
    @Value("${musicPlayer.volume}")
    private int volume;
    
    public String getName() {
        return name;
    }
    
    public int getVolume() {
        return volume;
    }
    
    public MusicPlayer(@Qualifier("rockMusic") Music rockMusic,
                       @Qualifier("jazzMusic") Music jazzMusic,
                       @Qualifier("classicMusic") Music classicMusic)
    {
        this.rockMusic = rockMusic;
        this.jazzMusic = jazzMusic;
        this.classicMusic = classicMusic;
    }
    
    public String playMusic(Genre genre) {
        Music musicGenre = getMusicGenre(genre);
        List<String> songs = musicGenre.getSongs();
        System.out.println(genre);
        return songs.get(new Random().nextInt(songs.size()));
    }
    
    private Music getMusicGenre(Genre genre) {
        return switch (genre) {
        case CLASSIC_MUSIC -> classicMusic;
        case JAZZ_MUSIC -> jazzMusic;
        case ROCK_MUSIC -> rockMusic;
        };
        
    }
}
