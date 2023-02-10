package ru.drogunov.springcource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.drogunov.springcource.config.AppConfig;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                AppConfig.class
        );
        MusicPlayer musicPlayer = context.getBean(MusicPlayer.class);
        Computer computer = context.getBean(Computer.class);
        startOut();
        System.out.println(musicPlayer.playMusic() + " this");
        System.out.println(computer);
        System.out.println("//".repeat(15));
        System.out.printf("\tName: %s Volume: %s%n", computer.getMusicPlayer().getName(), computer.getMusicPlayer().getVolume());
        System.out.println("//".repeat(15));
        System.out.printf("\tName: %s Volume: %s%n", musicPlayer.getName(), musicPlayer.getVolume());
        System.out.println("//".repeat(15));
        System.out.println(computer.getMusicPlayer() == musicPlayer);
        endOut(context);
    }
    
    private static void startOut() {
        System.out.println("//".repeat(18));
        System.out.println("//".repeat(5) + " @ Start output " + "//".repeat(5));
        System.out.println("//".repeat(18) + "\n");
    }
    
    private static void endOut(AnnotationConfigApplicationContext context) {
        System.out.println("//".repeat(18) + "\n");
        System.out.println("//".repeat(5) + " @ End output " + "//".repeat(6));
        System.out.println("//".repeat(18) + "\n");
        context.close();
    }
}
