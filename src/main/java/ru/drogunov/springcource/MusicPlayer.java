package ru.drogunov.springcource;

import org.springframework.beans.factory.annotation.Value;
import ru.drogunov.springcource.music.Genre;
import ru.drogunov.springcource.processor.Benchmark;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Random;

public class MusicPlayer {
    private List<Music> genreMusicList;
    @Value("${musicPlayer.name}")
    private String name;
    @Value("${musicPlayer.volume}")
    private int volume;
    
    public MusicPlayer() {
    }
    
    public MusicPlayer(List<Music> genreMusicList) {
        this.genreMusicList = genreMusicList;
    }
    
    public String getName() {
        return name;
    }
    
    public int getVolume() {
        return volume;
    }
    
    @Benchmark
    public String playMusic() {
        Random random = new Random();
        Music randomGenre = genreMusicList.get(random.nextInt(genreMusicList.size()));
        List<String> songs = randomGenre.getSongs();
        return songs.get(random.nextInt(songs.size()));
    }
    
    public List<Music> getGenreMusicList() {
        return genreMusicList;
    }
    
    @PostConstruct
    public void init() {
        System.out.println("+ Init " + this.getClass().getSimpleName());
    }
    
    @PreDestroy
    public void destroy() {
        System.out.println("- Destroy " + this.getClass().getSimpleName());
    }
    
}
