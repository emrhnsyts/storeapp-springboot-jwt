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
public class AppUserLoginRequest {
    @Length(min = 5, max = 20, message = "Username length must be between 5 and 20.")
    @NotBlank(message = "Username field can not be null.")
    private String username;
    @Length(min = 5, max = 20, message = "Password length must be between 5 and 20.")
    @NotBlank(message = "Password field can not be null.")
    private String password;
}
