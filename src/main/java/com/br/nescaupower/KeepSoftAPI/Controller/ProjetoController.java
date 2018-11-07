/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.nescaupower.KeepSoftAPI.Controller;

import com.br.nescaupower.KeepSoftAPI.Exception.ResourceNotFoundException;
import com.br.nescaupower.KeepSoftAPI.Models.Perfil;
import com.br.nescaupower.KeepSoftAPI.Models.Projeto;
import com.br.nescaupower.KeepSoftAPI.Repository.ProjetoRepository;
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
import com.br.nescaupower.KeepSoftAPI.Repository.PerfilRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vinic
 */
@RestController
@RequestMapping("/api/projeto")
public class ProjetoController {

    @Autowired
    ProjetoRepository projetoRepository;

    @Autowired
    PerfilRepository perfillRepository;

    @GetMapping
    public List<Projeto> getAllProjetos() {
        return projetoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Projeto getProjeto(@PathVariable(value = "id") Long projetoId) {
        return (Projeto) projetoRepository.findById(projetoId)
                .orElseThrow(() -> new ResourceNotFoundException("Projeto", "id", projetoId));
    }

    @GetMapping("/findByName/{name}")
    public Projeto findByName(@PathVariable(value = "name") String name) {
        return projetoRepository.findByName(name);
    }

    @GetMapping("/findByUserID/{id}")
    public List<Projeto> findByUserID(@PathVariable(value = "id") Long id) {
        return projetoRepository.findByUserID(id);
    }

    @GetMapping("/findByParticipantingUserID/{id}")
    public List<Projeto> findByParticipantingUserID(@PathVariable(value = "id") Long id) {
        return projetoRepository.findByParticipantingUserID(id);
    }

    @PostMapping
    public ResponseEntity<Projeto> inserirProjeto(@Valid @RequestBody Projeto projeto) {
        System.out.println(projeto.toString());
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        try {
            projeto.setDataCriacao(formato.parse(projeto.getDataCriacaoFormat()));
            projeto.setDataPrevFinalizacao(formato.parse(projeto.getDataPrevFinalizacaoFormat()));
        } catch (ParseException ex) {
        }
        return ResponseEntity.ok(projetoRepository.save(projeto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Projeto> atualizarProjeto(@PathVariable(value = "id") Long projetoId,
            @Valid @RequestBody Projeto projetoUpdate) {
        Projeto projeto = projetoRepository.findById(projetoId).
                orElseThrow(() -> new ResourceNotFoundException("Projeto", "projeto", projetoId));

        projeto.setDataCriacaoFormat(projetoUpdate.getDataCriacaoFormat());
        projeto.setDataFinalizacaoFormat(projetoUpdate.getDataFinalizacaoFormat());
        projeto.setDataPrevFinalizacaoFormat(projetoUpdate.getDataPrevFinalizacaoFormat());
        projeto.setDataFinalizacao(projetoUpdate.getDataFinalizacao());
        projeto.setDataPrevFinalizacao(projetoUpdate.getDataPrevFinalizacao());
        projeto.setDescricao(projetoUpdate.getDescricao());
        projeto.setNome(projetoUpdate.getNome());
        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        try {
            projeto.setDataCriacao(formato.parse(projetoUpdate.getDataCriacaoFormat()));
            projeto.setDataPrevFinalizacao(formato.parse(projetoUpdate.getDataPrevFinalizacaoFormat()));
        } catch (ParseException ex) {
        }

        return ResponseEntity.ok(projetoRepository.save(projeto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProjeto(@PathVariable(value = "id") Long projetoId) {
        Projeto projeto = projetoRepository.findById(projetoId)
                .orElseThrow(() -> new ResourceNotFoundException("Projeto", "id", projetoId));

        projetoRepository.delete(projeto);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/equipe")
    public ResponseEntity<Perfil> integrarEquipe(@Valid @RequestBody Perfil perfil) {
        return ResponseEntity.ok(perfillRepository.save(perfil));
    }
}
