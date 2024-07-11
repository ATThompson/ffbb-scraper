package fr.athompson.ffbbscraper.utils;

import lombok.Getter;

public final class CompteurAppelSingleton {

    private static CompteurAppelSingleton INSTANCE;

    private Integer nbAppel;

    private CompteurAppelSingleton(){
        this.nbAppel = 0;
    }

    public static CompteurAppelSingleton getInstance(){
        if(INSTANCE == null )
                INSTANCE = new CompteurAppelSingleton();
        return INSTANCE;
    }

    public void ajoutUnNombreAppel(){
        this.nbAppel++;
    }

    public Integer getNbAppel() {
        return nbAppel;
    }
}
