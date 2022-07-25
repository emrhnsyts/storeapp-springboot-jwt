package com.emrhnsyts.store.entity;

import com.emrhnsyts.store.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Product extends BaseEntity {
    private String name;
    private Integer quantity;
    private Double price;
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;
    @ManyToMany
    @JoinTable(name = "product_category"
            , joinColumns = @JoinColumn(name = "product_id")
            , inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;
    private String imageUrl;
    @OneToMany(mappedBy = "product")
    private List<Like> likes;
    @OneToMany(mappedBy = "product")
    private List<Comment> comments;
}
