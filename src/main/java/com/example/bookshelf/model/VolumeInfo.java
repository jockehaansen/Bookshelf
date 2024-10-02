package com.example.bookshelf.model;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class VolumeInfo {

    @Size(min = 1, max = 255)
    private String title;

    @Size(min = 1, max = 255)
    private String subtitle;

    @ElementCollection
    private List<String> authors;

    @NotBlank
    private String publishedDate;

    @Size(min = 1, max = 2000)
    @Column(length = 2000)
    private String description;

    @NotNull
    private int pageCount;
}
