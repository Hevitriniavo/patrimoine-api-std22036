package com.fresh.coding.patrimoineapi.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Patrimoine {
    private String possesseur;
    private LocalDateTime derniereModification;
}
