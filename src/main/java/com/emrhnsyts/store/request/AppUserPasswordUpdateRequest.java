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
public class AppUserPasswordUpdateRequest {
    @NotBlank(message = "Password can not be blank.")
    @Length(min=5,max = 20,message = "Password must be between 5 and 20 letters.")
    private String password;
}
