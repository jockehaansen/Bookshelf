package com.example.bookshelf.model;

import jakarta.persistence.*;
import jdk.jfr.BooleanFlag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    private String id;

    @Embedded
    private VolumeInfo volumeInfo;

    @BooleanFlag
    private boolean markedAsRead;

    public Book(VolumeInfo volumeInfo, boolean markedAsRead) {
        this.volumeInfo = volumeInfo;
        this.markedAsRead = markedAsRead;
    }
}
