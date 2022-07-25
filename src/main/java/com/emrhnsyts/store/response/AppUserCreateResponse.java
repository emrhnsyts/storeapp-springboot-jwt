package com.emrhnsyts.store.response;

import com.emrhnsyts.store.entity.AppUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppUserCreateResponse {
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String email;
    private String address;
    private Date createdAt;
    private String imageUrl;

    public AppUserCreateResponse(AppUser appUser) {
        this.id = appUser.getId();
        this.name = appUser.getName();
        this.surname = appUser.getSurname();
        this.username = appUser.getUsername();
        this.email = appUser.getEmail();
        this.address = appUser.getAddress();
        this.createdAt = appUser.getCreatedAt();
        this.imageUrl = appUser.getImageUrl();
    }
}
