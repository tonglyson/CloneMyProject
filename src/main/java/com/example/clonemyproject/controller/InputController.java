package com.example.clonemyproject.controller;

import com.example.clonemyproject.entities.Input;
import com.example.clonemyproject.error.ResourceNotFoundException;
import com.example.clonemyproject.repository.InputRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class InputController {
    @Autowired
    private InputRepository inputRepository;
    @GetMapping("inputs")
    public ResponseEntity<List<Input>> getAllInput(@RequestParam(value = "page",defaultValue = "0") int page){
        Pageable pageable = PageRequest.of(page,2, Sort.by(Sort.Direction.DESC,"created"));

        List<Input> list = inputRepository.findAll(pageable).stream().collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @PostMapping("inputs")
    public ResponseEntity<Input> addInputWithInputInfo(@Valid @RequestBody Input input){
        if(inputRepository.existsById(input.getId())){
            throw new IllegalArgumentException("Da ton tai id");
        }
        Input i = inputRepository.save(input);
        return new ResponseEntity(i,HttpStatus.CREATED);
    }
    @PutMapping("inputs/{id}")
    public ResponseEntity<Input> updateInputWithInputInfo(@Valid @RequestBody Input input,@PathVariable("id") String id){
        Input i = inputRepository.findById(id).map(x->{
            x.setContent(input.getContent());
            x.setInputs(input.getInputs());
            x.setUser_input((input.getUser_input()));
           return inputRepository.save(x);
        }).orElseThrow(()->new ResourceNotFoundException("Khong tim thay id nay"));
        return new ResponseEntity(i,HttpStatus.OK);
    }
    @DeleteMapping("inputs/{id}")
    public ResponseEntity<?> deleteInput(@PathVariable String id){
        return inputRepository.findById(id).map(x->{
            inputRepository.delete(x);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException("Khong tim thay id"));
    }
}
