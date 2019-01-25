package br.com.givailson.javajokes.models;

import java.io.Serializable;

public class JokeModel implements Serializable {

    public JokeModel() {}

    public JokeModel(String title, String joke) {
        this.title = title;
        this.joke = joke;
    }

    private String title;
    private String joke;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }
}
