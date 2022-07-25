package com.emrhnsyts.store.response;

import com.emrhnsyts.store.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {
    private Long id;
    private String text;
    private String username;
    private Long productId;
    private Date createdAt;

    public CommentResponse(Comment comment) {
        this.id = comment.getId();
        this.text = comment.getText();
        this.username = comment.getAppUser().getUsername();
        this.productId = comment.getProduct().getId();
        this.createdAt = comment.getCreatedAt();
    }
}
