package ru.stqa.pft.sandbox;

import java.util.Arrays;
import java.util.List;

public class Collections {

    public static void main(String[] args) {
        String[] langs = {"Java", "Java", "Java", "Java"};

        List<String> languages = Arrays.asList("Java", "Java", "Java", "Java");

        for (int i = 0; i < languages.size(); i++) {
            System.out.println("Я хочу выучить " + langs[i]);
        }

        for (String l : languages) {
            System.out.println("Я хочу выучить " + l);
        }
    }
}
