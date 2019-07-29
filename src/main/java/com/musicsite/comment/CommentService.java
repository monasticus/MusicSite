package com.musicsite.comment;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommentService {

    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment getComment(Long id) {
        return commentRepository.findOne(id);
    }

    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    public void remove(Long id) {
        commentRepository.delete(id);
    }

}
