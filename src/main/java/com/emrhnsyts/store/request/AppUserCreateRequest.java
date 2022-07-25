package com.emrhnsyts.store.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppUserCreateRequest {
    @NotBlank(message = "Name can not be blank.")
    @Length(max = 30, message = "Name can not be longer than 30 letters.")
    private String name;
    @NotBlank(message = "Surname can not be blank.")
    @Length(max = 30, message = "Name can not be longer than 30 letters.")
    private String surname;
    @NotBlank(message = "Username can not be blank.")
    @Length(min = 5, max = 20, message = "Username must be between 5 and 20 letters.")
    private String username;
    @NotBlank(message = "Email can not be blank.")
    private String email;
    @NotBlank(message = "Password can not be blank.")
    @Length(min = 5, max = 20, message = "Password must be between 5 and 20 letters.")
    private String password;
    @NotBlank(message = "Address can not be blank.")
    @Length(min = 5, max = 150, message = "Address must be between 5 and 120 letters.")
    private String address;
    private String imageUrl;
}
