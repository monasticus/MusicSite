package com.musicsite.entity;

import com.musicsite.album.Album;
import com.musicsite.performer.Performer;
import com.musicsite.track.Track;
import com.musicsite.user.User;

public abstract class Evaluation {

    private Long id;
    private User user;
    private Performer performer;
    private Album album;
    private Track track;

    public abstract Long getId();

    public abstract void setId(Long id);

    public abstract User getUser();

    public abstract void setUser(User user);

    public abstract Performer getPerformer();

    public abstract void setPerformer(Performer performer);

    public abstract Album getAlbum();

    public abstract void setAlbum(Album album);

    public abstract Track getTrack();

    public abstract void setTrack(Track track);
}
