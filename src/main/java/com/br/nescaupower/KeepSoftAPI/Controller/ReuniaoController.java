/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.nescaupower.KeepSoftAPI.Controller;

import com.br.nescaupower.KeepSoftAPI.Exception.ResourceNotFoundException;
import com.br.nescaupower.KeepSoftAPI.Models.Reuniao;
import com.br.nescaupower.KeepSoftAPI.Repository.ReuniaoRepository;
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
@RequestMapping("/api/reuniaos")
public class ReuniaoController {
    
    @Autowired
    ReuniaoRepository reuniaoRepository;
    
    @GetMapping
    public List<Reuniao> getAllReuniaos(){
        return reuniaoRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Reuniao getReuniao(@PathVariable(value = "id") Long reuniaoId){
        return (Reuniao) reuniaoRepository.findById(reuniaoId)
                .orElseThrow(() -> new ResourceNotFoundException("Reuniao", "id", reuniaoId));
    }
    
    @PostMapping
    public ResponseEntity<Reuniao> inserirReuniao(@Valid @RequestBody Reuniao reuniao){
        return ResponseEntity.ok(reuniaoRepository.save(reuniao));
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Reuniao> atualizarReuniao(@PathVariable(value = "id") Long reuniaoId, 
            @Valid @RequestBody Reuniao reuniaoUpdate){
        Reuniao reuniao = reuniaoRepository.findById(reuniaoId).
                orElseThrow(() -> new ResourceNotFoundException("Reuniao", "reuniao", reuniaoId));
        
        reuniao.setAssunto(reuniaoUpdate.getAssunto());
        reuniao.setData(reuniaoUpdate.getData());
        reuniao.setNome(reuniaoUpdate.getNome());
        reuniao.setRelatorio(reuniaoUpdate.getRelatorio());
        
        
        return ResponseEntity.ok(reuniaoRepository.save(reuniao));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReuniao(@PathVariable(value = "id")  Long reuniaoId){
        Reuniao reuniao = reuniaoRepository.findById(reuniaoId)
                .orElseThrow(() -> new ResourceNotFoundException("Reuniao", "id", reuniaoId));
        
        reuniaoRepository.delete(reuniao);
        
        return  ResponseEntity.ok().build();
    }
}
