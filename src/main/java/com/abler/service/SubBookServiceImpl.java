package com.abler.service;

import com.abler.domain.subBook.SubBook;
import com.abler.domain.subBook.SubBookRepository;
import com.abler.dto.SubBookListDTO;
import com.abler.dto.SubBookSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Component
@Transactional
public class SubBookServiceImpl implements SubBookService{

    private final SubBookRepository subBookRepository;

    @Autowired
    public SubBookServiceImpl (SubBookRepository subBookRepository){
        this.subBookRepository = subBookRepository;
    }

    @Override
    public List<SubBookListDTO> findByUserId(Long userId){
        List<SubBookListDTO> collect = subBookRepository.findByUserId(userId).stream()
                .map(SubBookListDTO::new)
                .collect(Collectors.toList());
        return collect;
        //return null;
    }

    @Override
    public SubBook saveSubBook(SubBookSaveDTO entity) {
        SubBook subBook = entity.toEntity();
        return subBookRepository.save(subBook);
    }

    @Override
    public void deleteSubBook(UUID id){
        subBookRepository.deleteById(id);
    }

    @Override
    public void deleteSubBook(Long userId, Long bookId){
        subBookRepository.deleteByBookId(userId, bookId);
    }
}
