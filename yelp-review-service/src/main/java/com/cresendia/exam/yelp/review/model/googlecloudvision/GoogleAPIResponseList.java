package com.cresendia.exam.yelp.review.model.googlecloudvision;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoogleAPIResponseList {
    private List<GoogleAPIResponse> responses;
}
