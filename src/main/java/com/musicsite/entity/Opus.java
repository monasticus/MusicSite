package com.musicsite.entity;

import java.util.ArrayList;
import java.util.List;

public abstract class Opus extends Ens{

    private String name;
    private String yearOfPublication;
    private List<Performer> performers = new ArrayList<>();

    public abstract String getName();

    public abstract void setName(String name) ;

    public abstract String getYearOfPublication();

    public abstract void setYearOfPublication(String yearOfPublication);

    public abstract List<Performer> getPerformers();

    public abstract void setPerformers(List<Performer> performers);
}
