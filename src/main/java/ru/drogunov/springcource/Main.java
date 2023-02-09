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
        MusicPlayer bean = context.getBean(MusicPlayer.class);
//        System.out.println(bean.playMusic());
        System.out.println("//".repeat(10));
        Computer bean1 = context.getBean(Computer.class);
        System.out.println(bean1);
        System.out.println("//".repeat(10));
        context.close();
    }
}
