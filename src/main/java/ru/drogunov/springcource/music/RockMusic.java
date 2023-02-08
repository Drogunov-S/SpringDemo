package ru.drogunov.springcource.music;

import ru.drogunov.springcource.Music;

public class RockMusic implements Music {
    @Override
    public String getSong() {
        return "Wind cries Mary";
    }
}
