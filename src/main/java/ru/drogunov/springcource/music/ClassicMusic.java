package ru.drogunov.springcource.music;

import org.springframework.stereotype.Component;
import ru.drogunov.springcource.Music;

import java.util.ArrayList;
import java.util.List;

@Component("classicMusic")
public class ClassicMusic implements Music {
    
    
    private final List<String> songs = new ArrayList<>();
    
    public ClassicMusic() {
        songs.add("Hungary Rhapsody");
        songs.add("Classic muzze");
        songs.add("Classic tree");
    }
    
    @Override
    public List<String> getSongs() {
        return songs;
    }
}
