package ru.drogunov.springcource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
String a = "aaaaaaaa";

    
    }
    
    private static void startOut() {
        System.out.println("//".repeat(18));
        System.out.println("//".repeat(5) + " @ Start output " + "//".repeat(5));
        System.out.println("//".repeat(18) + "\n");
    }
    
    private static void endOut(AnnotationConfigApplicationContext context) {
        System.out.println("\n" + "//".repeat(18));
        System.out.println("//".repeat(5) + " @ End output " + "//".repeat(6));
        System.out.println("//".repeat(18) + "\n");
        context.close();
    }
}
