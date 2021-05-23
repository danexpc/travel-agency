package com.danexpc.web_library.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LiteratureCategory implements Identifiable<Integer> {

    private Integer id;

    @NonNull
    private String name;
}
