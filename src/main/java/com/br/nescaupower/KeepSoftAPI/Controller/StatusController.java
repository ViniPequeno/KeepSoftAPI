/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.nescaupower.KeepSoftAPI.Controller;

import com.br.nescaupower.KeepSoftAPI.Exception.ResourceNotFoundException;
import com.br.nescaupower.KeepSoftAPI.Models.Status;
import com.br.nescaupower.KeepSoftAPI.Repository.StatusRepository;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author developer
 */
@RestController
@RequestMapping("/api/statuss")
public class StatusController {
    
    @Autowired
    StatusRepository statusRepository;
    
    @GetMapping
    public List<Status> getAllStatuss(){
        return statusRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Status getStatus(@PathVariable(value = "id") Long statusId){
        return (Status) statusRepository.findById(statusId)
                .orElseThrow(() -> new ResourceNotFoundException("Status", "id", statusId));
    }
    
    @PostMapping
    public ResponseEntity<Status> inserirStatus(@Valid @RequestBody Status status){
        return ResponseEntity.ok(statusRepository.save(status));
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Status> atualizarStatus(@PathVariable(value = "id") Long statusId, 
            @Valid @RequestBody Status statusUpdate){
        Status status = statusRepository.findById(statusId).
                orElseThrow(() -> new ResourceNotFoundException("Status", "status", statusId));
        
        
        status.setDescricao(statusUpdate.getDescricao());
        status.setNome(statusUpdate.getNome());
        
        
        
        return ResponseEntity.ok(statusRepository.save(status));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStatus(@PathVariable(value = "id")  Long statusId){
        Status status = statusRepository.findById(statusId)
                .orElseThrow(() -> new ResourceNotFoundException("Status", "id", statusId));
        
        statusRepository.delete(status);
        
        return  ResponseEntity.ok().build();
    }
}