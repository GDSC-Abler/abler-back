package com.abler.service;

import com.abler.domain.subBook.SubBook;
import com.abler.dto.SubBookListDTO;
import com.abler.dto.SubBookSaveDTO;
import org.springframework.context.annotation.Lazy;

import java.util.List;
import java.util.UUID;

@Lazy
public interface SubBookService {

    List<SubBookListDTO> findByUserId(Long userId);
    SubBook saveSubBook(SubBookSaveDTO subBook);
    void deleteSubBook(UUID id);
    void deleteSubBook(Long userId, Long bookId);
}
