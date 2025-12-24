package com.project.code.domain.entity;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    private long id;

    @NotNull(message="Product Id cannot be null")
    private Long productId;

    @NotNull(message="Store Id cannot be null")
    private Long storeId;

    @NotNull(message="Rating cannot be null")
    private Integer rating;
    private String comment;



}
