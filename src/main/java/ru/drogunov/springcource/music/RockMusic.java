package ru.drogunov.springcource.music;

import org.springframework.stereotype.Component;
import ru.drogunov.springcource.Music;

import java.util.List;
import java.util.ArrayList;

@Component
public class RockMusic implements Music {
    private final List<String> songs = new ArrayList<>();
    
    public RockMusic() {
        songs.add("Wind cries Mary");
        songs.add("Linking Park");
        songs.add("Luna");
    }
    
    @Override
    public List<String> getSongs() {
        return songs;
    }
}
