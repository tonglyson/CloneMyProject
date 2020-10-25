package com.example.clonemyproject.controller;

import com.example.clonemyproject.entities.Unit;
import com.example.clonemyproject.error.ResourceNotFoundException;
import com.example.clonemyproject.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UnitController {
    @Autowired
    private UnitRepository unitRepository;
    @GetMapping("/units")
    public ResponseEntity<List<Unit>> getAllUnit(){
        return new ResponseEntity(unitRepository.findAll(Sort.by(Sort.Direction.DESC,"id")), HttpStatus.OK);
    }
    @PostMapping("/units")
    public ResponseEntity<Unit> addUnit(@Valid @RequestBody Unit unit){
        if(unitRepository.existsById(unit.getId())){
            throw new IllegalArgumentException("Da ton tai id nay");
        }
        Unit u = unitRepository.save(unit);
        return new ResponseEntity(u,HttpStatus.CREATED);
    }
    @PutMapping("/units/{id}")
    public ResponseEntity<Unit> updateUnit(@Valid @RequestBody Unit unit,@PathVariable("id") String id){
        Unit u = unitRepository.findById(id).map(x->{
            x.setName(unit.getName());
            return unitRepository.save(x);
        }).orElseThrow(()->new ResourceNotFoundException("Khong tim thay id"));
        return new ResponseEntity(u,HttpStatus.OK);
    }
    @DeleteMapping("units/{id}")
    public ResponseEntity<?> deleteUnit(@PathVariable("id") String id){
        return unitRepository.findById(id).map(u->{
            unitRepository.delete(u);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException("Khong tim thay id"));
    }
}
