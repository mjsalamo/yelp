package com.cresendia.exam.yelp.review.model.googlecloudvision;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Feature {
    private Integer maxResults;
    private String type;
}
