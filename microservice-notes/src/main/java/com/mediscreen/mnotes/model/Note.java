package com.mediscreen.mnotes.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Document(collection = "notes")
public class Note {
    @Id
    private String id;
    private Integer patId;
    private String notes;

}
