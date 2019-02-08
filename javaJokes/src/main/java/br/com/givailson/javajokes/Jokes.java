package br.com.givailson.javajokes;

import java.util.Random;

public class Jokes {

    String jokes[] = new String[] {
            "Batatinha quando nasce espalha a rama pelo chão, menininha quando reza, poe a mão no coração",
            "Uma piada pesada e suja... Um eleante caiu na lama",
            "Uma piada leve e limpa... uma pena caiu no rio"
    };

    public String getJoke() {
        Random generator = new Random();
        int randomIndex = generator.nextInt(jokes.length);
        return jokes[randomIndex];
    }
}
