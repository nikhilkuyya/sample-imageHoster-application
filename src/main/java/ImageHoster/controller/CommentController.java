package ImageHoster.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ImageHoster.model.Comment;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;

public class CommentController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/image/{id}/comments", method = RequestMethod.POST)
    public String addComment(@PathVariable("id") Integer imageId, @RequestParam("comment") String comment, Model model,
            HttpSession session) {

        User user = (User) session.getAttribute("loggeduser");
        Comment newComment = new Comment();
        newComment.setText(comment);
        newComment.setCommentedUser(user);
        newComment.setImageCommentedOn(this.imageService.getImage(imageId));
        newComment.setCreatedDate(new Date());
        this.commentService.addComment(newComment);
        return "redirect:/images/" + imageId;
    }
}