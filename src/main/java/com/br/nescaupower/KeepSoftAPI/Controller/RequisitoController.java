/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.nescaupower.KeepSoftAPI.Controller;

import com.br.nescaupower.KeepSoftAPI.Exception.ResourceNotFoundException;
import com.br.nescaupower.KeepSoftAPI.Models.Requisito;
import com.br.nescaupower.KeepSoftAPI.Repository.PerfilRepository;
import com.br.nescaupower.KeepSoftAPI.Repository.RequisitoRepository;
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
public class RequisitoController {
    @Autowired
    RequisitoRepository requisitoRepository;
    
    @GetMapping
    public List<Requisito> getAllRequisitos(){
        return requisitoRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Requisito getRequisito(@PathVariable(value = "id") Long requisitoId){
        return (Requisito) requisitoRepository.findById(requisitoId)
                .orElseThrow(() -> new ResourceNotFoundException("Requisito", "id", requisitoId));
    }
    
    @PostMapping
    public ResponseEntity<Requisito> inserirRequisito(@Valid @RequestBody Requisito requisito){
        return ResponseEntity.ok(requisitoRepository.save(requisito));
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Requisito> atualizarRequisito(@PathVariable(value = "id") Long requisitoId, 
            @Valid @RequestBody Requisito requisitoUpdate){
        Requisito requisito = requisitoRepository.findById(requisitoId).
                orElseThrow(() -> new ResourceNotFoundException("Requisito", "requisito", requisitoId));
        
        requisito.setDificuldade(requisitoUpdate.getDificuldade());
        requisito.setPrioridade(requisitoUpdate.getPrioridade());
        requisito.setDescricao(requisitoUpdate.getDescricao());
        requisito.setNome(requisitoUpdate.getNome());
        
        
        
        return ResponseEntity.ok(requisitoRepository.save(requisito));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRequisito(@PathVariable(value = "id")  Long requisitoId){
        Requisito requisito = requisitoRepository.findById(requisitoId)
                .orElseThrow(() -> new ResourceNotFoundException("Requisito", "id", requisitoId));
        
        requisitoRepository.delete(requisito);
        
        return  ResponseEntity.ok().build();
    }
    
}
