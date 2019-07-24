package com.musicsite.comment;

import com.musicsite.comment.Comment;
import com.musicsite.comment.CommentRepository;
import com.musicsite.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommentService {

    private UserRepository userRepository;
    private CommentRepository commentRepository;

    @Autowired
    public CommentService(UserRepository userRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    public Comment getComment(Long id) {
        return commentRepository.findOne(id);
    }

    public void save (Comment comment) {
        commentRepository.save(comment);
    }

    public void remove (Long id) {
        commentRepository.delete(id);
    }

}