package com.cresendia.exam.yelp.review.service;

import com.cresendia.exam.yelp.review.model.googlecloudvision.*;
import com.cresendia.exam.yelp.review.service.config.GoogleCloudVisionApiProperties;
import com.cresendia.exam.yelp.review.service.config.HttpEntityApiHelper;
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
import java.util.Optional;

@Service
public class EmotionRecognitionService {

    private static final Logger logger = LoggerFactory.getLogger(EmotionRecognitionService.class);
    private static final Integer FEATURE_MAX_RESULT = 10;
    private static final String FEATURE_TYPE = "FACE_DETECTION";
    @Autowired
    private ThreadLocal<RestTemplate> threadLocalRestTemplate;
    @Autowired
    private GoogleCloudVisionApiProperties googleCloudVisionApiProperties;
    @Autowired
    private HttpEntityApiHelper googleCloudVisionApiHelper;

    public GoogleCloudVisionEmotionResponse processEmotionRecognition(String imageUrl) {
        logger.info("Process emotion recognition");
        setRestTemplate();
        UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl(this.googleCloudVisionApiProperties.getUrl());
        url.queryParam("key", googleCloudVisionApiProperties.getApiKey());
        GoogleAPIRequest requestList = buildRequestBody(imageUrl);
        HttpEntity requestEntity = this.googleCloudVisionApiHelper.
                buildEmotionRecognitionRequestEntity(requestList);
        GoogleAPIResponseList googleAPIResponse = this.post(url.toUriString(), requestEntity);

        return fetchEmotionsResponse(googleAPIResponse);
    }

    private synchronized void setRestTemplate() {
        threadLocalRestTemplate.set(new RestTemplateBuilder().build());
    }

    private GoogleCloudVisionEmotionResponse fetchEmotionsResponse(GoogleAPIResponseList apiResponseList){
        Optional<FaceAnnotations> emotions = apiResponseList.getResponses().get(0).getFaceAnnotations().stream().findFirst();
        return emotions.isPresent() ? GoogleCloudVisionEmotionResponse.builder()
                        .joyLikelihood(emotions.get().getJoyLikelihood())
                        .angerLikelihood(emotions.get().getAngerLikelihood())
                        .sorrowLikelihood(emotions.get().getSorrowLikelihood())
                        .surpriseLikelihood(emotions.get().getSurpriseLikelihood()).build() : null;
    }
    private GoogleAPIRequest buildRequestBody(String imageUrl) {
        Image img = Image.builder().source(new Source()).build();
        img.getSource().setImageUri(imageUrl);
        List<Feature> featureList = new ArrayList<>();
        featureList.add(Feature.builder().maxResults(FEATURE_MAX_RESULT).type(FEATURE_TYPE).build());
        GoogleCloudVisionEmotionRequest singleRequest = GoogleCloudVisionEmotionRequest.builder()
                .image(img).features(featureList).build();
        List<GoogleCloudVisionEmotionRequest> requestList = new ArrayList<>();
        requestList.add(singleRequest);
        return GoogleAPIRequest.builder().requests(requestList).build();
    }

    private GoogleAPIResponseList post(String url, HttpEntity requestEntity) {

        ResponseEntity<GoogleAPIResponseList> responseEntity = null;
        GoogleAPIResponseList response = null;

        try {
            logger.info(String.format("Request: Header[%s] Body[%s]", requestEntity.getHeaders(), requestEntity.getBody()));
            logger.info(String.format("Requesting to: %s", url));
            responseEntity = threadLocalRestTemplate.get()
                    .exchange(url, HttpMethod.POST, requestEntity, GoogleAPIResponseList.class);
            logger.info(String.format("Response JSON String: " + responseEntity.getBody().toString()));
            response = responseEntity.getBody();
        } catch (Exception e) {
            logger.error(String.format("API call error occured URL[%s] Error[%s]", url, e.getMessage()));
        }
        return response;
    }
}