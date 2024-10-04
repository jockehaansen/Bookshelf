package com.example.bookshelf.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class ImageLinks {

    @URL
    private String smallThumbnail;

    @URL
    private String thumbnail;
}
