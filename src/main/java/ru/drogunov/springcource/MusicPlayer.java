package ru.drogunov.springcource;

import org.springframework.beans.factory.annotation.Value;
import ru.drogunov.springcource.music.Genre;

import java.util.List;
import java.util.Random;

public class MusicPlayer {
    private final List<Music> genreMusicList;
    @Value("${musicPlayer.name}")
    private String name;
    @Value("${musicPlayer.volume}")
    private int volume;
    
    public MusicPlayer(List<Music> genreMusicList) {
        this.genreMusicList = genreMusicList;
    }
    
    public String getName() {
        return name;
    }
    
    public int getVolume() {
        return volume;
    }
    
    public String playMusic() {
        Random random = new Random();
        Music randomGenre = genreMusicList.get(random.nextInt(genreMusicList.size()));
        List<String> songs = randomGenre.getSongs();
        return songs.get(random.nextInt(songs.size()));
    }
    
    public List<Music> getGenreMusicList() {
        return genreMusicList;
    }
}
