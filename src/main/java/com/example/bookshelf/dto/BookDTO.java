package com.example.bookshelf.dto;

import com.example.bookshelf.model.VolumeInfo;
import lombok.Data;

@Data
public class BookDTO {

    private String id;
    private VolumeInfo volumeInfo;
}
