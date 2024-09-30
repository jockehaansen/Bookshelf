package com.example.bookshelf.dto;

import com.example.bookshelf.model.VolumeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveBookDTO {
    private VolumeInfo volumeInfo;
}
