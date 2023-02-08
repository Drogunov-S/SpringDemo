package ru.drogunov.springcource;

import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.drogunov.springcource.music.ClassicMusic;

public class Main {
    
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml"
        );
        System.out.println("//".repeat(10));
        MusicPlayer bean = context.getBean(MusicPlayer.class);
        bean.playMusic();
        System.out.println("//".repeat(10));
        ClassicMusic bean1 = context.getBean(ClassicMusic.class);
        System.out.println(bean1.getSong());
        context.close();
    }
}
