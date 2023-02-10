package ru.drogunov.springcource;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public interface Music {
    
    List<String> getSongs();
    @PostConstruct
    default void init() {
        System.out.println("+ Init " + this.getClass().getSimpleName());
    }
    @PreDestroy
    default void destroy() {
        System.out.println("- Destroy " + this.getClass().getSimpleName());
    }
    
}
