package io.rsi.codespace.resource;

import io.rsi.codespace.model.CatalogItem;
import io.rsi.codespace.model.Movie;
import io.rsi.codespace.model.UserRating;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
@RequiredArgsConstructor
public class MovieCatalogResource {

    private final RestTemplate restTemplate;

  /*  @Autowired
    WebClient.Builder webClientBuilder;*/

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId) {

        UserRating userRating = restTemplate
                .getForObject("http://ratings-data-service/ratingsdata/users/foo" + userId, UserRating.class);

        return userRating.getUserRating().stream().map(rating -> {
            Movie movie = restTemplate
                    .getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
            return new CatalogItem(movie.getName(), "Test", rating.getRating());
        }).collect(Collectors.toList());

    }

    /**
     * Demo for WebclientBuilder , uncomment webflux dependency to test service
     */
    /*@RequestMapping("webclient/{userId}")
    public List<CatalogItem> getCatalogWebClient(@PathVariable String userId) {

        UserRating userRating = restTemplate
                .getForObject("http://localhost:8083/ratingsdata/users/foo" + userId, UserRating.class);

        return userRating.getUserRating().stream().map(rating -> {

            Movie movie = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8082/movies/" + rating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();
            return new CatalogItem(movie.getName(), "Test", rating.getRating());
        }).collect(Collectors.toList());

    }*/

}
