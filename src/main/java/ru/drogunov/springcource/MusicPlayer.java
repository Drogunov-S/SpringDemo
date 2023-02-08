package ru.drogunov.springcource;

import java.util.ArrayList;
import java.util.List;

public class MusicPlayer {
    private List<Music> musicList = new ArrayList<>();
    private String name;
    private int volume;
    
    private MusicPlayer() {}
    
    public List<Music> getMusicList() {
        return musicList;
    }
    
    public void setMusicList(List<Music> musicList) {
        this.musicList = musicList;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getVolume() {
        return volume;
    }
    
    public void setVolume(int volume) {
        this.volume = volume;
    }
    
    public void playMusic() {
        musicList.stream()
                .map(music -> "Playing: " + music.getSong())
                .forEach(System.out::println);
    }
}
