package com.cresendia.exam.yelp.review.model.yelp;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YelpUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    @JsonProperty("profile_url")
    private String profileUrl;
    @JsonProperty("image_url")
    private String imageUrl;
    private String name;
}
