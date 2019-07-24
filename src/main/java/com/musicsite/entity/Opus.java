package com.musicsite.entity;

import com.musicsite.performer.Performer;

public abstract class Opus extends Ens{

    private String name;
    private String yearOfPublication;
    private Performer performer;

    public abstract String getName();

    public abstract void setName(String name) ;

    public abstract String getYearOfPublication();

    public abstract void setYearOfPublication(String yearOfPublication);

    public Performer getPerformer() {
        return performer;
    }

    public void setPerformer(Performer performer) {
        this.performer = performer;
    }
}
