/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.nescaupower.KeepSoftAPI.Controller;

import com.br.nescaupower.KeepSoftAPI.Exception.ResourceNotFoundException;
import com.br.nescaupower.KeepSoftAPI.Models.Perfil;
import com.br.nescaupower.KeepSoftAPI.Models.Projeto;
import com.br.nescaupower.KeepSoftAPI.Models.Usuario;
import com.br.nescaupower.KeepSoftAPI.Repository.PerfillRepository;
import com.br.nescaupower.KeepSoftAPI.Repository.ProjetoRepository;
import java.security.NoSuchAlgorithmException;
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
 * @author vinic
 */
@RestController
@RequestMapping("/api")
public class ProjetoController {
    @Autowired
    ProjetoRepository projetoRepository;
    
    @Autowired
    PerfillRepository perfillRepository;

    @GetMapping("/projetos")
    public List<Projeto> getAllProjetos(){
        return projetoRepository.findAll();
    }
    
    @GetMapping("/projetos/{id}")
    public Projeto getProjeto(@PathVariable(value = "id") Long projetoId){
        return (Projeto) projetoRepository.findById(projetoId)
                .orElseThrow(() -> new ResourceNotFoundException("Projeto", "id", projetoId));
    }
    
    @PostMapping("/projetos")
    public ResponseEntity<Projeto> inserirProjeto(@Valid @RequestBody Projeto projeto){
        return ResponseEntity.ok(projetoRepository.save(projeto));
    }

    
    @PutMapping("/projetos/{id}")
    public ResponseEntity<Projeto> atualizarProjeto(@PathVariable(value = "id") Long projetoId, 
            @Valid @RequestBody Projeto projetoUpdate){
        Projeto projeto = projetoRepository.findById(projetoId).
                orElseThrow(() -> new ResourceNotFoundException("Projeto", "projeto", projetoId));
        
        projeto.setDataFinalizacao(projetoUpdate.getDataFinalizacao());
        projeto.setDataPrevFinalizacao(projetoUpdate.getDataPrevFinalizacao());
        projeto.setDescricao(projetoUpdate.getDescricao());
        projeto.setNome(projetoUpdate.getNome());
        
        
        return ResponseEntity.ok(projetoRepository.save(projeto));
    }
    
    @DeleteMapping("/projetos/{id}")
    public ResponseEntity<?> deleteProjeto(@PathVariable(value = "id")  Long projetoId){
        Projeto projeto = projetoRepository.findById(projetoId)
                .orElseThrow(() -> new ResourceNotFoundException("Projeto", "id", projetoId));
        
        projetoRepository.delete(projeto);
        
        return  ResponseEntity.ok().build();
    }
    
    @PostMapping("/projetos/equipe")
    public ResponseEntity<Perfil> integrarEquipe(@Valid @RequestBody Perfil perfil){
        return ResponseEntity.ok(perfillRepository.save(perfil));
    }
}
