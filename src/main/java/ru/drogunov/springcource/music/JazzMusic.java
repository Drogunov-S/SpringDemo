package ru.drogunov.springcource.music;

import org.springframework.stereotype.Component;
import ru.drogunov.springcource.Music;

import java.util.ArrayList;
import java.util.List;

public class JazzMusic implements Music {
    private final List<String> songs = new ArrayList<>();
    
    public JazzMusic() {
        songs.add("Jazz music");
        songs.add("Juli Jazz");
        songs.add("Jazzy");
    }
    
    @Override
    public List<String> getSongs() {
        return songs;
    }
}
