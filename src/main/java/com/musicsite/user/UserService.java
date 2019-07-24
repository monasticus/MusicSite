package com.musicsite.user;

import com.musicsite.album.Album;
import com.musicsite.performer.Performer;
import com.musicsite.rating.Rating;
import com.musicsite.rating.RatingRepository;
import com.musicsite.track.Track;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;
    private RatingRepository ratingRepository;

    public UserService(UserRepository userRepository, RatingRepository ratingRepository) {
        this.userRepository = userRepository;
        this.ratingRepository = ratingRepository;
    }

    public User getUserByUsername(String username) {
        return userRepository.getUserByUsernameIgnoreCase(username);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findOne(id);
    }

    public User getUserByEmail(String email) {
        return userRepository.getUserByEmailIgnoreCase(email);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public int getPerformerUserRating(Long userId, Performer performer) {
        Rating rating = ratingRepository.getRatingByUserAndPerformer(getUserById(userId), performer);
        if (rating == null)
            return 0;
        else
            return rating.getRating();
    }

    public int getAlbumUserRating(Long userId, Album album) {
        Rating rating = ratingRepository.getRatingByUserAndAlbum(getUserById(userId), album);
        if (rating == null)
            return 0;
        else
            return rating.getRating();
    }

    public int getTrackUserRating(Long userId, Track track) {
        Rating rating = ratingRepository.getRatingByUserAndTrack(getUserById(userId), track);
        if (rating == null)
            return 0;
        else
            return rating.getRating();
    }

    public void updateUser(User originUser, User newUser) {
        originUser.setFirstName(newUser.getFirstName());
        originUser.setUsername(newUser.getUsername());
        originUser.setEmail(newUser.getEmail());
        userRepository.save(originUser);
    }

    public void removeUser(Long id) {
        userRepository.delete(id);
    }

    public void confirm(Long id) {
        userRepository.findOne(id).setConfirmed(true);
    }
}
