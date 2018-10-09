/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.nescaupower.KeepSoftAPI.Controller;

import com.br.nescaupower.KeepSoftAPI.Exception.ResourceNotFoundException;
import com.br.nescaupower.KeepSoftAPI.Models.Sprint;
import com.br.nescaupower.KeepSoftAPI.Repository.SprintRepository;
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
@RequestMapping("/api/sprints")
public class SprintController {
    @Autowired
    SprintRepository sprintRepository;
    
    @GetMapping
    public List<Sprint> getAllSprints(){
        return sprintRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Sprint getSprint(@PathVariable(value = "id") Long sprintId){
        return (Sprint) sprintRepository.findById(sprintId)
                .orElseThrow(() -> new ResourceNotFoundException("Sprint", "id", sprintId));
    }
    
    @PostMapping
    public ResponseEntity<Sprint> inserirSprint(@Valid @RequestBody Sprint sprint){
        return ResponseEntity.ok(sprintRepository.save(sprint));
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Sprint> atualizarSprint(@PathVariable(value = "id") Long sprintId, 
            @Valid @RequestBody Sprint sprintUpdate){
        Sprint sprint = sprintRepository.findById(sprintId).
                orElseThrow(() -> new ResourceNotFoundException("Sprint", "sprint", sprintId));
        
        sprint.setDataInicio(sprintUpdate.getDataInicio());
        sprint.setDataFim(sprintUpdate.getDataFim());
        sprint.setDescricao(sprintUpdate.getDescricao());
        sprint.setTitulo(sprintUpdate.getTitulo());
        sprint.setProjeto(sprintUpdate.getProjeto());
        
        
        
        return ResponseEntity.ok(sprintRepository.save(sprint));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSprint(@PathVariable(value = "id")  Long sprintId){
        Sprint sprint = sprintRepository.findById(sprintId)
                .orElseThrow(() -> new ResourceNotFoundException("Sprint", "id", sprintId));
        
        sprintRepository.delete(sprint);
        
        return  ResponseEntity.ok().build();
    }
}