package com.cresendia.exam.yelp.review.model.googlecloudvision;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FaceAnnotations {
    String joyLikelihood;
    String sorrowLikelihood;
    String angerLikelihood;
    String surpriseLikelihood;
}
