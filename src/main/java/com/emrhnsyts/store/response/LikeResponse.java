package com.emrhnsyts.store.response;

import com.emrhnsyts.store.entity.Like;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LikeResponse {
    private Long id;
    private Long userId;
    private Long productId;

    public LikeResponse(Like like) {
        this.id = like.getId();
        this.userId = like.getAppUser().getId();
        this.productId = like.getProduct().getId();
    }
}
