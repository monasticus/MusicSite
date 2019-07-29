package com.musicsite.rating;

import com.musicsite.album.Album;
import com.musicsite.entity.Ens;
import com.musicsite.entity.Evaluation;
import com.musicsite.performer.Performer;
import com.musicsite.track.Track;
import com.musicsite.user.User;

import javax.persistence.*;

@Entity
@Table(name = "ratings")
public class Rating extends Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private int rating;

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

    public Ens getEns() {
        if (performer != null)
            return performer;
        else if (album != null)
            return album;
        else if (track != null)
            return track;
        else
            return null;
    }

    public void setEns(Ens ens) {
        if (ens instanceof Performer)
            this.setPerformer((Performer) ens);
        else if (ens instanceof Album)
            this.setAlbum((Album) ens);
        else if (ens instanceof Track)
            this.setTrack((Track) ens);
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "(rating: " + rating + ")";
    }
}
