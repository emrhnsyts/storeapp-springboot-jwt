package com.emrhnsyts.store.service;

import com.emrhnsyts.store.entity.AppUser;
import com.emrhnsyts.store.exception.UserNotFoundException;
import com.emrhnsyts.store.repository.AppUserRepository;
import com.emrhnsyts.store.request.AppUserCreateRequest;
import com.emrhnsyts.store.request.AppUserEmailUpdateRequest;
import com.emrhnsyts.store.request.AppUserPasswordUpdateRequest;
import com.emrhnsyts.store.response.AppUserCreateResponse;
import com.emrhnsyts.store.response.AppUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserService implements UserDetailsService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUserCreateResponse addAppUser(AppUserCreateRequest appUserCreateRequest){
        AppUser appUser = new AppUser();
        appUser.setEmail(appUserCreateRequest.getEmail());
        appUser.setImageUrl(appUserCreateRequest.getImageUrl());
        appUser.setName(appUserCreateRequest.getName());
        appUser.setAddress(appUserCreateRequest.getAddress());
        appUser.setSurname(appUserCreateRequest.getSurname());
        appUser.setUsername(appUserCreateRequest.getUsername());
        appUser.setPassword(passwordEncoder.encode(appUserCreateRequest.getPassword()));
        return new AppUserCreateResponse(appUserRepository.save(appUser));
    }

    public AppUserResponse updateAppUserPassword(Long appUserId, AppUserPasswordUpdateRequest appUserPasswordUpdateRequest){
        AppUser appUser = getAppUserForService(appUserId);
        appUser.setPassword(passwordEncoder.encode(appUserPasswordUpdateRequest.getPassword()));
        return new AppUserResponse(appUserRepository.save(appUser));
    }

    public AppUserResponse updateAppUserEmail(Long appUserId, AppUserEmailUpdateRequest appUserEmailUpdateRequest){
        AppUser appUser = getAppUserForService(appUserId);
        appUser.setEmail(appUserEmailUpdateRequest.getEmail());
        return new AppUserResponse(appUserRepository.save(appUser));
    }

    public void deleteAppUser(Long appUserId){
        Optional<AppUser> optionalAppUser = appUserRepository.findById(appUserId);
        if (optionalAppUser.isPresent()){
            appUserRepository.delete(optionalAppUser.get());
        }
        else{
            throw new UserNotFoundException("User not found.");
        }
    }

    public AppUserResponse getAppUser(Long appUserId){
        Optional<AppUser> optionalAppUser = appUserRepository.findById(appUserId);
        if (optionalAppUser.isPresent()){
            return new AppUserResponse(optionalAppUser.get());
        }
        throw new UserNotFoundException("User not found.");
    }

    protected AppUser getAppUserForService(Long appUserId) {
        Optional<AppUser> optionalAppUser = appUserRepository.findById(appUserId);
        if (optionalAppUser.isPresent()) {
            return optionalAppUser.get();
        }
        throw new UserNotFoundException("User not found.");
    }


    @Override
    public AppUser loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> optionalAppUser = appUserRepository.findByUsername(username);
        if (optionalAppUser.isPresent()) {
            return optionalAppUser.get();
        }
        throw new UsernameNotFoundException("Username not found.");
    }
}
