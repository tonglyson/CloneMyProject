package com.example.clonemyproject.controller;

import com.example.clonemyproject.entities.Product;
import com.example.clonemyproject.entities.Unit;
import com.example.clonemyproject.error.ResourceNotFoundException;
import com.example.clonemyproject.repository.ProductRepository;
import com.example.clonemyproject.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UnitRepository unitRepository;
//    @GetMapping("products")
//    public ResponseEntity<List<Product>> getAllproducts(@RequestParam(value = "page",defaultValue = "0") int page){
//        Pageable pageable = PageRequest.of(page,2, Sort.by(Sort.Direction.DESC,"id"));
//        List<Product> list = productRepository.findAll(pageable).stream().collect(Collectors.toList());
//        return new ResponseEntity<>(list, HttpStatus.OK);
//    }
    @GetMapping("products")
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(name = "page",defaultValue = "0")int page){
        Pageable pageable = PageRequest.of(page,2,Sort.by(Sort.Direction.ASC,"id"));
        List<Product> list = productRepository.findAll(pageable).stream().collect(Collectors.toList());
        return new ResponseEntity(list,HttpStatus.OK);
    }

    @PostMapping("/units/{unitId}/products")
    public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product,
                                              @PathVariable(name = "unitId") String id){
        if(productRepository.existsById(product.getId())){
            throw new IllegalArgumentException("Da ton tai id nay");
        }
        Product p = unitRepository.findById(id)
                .map(x->{
                   product.setUnit(x);
                   return productRepository.save(product);
                })
                .orElseThrow(()->new ResourceNotFoundException("Khong tim thay id nay"));
        return new ResponseEntity(p,HttpStatus.CREATED);
    }
    @PutMapping("units/{unitId}/products/{productId}")
    public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product product,
                                                 @PathVariable(name = "unitId") String unitId,
                                                 @PathVariable(name = "productId") String productId){
        Unit u = unitRepository.findById(unitId).
                orElseThrow(()->new ResourceNotFoundException("Khong tim thay id"));
        Product p = productRepository.findById(productId).map(x->{
            x.setUnit(u);
            x.setStatus(product.isStatus());
            x.setName(product.getName());
            return productRepository.save(x);
        }).orElseThrow(()->new ResourceNotFoundException("Khong ton tai id nay"));
        return new ResponseEntity<>(p,HttpStatus.OK);
    }
    @DeleteMapping("units/{unitId}/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable("unitId") String unitId,
                                           @PathVariable("productId") String productId){
        if(!unitRepository.existsById(unitId)){
            throw new ResourceNotFoundException("Khong ton tai id nay");
        }
        return productRepository.findById(productId)
                .map(x->{
                    productRepository.delete(x);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(()->new ResourceNotFoundException("Khong ton tai id nay"));
    }
}
