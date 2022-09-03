package com.emrhnsyts.store.controller;

import com.emrhnsyts.store.request.CommentCreateRequest;
import com.emrhnsyts.store.request.CommentUpdateRequest;
import com.emrhnsyts.store.response.CommentResponse;
import com.emrhnsyts.store.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping
    public List<CommentResponse> getCommentsByUserIdOrProductId(@RequestParam Optional<Long> userId,
                                                                @RequestParam Optional<Long> productId) {
        return commentService.getAllCommentsByProductIdOrUserId(userId, productId);
    }

    @PostMapping
    public CommentResponse addComment(@Valid @RequestBody CommentCreateRequest commentCreateRequest) {
        return commentService.addComment(commentCreateRequest);
    }

    @GetMapping("/{commentId}")
    public CommentResponse getComment(@PathVariable("commentId") Long commentId) {
        return commentService.getComment(commentId);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity deleteComment(@PathVariable("commentId") Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok("Comment Deleted.");
    }

    @PutMapping("/{commentId}")
    public CommentResponse updateComment(@Valid @RequestBody CommentUpdateRequest commentUpdateRequest
            , @PathVariable("commentId") Long commentId) {
        return commentService.updateCommentById(commentId, commentUpdateRequest);
    }
}
