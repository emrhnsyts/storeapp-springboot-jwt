package com.emrhnsyts.store.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppUserEmailUpdateRequest {
    @NotBlank(message = "Email can not be blank.")
    private String email;
}
