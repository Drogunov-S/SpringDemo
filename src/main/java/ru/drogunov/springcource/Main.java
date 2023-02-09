package ru.drogunov.springcource;

import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.drogunov.springcource.music.ClassicMusic;
import ru.drogunov.springcource.music.RockMusic;

public class Main {
    
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml"
        );
        System.out.println("//".repeat(10));
        MusicPlayer musicPlayer = context.getBean(MusicPlayer.class);
        System.out.println("//".repeat(10));
        Computer computer = context.getBean(Computer.class);
        System.out.printf("Name: %s Volume: %s%n", computer.getMusicPlayer().getName(), computer.getMusicPlayer().getVolume());
        System.out.println("//".repeat(10));
        System.out.printf("Name: %s Volume: %s%n", musicPlayer.getName(), musicPlayer.getVolume());
        System.out.println(computer.getMusicPlayer() == musicPlayer);
        context.close();
    }
}
