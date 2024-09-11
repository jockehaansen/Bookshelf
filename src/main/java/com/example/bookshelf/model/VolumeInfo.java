package com.example.bookshelf.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class VolumeInfo {

    private String title;

    private String subtitle;

    private List<String> authors;

    private String publishedDate;

    private String description;

    private int pageCount;
}
