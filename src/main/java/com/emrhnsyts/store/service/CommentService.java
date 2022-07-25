package com.emrhnsyts.store.service;

import com.emrhnsyts.store.entity.AppUser;
import com.emrhnsyts.store.entity.Comment;
import com.emrhnsyts.store.entity.Product;
import com.emrhnsyts.store.exception.CommentNotFoundException;
import com.emrhnsyts.store.repository.CommentRepository;
import com.emrhnsyts.store.request.CommentCreateRequest;
import com.emrhnsyts.store.request.CommentUpdateRequest;
import com.emrhnsyts.store.response.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final AppUserService appUserService;
    private final ProductService productService;

    public List<CommentResponse> getAllCommentsByUserId(Long appUserId) {
        AppUser appUser = appUserService.getAppUserForService(appUserId);
        return commentRepository.findByAppUserId(appUserId).stream().map(comment -> {
            return new CommentResponse(comment);
        }).collect(Collectors.toList());
    }

    public CommentResponse getComment(Long commentId) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isPresent()) {
            return new CommentResponse(optionalComment.get());
        }
        throw new CommentNotFoundException("Comment not found.");
    }

    public List<CommentResponse> getAllCommentsByProductId(Long productId) {
        Product product = productService.getProductForService(productId);
        return commentRepository.findByProductId(productId).stream().map(comment -> {
            return new CommentResponse(comment);
        }).collect(Collectors.toList());
    }

    public CommentResponse addComment(CommentCreateRequest commentCreateRequest) {
        AppUser appUser = appUserService.getAppUserForService(commentCreateRequest.getUserId());
        Product product = productService.getProductForService(commentCreateRequest.getProductId());
        return new CommentResponse(commentRepository
                .save(Comment.builder()
                        .appUser(appUser)
                        .product(product)
                        .text(commentCreateRequest.getText())
                        .build()));
    }

    public void deleteComment(Long commentId) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isPresent()) {
            commentRepository.delete(optionalComment.get());
        }
        else{
            throw new CommentNotFoundException("Comment not found.");
        }
    }

    public CommentResponse updateCommentById(Long commentId, CommentUpdateRequest commentUpdateRequest) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            comment.setText(commentUpdateRequest.getText());
            return new CommentResponse(commentRepository.save(comment));
        }
        throw new CommentNotFoundException("Comment not found.");
    }


    public List<CommentResponse> getAllCommentsByProductIdOrUserId(Optional<Long> userId, Optional<Long> productId) {
        if (userId.isPresent()) {
            return getAllCommentsByUserId(userId.get());
        } else if (productId.isPresent()) {
            return getAllCommentsByProductId(productId.get());
        }
        return null;
    }
}
