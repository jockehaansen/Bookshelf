package com.example.bookshelf.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;
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

    private List<String> authors;

    private String publishedDate;

    @Size(min = 1, max = 1000)
    @Column(length = 1000)
    private String description;

    private int pageCount;
}
