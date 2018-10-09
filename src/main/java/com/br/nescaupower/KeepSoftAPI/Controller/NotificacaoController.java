/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.nescaupower.KeepSoftAPI.Controller;

import com.br.nescaupower.KeepSoftAPI.Exception.ResourceNotFoundException;
import com.br.nescaupower.KeepSoftAPI.Models.Notificacao;
import com.br.nescaupower.KeepSoftAPI.Repository.NotificacaoRepository;
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
@RequestMapping("/api/notificacao")
public class NotificacaoController {
    
    @Autowired
    NotificacaoRepository notificacaoRepository;
    
    @GetMapping
    public List<Notificacao> getAllConvitres(){
        return notificacaoRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Notificacao getNotificacao(@PathVariable(value = "id") Long notificacaoId){
        return (Notificacao) notificacaoRepository.findById(notificacaoId)
                .orElseThrow(() -> new ResourceNotFoundException("Notificacao", "id", notificacaoRepository));
    }
    
    @PostMapping
    public ResponseEntity<Notificacao> inserirNotificacao(@Valid @RequestBody Notificacao projeto){
        return ResponseEntity.ok(notificacaoRepository.save(projeto));
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Notificacao> atualizarNotificacao(@PathVariable(value = "id") Long notificacaoId, 
            @Valid @RequestBody Notificacao notificacaoUpdate){
        Notificacao notificacao = notificacaoRepository.findById(notificacaoId).
                orElseThrow(() -> new ResourceNotFoundException("Notificacao", "notificacao", notificacaoId));
        
        notificacao.setDataCriacao(notificacaoUpdate.getDataCriacao());
        notificacao.setUsuario(notificacaoUpdate.getUsuario());
        
        
        
        return ResponseEntity.ok(notificacaoRepository.save(notificacao));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNotificacao(@PathVariable(value = "id")  Long projetoId){
        Notificacao projeto = notificacaoRepository.findById(projetoId)
                .orElseThrow(() -> new ResourceNotFoundException("Notificacao", "id", projetoId));
        
        notificacaoRepository.delete(projeto);
        
        return  ResponseEntity.ok().build();
    }
    
}
