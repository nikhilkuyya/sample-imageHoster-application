package ImageHoster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ImageHoster.model.Comment;
import ImageHoster.repository.CommentRepository;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public void addComment(Comment comment) {
        this.commentRepository.createComment(comment);
    }

}