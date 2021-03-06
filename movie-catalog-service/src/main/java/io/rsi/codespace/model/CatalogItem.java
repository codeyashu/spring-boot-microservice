package io.rsi.codespace.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class CatalogItem {
    private String name;
    private String desc;
    private int rating;
}
