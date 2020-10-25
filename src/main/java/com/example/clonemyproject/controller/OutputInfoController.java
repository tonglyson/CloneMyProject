package com.example.clonemyproject.controller;

import com.example.clonemyproject.entities.OutputInfo;
import com.example.clonemyproject.entities.Product;
import com.example.clonemyproject.error.ResourceNotFoundException;
import com.example.clonemyproject.repository.InputInfoRepository;
import com.example.clonemyproject.repository.InputRepository;
import com.example.clonemyproject.repository.OutputInfoRepository;
import com.example.clonemyproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.executable.ValidateOnExecution;
import java.util.List;
@Transactional
@RestController
public class OutputInfoController {
    @Autowired
    private OutputInfoRepository outputInfoRepository;
    @Autowired
    private InputInfoRepository inputInfoRepository;
    @Autowired
    private ProductRepository productRepository;
    @GetMapping("outputInfos")
    public ResponseEntity<List<OutputInfo>> getAll(){
        List<OutputInfo> list = outputInfoRepository.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @PostMapping("product/{productId}/inputInfo/{id}/outputInfos")
    public ResponseEntity<OutputInfo> add(@Valid @RequestBody OutputInfo outputInfo,
                                          @PathVariable("productId") String productId,
                                          @PathVariable("id") int id
                                         ){
        Product p = productRepository.findById(productId).orElseThrow();
        OutputInfo o = inputInfoRepository.findById(id).map(x->{
            outputInfo.setProductOutput(p);
            outputInfo.setInputInfo(x);
           return outputInfoRepository.save(outputInfo);
        }).orElseThrow(()->new ResourceNotFoundException("Khong tim thay id"));
        return new ResponseEntity<>(o,HttpStatus.CREATED);
    }
    @DeleteMapping("outputInfos/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        return outputInfoRepository.findById(id).map(o->{
            outputInfoRepository.delete(o);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException("Khong tim thay id"));
    }
}
