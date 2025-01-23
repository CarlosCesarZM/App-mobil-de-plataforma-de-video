package com.example.a01081123_a21tecfilms;

import java.util.ArrayList;

public class Favoritos {
    private static Favoritos instance;
    private ArrayList<String> listaFavoritos;

    private Favoritos() {
        listaFavoritos = new ArrayList<>();
    }

    public static synchronized Favoritos getInstance() {
        if (instance == null) {
            instance = new Favoritos();
        }
        return instance;
    }

    public ArrayList<String> getListaFavoritos() {
        return listaFavoritos;
    }

    public void agregarFavorito(String favorito) {
        listaFavoritos.add(favorito);
    }
}

