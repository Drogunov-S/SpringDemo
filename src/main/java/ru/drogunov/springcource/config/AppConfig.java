package ru.drogunov.springcource.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.drogunov.springcource.Computer;
import ru.drogunov.springcource.Music;
import ru.drogunov.springcource.MusicPlayer;
import ru.drogunov.springcource.music.ClassicMusic;
import ru.drogunov.springcource.music.JazzMusic;
import ru.drogunov.springcource.music.RockMusic;
import ru.drogunov.springcource.processor.BenchmarkAnnotationBeanPostProcessor;

import java.util.ArrayList;

@Configuration
//@ComponentScan("ru.drogunov.springcource")
@PropertySource("classpath:musicPlayer.properties")
public class AppConfig {
    
    @Bean
    public BenchmarkAnnotationBeanPostProcessor benchmarkAnnotationBeanPostProcessor() {
        return new BenchmarkAnnotationBeanPostProcessor();
    }
    
    @Bean
    public ClassicMusic classicMusic() {
        System.out.println("> Create bean " + this.getClass().getSimpleName().split("\\$\\$")[0] + " " + Thread.currentThread().getStackTrace()[1].getMethodName());
        return new ClassicMusic();
    }
    
    @Bean
    public RockMusic rockMusic() {
        System.out.println("> Create bean " + this.getClass().getSimpleName().split("\\$\\$")[0] + " " + Thread.currentThread().getStackTrace()[1].getMethodName());;
        return new RockMusic();
    }
    
    @Bean
    public JazzMusic jazzMusic() {
        System.out.println("> Create bean " + this.getClass().getSimpleName().split("\\$\\$")[0] + " " + Thread.currentThread().getStackTrace()[1].getMethodName());;
        return new JazzMusic();
    }
    
    @Bean
    public MusicPlayer musicPlayer() {
        System.out.println("> Create bean " + this.getClass().getSimpleName().split("\\$\\$")[0] + " " + Thread.currentThread().getStackTrace()[1].getMethodName());;
        ArrayList<Music> music = new ArrayList<>();
        music.add(classicMusic());
        music.add(rockMusic());
        music.add(jazzMusic());
        return new MusicPlayer(music);
    }
    
    @Bean
    public Computer computer() {
        System.out.println("> Create bean " + this.getClass().getSimpleName().split("\\$\\$")[0] + " " + Thread.currentThread().getStackTrace()[1].getMethodName());;
        return new Computer(musicPlayer());
    }
}
