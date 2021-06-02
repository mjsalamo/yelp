package com.cresendia.exam.yelp.review.service;

import com.cresendia.exam.yelp.review.model.CustomYelpReviewResponse;
import com.cresendia.exam.yelp.review.model.googlecloudvision.GoogleCloudVisionEmotionResponse;
import com.cresendia.exam.yelp.review.model.yelp.YelpReviewRequest;
import com.cresendia.exam.yelp.review.model.yelp.YelpReviewResponse;
import com.cresendia.exam.yelp.review.service.config.HttpEntityApiHelper;
import com.cresendia.exam.yelp.review.service.config.YelpApiProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
public class YelpReviewService {

    private static final Logger logger = LoggerFactory.getLogger(YelpReviewService.class);
    @Autowired
    private ThreadLocal<RestTemplate> threadLocalRestTemplate;
    @Autowired
    private YelpApiProperties yelpApiProperties;
    @Autowired
    private HttpEntityApiHelper yelpApiHelper;
    @Autowired
    private EmotionRecognitionService emotionRecognitionService;
    private GoogleCloudVisionEmotionResponse emotionResponse;

    public List<CustomYelpReviewResponse> fetchReviewsByBizId(YelpReviewRequest yelpReviewRequest) {
        logger.info("Inquire start");
        setRestTemplate();
        String bizId = yelpReviewRequest.getBizId();
        UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl(this.yelpApiProperties.getUrl());
        url.path("/" + bizId + "/reviews");

        HttpEntity requestEntity = this.yelpApiHelper.buildYelpRequestEntity();
        YelpReviewResponse response = this.get(url.toUriString(), requestEntity);

        return convertResponse(response);
    }

    private List<CustomYelpReviewResponse> convertResponse(YelpReviewResponse response){
       final List<CustomYelpReviewResponse> customResponseList = new ArrayList<>();
        response.getReviews().stream().forEach(r->
                customResponseList.
                add(CustomYelpReviewResponse.builder().reviewContent(r).reviewerInfo(r.getUser())
                        .emotions(emotionRecognitionService.processEmotionRecognition(r.getUser().getImageUrl())).build()));
    return customResponseList;
    }


    private synchronized void setRestTemplate() {
        threadLocalRestTemplate.set(new RestTemplateBuilder().build());
    }

    private YelpReviewResponse get(String url, HttpEntity requestEntity) {

        ResponseEntity<YelpReviewResponse> responseEntity = null;
        YelpReviewResponse response = null;

        try {
            logger.info(String.format("Request: Header[%s] Body[%s]", requestEntity.getHeaders(), requestEntity.getBody()));
            logger.info(String.format("Requesting to: %s", url));
            responseEntity = threadLocalRestTemplate.get()
                    .exchange(url, HttpMethod.GET, requestEntity, YelpReviewResponse.class);
            logger.info(String.format("Response JSON String: " + responseEntity.getBody().toString()));
            response = responseEntity.getBody();
        } catch (Exception e) {
            logger.error(String.format("API call error occured URL[%s] Error[%s]", url, e.getMessage()));
        }
        return response;
    }


}
