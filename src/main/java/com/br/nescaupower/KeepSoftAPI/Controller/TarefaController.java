/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.nescaupower.KeepSoftAPI.Controller;

import com.br.nescaupower.KeepSoftAPI.Exception.ResourceNotFoundException;
import com.br.nescaupower.KeepSoftAPI.Models.Tarefa;
import com.br.nescaupower.KeepSoftAPI.Repository.TarefaRepository;
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
@RequestMapping("/api/tarefas")
public class TarefaController {
    @Autowired
    TarefaRepository tarefaRepository;
    
    @GetMapping
    public List<Tarefa> getAllTarefas(){
        return tarefaRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Tarefa getTarefa(@PathVariable(value = "id") Long tarefaId){
        return (Tarefa) tarefaRepository.findById(tarefaId)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa", "id", tarefaId));
    }
    
    @PostMapping
    public ResponseEntity<Tarefa> inserirTarefa(@Valid @RequestBody Tarefa tarefa){
        return ResponseEntity.ok(tarefaRepository.save(tarefa));
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable(value = "id") Long tarefaId, 
            @Valid @RequestBody Tarefa tarefaUpdate){
        Tarefa tarefa = tarefaRepository.findById(tarefaId).
                orElseThrow(() -> new ResourceNotFoundException("Tarefa", "tarefa", tarefaId));
        
        tarefa.setDificuldade(tarefaUpdate.getDificuldade());
        tarefa.setPrioridade(tarefaUpdate.getPrioridade());
        tarefa.setDescricao(tarefaUpdate.getDescricao());
        tarefa.setTitulo(tarefaUpdate.getTitulo());
        
        
        
        return ResponseEntity.ok(tarefaRepository.save(tarefa));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTarefa(@PathVariable(value = "id")  Long tarefaId){
        Tarefa tarefa = tarefaRepository.findById(tarefaId)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa", "id", tarefaId));
        
        tarefaRepository.delete(tarefa);
        
        return  ResponseEntity.ok().build();
    }
    
}