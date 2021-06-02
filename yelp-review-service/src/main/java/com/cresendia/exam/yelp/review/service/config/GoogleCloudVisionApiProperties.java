package com.cresendia.exam.yelp.review.service.config;
/**
 * Globe FinTech Innovations, Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/**
 * @author mjsalamo
 * @version $Id: LuxandApiProperties.java, v 0.1 2021-06-01 mjsalamo Exp $$
 */
@Configuration
@ConfigurationProperties(prefix = "com.google.cloud.vision.api", ignoreInvalidFields = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoogleCloudVisionApiProperties {

    private String url;
    private String apiKey;
    private String clientId;
    private String clientSecret;


}
