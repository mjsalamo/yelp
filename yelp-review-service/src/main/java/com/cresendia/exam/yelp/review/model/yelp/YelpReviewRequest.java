package com.cresendia.exam.yelp.review.model.yelp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YelpReviewRequest {

    private String bizId;
}
