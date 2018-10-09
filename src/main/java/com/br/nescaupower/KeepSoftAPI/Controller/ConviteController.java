/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.nescaupower.KeepSoftAPI.Controller;

import com.br.nescaupower.KeepSoftAPI.Exception.ResourceNotFoundException;
import com.br.nescaupower.KeepSoftAPI.Models.Convite;
import com.br.nescaupower.KeepSoftAPI.Repository.ConviteRepository;
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
@RequestMapping("/api/convite")
public class ConviteController {
    
    @Autowired
    ConviteRepository conviteRepository;
    
    @GetMapping
    public List<Convite> getAllConvitres(){
        return conviteRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Convite getConvite(@PathVariable(value = "id") Long conviteId){
        return (Convite) conviteRepository.findById(conviteId)
                .orElseThrow(() -> new ResourceNotFoundException("Convite", "id", conviteRepository));
    }
    
    @PostMapping
    public ResponseEntity<Convite> inserirConvite(@Valid @RequestBody Convite projeto){
        return ResponseEntity.ok(conviteRepository.save(projeto));
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Convite> atualizarConvite(@PathVariable(value = "id") Long conviteId, 
            @Valid @RequestBody Convite conviteUpdate){
        Convite convite = conviteRepository.findById(conviteId).
                orElseThrow(() -> new ResourceNotFoundException("Convite", "convite", conviteId));
        
        convite.setData(conviteUpdate.getData());
        convite.setCodProjeto(conviteUpdate.getCodProjeto());
        convite.setDestinatarioId(conviteUpdate.getDestinatarioId());
        convite.setRemetenteId(conviteUpdate.getRemetenteId());
        convite.setFuncao(conviteUpdate.getFuncao());
        
        
        return ResponseEntity.ok(conviteRepository.save(convite));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteConvite(@PathVariable(value = "id")  Long conviteId){
        Convite projeto = conviteRepository.findById(conviteId)
                .orElseThrow(() -> new ResourceNotFoundException("Convite", "id", conviteId));
        
        conviteRepository.delete(projeto);
        
        return  ResponseEntity.ok().build();
    }
}