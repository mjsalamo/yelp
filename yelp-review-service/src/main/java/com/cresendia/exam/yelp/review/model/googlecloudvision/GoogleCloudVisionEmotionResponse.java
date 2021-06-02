package com.cresendia.exam.yelp.review.model.googlecloudvision;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoogleCloudVisionEmotionResponse {
    private String joyLikelihood;
    private String sorrowLikelihood;
    private String angerLikelihood;
    private String surpriseLikelihood;
}
