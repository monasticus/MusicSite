package com.musicsite.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tracks")
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 50)
    private String name;

    @ManyToMany(mappedBy = "albumTracks", fetch = FetchType.EAGER)
    @Column(name = "albums")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Album> trackAlbums = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @Column(name = "performers")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Performer> trackPerformers = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Album> getTrackAlbums() {
        return trackAlbums;
    }

    public void setTrackAlbums(List<Album> trackAlbums) {
        this.trackAlbums = trackAlbums;
    }

    public List<Performer> getTrackPerformers() {
        return trackPerformers;
    }

    public void setTrackPerformers(List<Performer> trackPerformers) {
        this.trackPerformers = trackPerformers;
    }

    @Override
    public String toString() {
        return trackPerformers.toString() + " - " + name;
    }
}
