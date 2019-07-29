package com.musicsite.recommendation;

import com.musicsite.album.Album;
import com.musicsite.entity.Evaluation;
import com.musicsite.performer.Performer;
import com.musicsite.track.Track;
import com.musicsite.user.User;

import javax.persistence.*;

@Entity
@Table(name = "recommendations")
public class Recommendation extends Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Performer performer;

    @ManyToOne
    private Album album;

    @ManyToOne
    private Track track;


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public Performer getPerformer() {
        return performer;
    }

    @Override
    public void setPerformer(Performer performer) {
        this.performer = performer;
    }

    @Override
    public Album getAlbum() {
        return album;
    }

    @Override
    public void setAlbum(Album album) {
        this.album = album;
    }

    @Override
    public Track getTrack() {
        return track;
    }

    @Override
    public void setTrack(Track track) {
        this.track = track;
    }

    @Override
    public String toString() {
        return "recommendation: " + id;
    }
}
