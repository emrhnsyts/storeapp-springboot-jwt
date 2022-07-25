package com.emrhnsyts.store.request;

import com.emrhnsyts.store.entity.Like;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LikeCreateRequest {
    private Long userId;
    private Long productId;
}
