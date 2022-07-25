package com.emrhnsyts.store.controller;


import com.emrhnsyts.store.entity.AppUser;
import com.emrhnsyts.store.request.AppUserCreateRequest;
import com.emrhnsyts.store.request.AppUserEmailUpdateRequest;
import com.emrhnsyts.store.request.AppUserLoginRequest;
import com.emrhnsyts.store.request.AppUserPasswordUpdateRequest;
import com.emrhnsyts.store.response.AppUserCreateResponse;
import com.emrhnsyts.store.response.AppUserResponse;
import com.emrhnsyts.store.security.JwtTokenUtil;
import com.emrhnsyts.store.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class AppUserController {
    private final AppUserService appUserService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public String login(@Valid @RequestBody AppUserLoginRequest appUserLoginRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    appUserLoginRequest.getUsername(), appUserLoginRequest.getPassword()
            ));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password.");
        }

        final AppUser appUser = appUserService.loadUserByUsername(appUserLoginRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(appUser);
        return jwt;
    }


    @PostMapping("/register")
    public AppUserCreateResponse register(@Valid @RequestBody AppUserCreateRequest appUserCreateRequest) {
        return appUserService.addAppUser(appUserCreateRequest);
    }

    @GetMapping("/{userId}")
    public AppUserResponse getUser(@PathVariable("userId") Long userId) {
        return appUserService.getAppUser(userId);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUser(@PathVariable("userId") Long userId) {
        appUserService.deleteAppUser(userId);
        return ResponseEntity.ok("User deleted.");
    }

    @PutMapping("/password/{userId}")
    public AppUserResponse updateUserPassword(@Valid @RequestBody AppUserPasswordUpdateRequest appUserPasswordUpdateRequest, @PathVariable("userId") Long userId) {
        return appUserService.updateAppUserPassword(userId, appUserPasswordUpdateRequest);
    }

    @PutMapping("/email/{userId}")
    public AppUserResponse updateUserEmail(@Valid @RequestBody AppUserEmailUpdateRequest appUserEmailUpdateRequest, @PathVariable("userId") Long userId) {
        return appUserService.updateAppUserEmail(userId, appUserEmailUpdateRequest);
    }

}
