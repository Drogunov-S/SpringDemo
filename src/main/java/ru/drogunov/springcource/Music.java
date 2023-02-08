package ru.drogunov.springcource;

public interface Music {
    String getSong();
    
    default void init() {
        System.out.println("+ Init " + this.getClass().getSimpleName());
    }
    
    default void destroy() {
        System.out.println("- Destroy " + this.getClass().getSimpleName());
    }
}
