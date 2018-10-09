/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.nescaupower.KeepSoftAPI.Controller;

import com.br.nescaupower.KeepSoftAPI.Exception.ResourceNotFoundException;
import com.br.nescaupower.KeepSoftAPI.Models.RequisitoStatus;
import com.br.nescaupower.KeepSoftAPI.Repository.RequisitoStatusRepository;
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
@RequestMapping("/api/requisitos")
public class RequisitoStatusController {
    
    @Autowired
    RequisitoStatusRepository requisitosStatusRepository;
    
    @GetMapping
    public List<RequisitoStatus> getAllRequisitoStatuss(){
        return requisitosStatusRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public RequisitoStatus getRequisitoStatus(@PathVariable(value = "id") Long requisitoStatusId){
        return (RequisitoStatus) requisitosStatusRepository.findById(requisitoStatusId)
                .orElseThrow(() -> new ResourceNotFoundException("RequisitoStatus", "id", requisitoStatusId));
    }
    
    @PostMapping
    public ResponseEntity<RequisitoStatus> inserirRequisitoStatus(@Valid @RequestBody RequisitoStatus requisitoStatus){
        return ResponseEntity.ok(requisitosStatusRepository.save(requisitoStatus));
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<RequisitoStatus> atualizarRequisitoStatus(@PathVariable(value = "id") Long requisitoStatusId, 
            @Valid @RequestBody RequisitoStatus requisitoStatusUpdate){
        RequisitoStatus requisitoStatus = requisitosStatusRepository.findById(requisitoStatusId).
                orElseThrow(() -> new ResourceNotFoundException("RequisitoStatus", "requisitoStatus", requisitoStatusId));
        
        requisitoStatus.setDataInicio(requisitoStatusUpdate.getDataInicio());
        requisitoStatus.setDateFim(requisitoStatusUpdate.getDateFim());
        requisitoStatus.setRequisito(requisitoStatusUpdate.getRequisito());
        requisitoStatus.setStatus(requisitoStatusUpdate.getStatus());
        
        
        
        return ResponseEntity.ok(requisitosStatusRepository.save(requisitoStatus));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRequisitoStatus(@PathVariable(value = "id")  Long requisitoId){
        RequisitoStatus requisito = requisitosStatusRepository.findById(requisitoId)
                .orElseThrow(() -> new ResourceNotFoundException("RequisitoStatus", "id", requisitoId));
        
        requisitosStatusRepository.delete(requisito);
        
        return  ResponseEntity.ok().build();
    }
}
