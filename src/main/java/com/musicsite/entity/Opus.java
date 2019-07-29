package com.musicsite.entity;

import com.musicsite.performer.Performer;

public abstract class Opus extends Ens{

    public abstract String getName();

    public abstract void setName(String name) ;

    public abstract String getYearOfPublication();

    public abstract void setYearOfPublication(String yearOfPublication);

    public abstract Performer getPerformer();

    public abstract void setPerformer(Performer performer);
}
