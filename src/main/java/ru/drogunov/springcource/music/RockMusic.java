package ru.drogunov.springcource.music;

import org.springframework.stereotype.Component;
import ru.drogunov.springcource.Music;

@Component
public class RockMusic implements Music {
    @Override
    public String getSong() {
        return "Wind cries Mary";
    }
}
