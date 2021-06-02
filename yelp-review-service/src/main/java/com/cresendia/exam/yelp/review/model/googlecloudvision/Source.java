package com.cresendia.exam.yelp.review.model.googlecloudvision;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Source {
    private String imageUri;
}
