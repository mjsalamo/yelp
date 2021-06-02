package com.cresendia.exam.yelp.review.service.config;

import com.cresendia.exam.yelp.review.model.googlecloudvision.GoogleAPIRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;

@Component
public class HttpEntityApiHelper {

    @Autowired
    private YelpApiProperties yelpApiProperties;
    @Autowired
    private GoogleCloudVisionApiProperties googleCloudVisionApiProperties;

    public HttpEntity<HashMap<String, String>> buildYelpRequestEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", this.yelpApiProperties.getAuth());
        return new HttpEntity<>(null, headers);
    }

    public HttpEntity<GoogleAPIRequest> buildEmotionRecognitionRequestEntity(GoogleAPIRequest googleCloudVisionEmotionRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("client_id", this.googleCloudVisionApiProperties.getClientId());
        headers.set("client_secret", this.googleCloudVisionApiProperties.getClientSecret());
        return new HttpEntity<>(googleCloudVisionEmotionRequest, headers);
    }

}
