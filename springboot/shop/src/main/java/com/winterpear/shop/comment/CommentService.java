package com.winterpear.shop.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public void saveComment(String username, String date, String content, Integer parentId) {
        Comment comment = new Comment();
        comment.setUsername(username);
        comment.setCommentDate(date);
        comment.setContent(content);
        comment.setParentId(parentId);
        commentRepository.save(comment);
    }

    public String getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
            return userDetails.getUsername();
        }
        return null;
    }

    public List<CommentDTO> getCommentUsers(Integer parentId) {
        List<Comment> comments = commentRepository.findByParentId(parentId);

        List<CommentDTO> commentList = new ArrayList<>();
        SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        isoFormat.setTimeZone(TimeZone.getTimeZone("KST"));

        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        for (Comment comment : comments) {
            String dateParse = comment.getCommentDate();
            try {
                Date commentDate = isoFormat.parse(dateParse);
                String date = outputFormat.format(commentDate);
                String username = comment.getUsername();
                String content = comment.getContent();

                CommentDTO dto = new CommentDTO(username, date, content);
                commentList.add(dto);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return commentList;
    }

}
