package com.emrhnsyts.store.controller;

import com.emrhnsyts.store.request.LikeCreateRequest;
import com.emrhnsyts.store.response.LikeResponse;
import com.emrhnsyts.store.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/likes")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @GetMapping("/{likeId}")
    public LikeResponse getLike(@PathVariable("likeId") Long likeId){
        return likeService.getLike(likeId);
    }

    @GetMapping
    public List<LikeResponse> getLikesByProductIdOrUserId(@RequestParam Optional<Long> productId
            ,@RequestParam Optional<Long> userId){
        return likeService.getAllLikesByAppUserIdOrProductId(productId,userId);
    }

    @PostMapping
    public LikeResponse addLike(@Valid @RequestBody LikeCreateRequest likeCreateRequest){
        return likeService.addLike(likeCreateRequest);
    }

    @DeleteMapping("/{likeId}")
    public ResponseEntity deleteLike(@PathVariable("likeId") Long likeId){
        likeService.deleteLike(likeId);
        return ResponseEntity.ok("Like deleted.");
    }


}
