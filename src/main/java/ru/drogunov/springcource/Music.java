package ru.drogunov.springcource;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public interface Music {
    
    List<String> getSongs();
    
    default void init() {
        System.out.println("+ Init " + this.getClass().getSimpleName());
    }
    
    default void destroy() {
        System.out.println("- Destroy " + this.getClass().getSimpleName());
    }
    
}
