package com.cresendia.exam.yelp.review.model;

import com.cresendia.exam.yelp.review.model.googlecloudvision.GoogleAPIResponse;
import com.cresendia.exam.yelp.review.model.googlecloudvision.GoogleCloudVisionEmotionResponse;
import com.cresendia.exam.yelp.review.model.yelp.YelpReview;
import com.cresendia.exam.yelp.review.model.yelp.YelpUser;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CustomYelpReviewResponse implements Serializable{

    private static final long serialVersionUID = 1L;

    private YelpUser reviewerInfo;
    @JsonIgnoreProperties(value = {"user"})
    private YelpReview reviewContent;
    private GoogleCloudVisionEmotionResponse emotions;

}
