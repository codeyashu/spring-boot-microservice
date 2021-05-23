package io.rsi.codespace.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class UserRating {
    List<Rating> userRating;
}
