package com.cresendia.exam.yelp.review.model.yelp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YelpReview{
    private String id;
    private String url;
    private String text;
    private Integer rating;
    @JsonProperty("time_created")
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private LocalDateTime timeCreated;
    private YelpUser user;
}
