package com.emrhnsyts.store.response;

import com.emrhnsyts.store.entity.AppUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppUserResponse {
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String email;
    private String address;
    private Date createdAt;
    private String imageUrl;
    private List<CommentResponse> comments;
    private List<LikeResponse> likes;

    public AppUserResponse(AppUser appUser) {
        this.id = appUser.getId();
        this.name = appUser.getName();
        this.surname = appUser.getSurname();
        this.username = appUser.getUsername();
        this.email = appUser.getEmail();
        this.address = appUser.getAddress();
        this.createdAt = appUser.getCreatedAt();
        this.imageUrl = appUser.getImageUrl();
        this.comments = appUser.getComments().stream().map(comment -> {
            return new CommentResponse(comment);
        }).collect(Collectors.toList());
        this.likes = appUser.getLikes().stream().map(like -> {
            return new LikeResponse(like);
        }).collect(Collectors.toList());
    }
}
