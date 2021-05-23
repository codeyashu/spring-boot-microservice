package io.rsi.codespace.resource;

import io.rsi.codespace.model.Rating;
import io.rsi.codespace.model.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RequestMapping("/ratingsdata")
@RestController
public class RatingDataResource {

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable String movieId) {
        return new Rating(movieId, 4);
    }

    @RequestMapping("/users/{userid}")
    public UserRating getUserRating(@PathVariable String userid) {
        return UserRating.builder()
                .userRating(
                        Arrays.asList(
                                new Rating("1234", 4),
                                new Rating("5678", 3)
                        )
                )
                .build();

    }
}
