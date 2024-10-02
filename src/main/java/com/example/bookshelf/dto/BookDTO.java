package com.example.bookshelf.dto;

import com.example.bookshelf.model.VolumeInfo;
import jdk.jfr.BooleanFlag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    private Long id;

    private VolumeInfo volumeInfo;

    @BooleanFlag
    private boolean markedAsRead;
}