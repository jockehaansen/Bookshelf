package com.example.bookshelf.dto;

import com.example.bookshelf.model.VolumeInfo;
import lombok.Data;

@Data
public class BookDTO {
    private Long id;
    private VolumeInfo volumeInfo;
    private boolean markedAsRead;
}