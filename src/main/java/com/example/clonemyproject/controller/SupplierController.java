package com.example.clonemyproject.controller;

import com.example.clonemyproject.entities.Supplier;
import com.example.clonemyproject.error.ResourceNotFoundException;
import com.example.clonemyproject.repository.SupplierRepository;
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
public class SupplierController {
    @Autowired
    private SupplierRepository supplierRepository;
    @GetMapping("/suppliers")
    public ResponseEntity<List<Supplier>> getAll(@RequestParam(name = "page",defaultValue = "0") int page){
        Pageable pageable = PageRequest.of(page,2, Sort.by(Sort.Direction.DESC,"id"));
        List<Supplier>list = supplierRepository.findAll(pageable).stream().collect(Collectors.toList());
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
    @GetMapping("/suppliers/{id}")
    public ResponseEntity<Supplier> getById(@PathVariable(name = "id")int id){
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Khong tim thay id"));
        return new ResponseEntity(supplier,HttpStatus.OK);
    }
    @PostMapping("/suppliers")
    public ResponseEntity<Supplier> addSupplier(@Valid @RequestBody Supplier supplier){
        Supplier supplier1 = supplierRepository.save(supplier);
        return ResponseEntity.ok(supplier1);
    }
    @PutMapping("/suppliers/{id}")
    public ResponseEntity<Supplier> updateSupplier(@Valid @RequestBody Supplier supplier,
                                                   @PathVariable(name = "id") int id){
        return supplierRepository.findById(id).map(x->{
            x.setName(supplier.getName());
            x.setAddress(supplier.getAddress());
            x.setEmail(supplier.getEmail());
            x.setPhone(supplier.getPhone());
            return ResponseEntity.ok(supplierRepository.save(x));
        }).orElseThrow(()->new ResourceNotFoundException("Khong ton tai id"));
    }
    @DeleteMapping("suppliers/{id}")
    public ResponseEntity<?> deleteSupplier(@PathVariable(name = "id") int id){
        return supplierRepository.findById(id).map(x->{
            supplierRepository.delete(x);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException("Khong ton tai id"));
    }

}
