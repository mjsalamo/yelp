package com.cresendia.exam.yelp.review.controller;

import com.cresendia.exam.yelp.review.model.CustomYelpReviewResponse;
import com.cresendia.exam.yelp.review.model.yelp.YelpReviewRequest;
import com.cresendia.exam.yelp.review.service.YelpReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/yelp")
public class YelpApiController {

    @Autowired
    YelpReviewService yelpReviewService;

    @PostMapping(value = "/business/reviews")
    public ResponseEntity<List<CustomYelpReviewResponse>> processYelpReviewsByBusinessId(@RequestBody YelpReviewRequest yelpReviewRequest) {
        List<CustomYelpReviewResponse> result = yelpReviewService.fetchReviewsByBizId(yelpReviewRequest);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
