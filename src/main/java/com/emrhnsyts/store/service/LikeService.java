package com.emrhnsyts.store.service;

import com.emrhnsyts.store.entity.AppUser;
import com.emrhnsyts.store.entity.Like;
import com.emrhnsyts.store.entity.Product;
import com.emrhnsyts.store.exception.LikeNotFoundException;
import com.emrhnsyts.store.repository.LikeRepository;
import com.emrhnsyts.store.request.LikeCreateRequest;
import com.emrhnsyts.store.response.LikeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final AppUserService appUserService;
    private final ProductService productService;

    public List<LikeResponse> getAllLikesByProductId(Long productId) {
        Product product = productService.getProductForService(productId);
        return likeRepository.findByProductId(productId).stream().map(like -> {
            return new LikeResponse(like);
        }).collect(Collectors.toList());
    }

    public List<LikeResponse> getAllLikesByAppUserId(Long appUserId) {
        AppUser appUser = appUserService.getAppUserForService(appUserId);
        return likeRepository.findByAppUserId(appUserId).stream().map(like -> {
            return new LikeResponse(like);
        }).collect(Collectors.toList());
    }

    public LikeResponse getLike(Long likeId) {
        Optional<Like> optionalLike = likeRepository.findById(likeId);
        if (optionalLike.isPresent()) {
            return new LikeResponse(optionalLike.get());
        }
        throw new LikeNotFoundException("Like not found");
    }

    public LikeResponse addLike(LikeCreateRequest likeCreateRequest) {
        AppUser appUser = appUserService.getAppUserForService(likeCreateRequest.getUserId());
        Product product = productService.getProductForService(likeCreateRequest.getProductId());
        return new LikeResponse(likeRepository.save(Like.builder().product(product).appUser(appUser).build()));
    }

    public void deleteLike(Long likeId) {
        Optional<Like> optionalLike = likeRepository.findById(likeId);
        if (optionalLike.isPresent()) {
            likeRepository.delete(optionalLike.get());
        }
        else{
            throw new LikeNotFoundException("Like not found.");
        }
    }

    public List<LikeResponse> getAllLikesByAppUserIdOrProductId(Optional<Long> productId, Optional<Long> userId) {
        if (productId.isPresent()){
            return getAllLikesByProductId(productId.get());
        }
        else if (userId.isPresent()) {
            return getAllLikesByAppUserId(userId.get());
        }
        return null;
    }
}
