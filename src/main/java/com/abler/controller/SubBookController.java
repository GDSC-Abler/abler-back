package com.abler.controller;

import com.abler.domain.subBook.SubBook;
import com.abler.dto.SubBookListDTO;
import com.abler.dto.SubBookSaveDTO;
import com.abler.service.SubBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4000")

@RestController
@Lazy
@RequestMapping("/subbook")
public class SubBookController {

    @Resource
    private final SubBookService subBookService;

    @Autowired
    public SubBookController(SubBookService subBookService) {
        this.subBookService = subBookService;
    }

    @GetMapping("/test")
    public String test(){
        return "test!";
    }

    @GetMapping("/{userId}/list")
    public List<SubBookListDTO> getSubBookListByUserId(@PathVariable("userId") Long userId){
        return subBookService.findByUserId(userId);
    }

    @PostMapping("/save")
    public SubBook saveSubscribeBook(@RequestBody SubBookSaveDTO subBook){
        return subBookService.saveSubBook(subBook);

    }

    // userId, bookId 구분?
    @DeleteMapping("/{id}")
    public void deleteSubscribeBook(@PathVariable("id") UUID id){
        subBookService.deleteSubBook(id);
    }

    @DeleteMapping("/{userId}/{bookId}")
    public void deleteSubscribeBook(@PathVariable("userId") Long userId, @PathVariable("bookId") Long bookId){
        subBookService.deleteSubBook(userId, bookId);
    }
}
