package com.abler.dto;

import com.abler.domain.subBook.SubBook;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class SubBookListDTO {
    private UUID id;
    private LocalDateTime datetime;
    private Long userId;
    private Long bookId;

    public SubBookListDTO(SubBook subBook){
        id = subBook.getId();
        datetime = subBook.getDatetime();
        userId = subBook.getUserId();
        bookId = subBook.getBookId();
    }
}
