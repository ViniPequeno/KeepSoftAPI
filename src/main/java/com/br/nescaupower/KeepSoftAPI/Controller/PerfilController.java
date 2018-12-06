/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.nescaupower.KeepSoftAPI.Controller;

import com.br.nescaupower.KeepSoftAPI.Exception.ResourceNotFoundException;
import com.br.nescaupower.KeepSoftAPI.Models.Perfil;
import com.br.nescaupower.KeepSoftAPI.Repository.PerfilRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
@RequestMapping("/api/perfil")
public class PerfilController {

    @Autowired
    PerfilRepository perfilRepository;

    @GetMapping
    public List<Perfil> getAllConvites() {
        return perfilRepository.findAll();
    }

    @GetMapping("/{id}")
    public Perfil getPerfil(@PathVariable(value = "id") Long perfilId) {
        return (Perfil) perfilRepository.findById(perfilId)
                .orElseThrow(() -> new ResourceNotFoundException("Perfil", "id", perfilRepository));
    }

    @GetMapping("/findByProjeto/{projeto}")
    public List<Perfil> findByProjeto(@PathVariable(value = "projeto") Long projeto) {
        return perfilRepository.findByProjeto(projeto);
    }

    @GetMapping("/findByUserIdAndProjectID/{projeto}/{usuario}")
    public Perfil findByUserIdAndProjectID(@PathVariable(value = "projeto") Long projeto,
            @PathVariable(value = "usuario") Long usuario) {
        return perfilRepository.findByUserIdAndProjectID(projeto, usuario);
    }

    @PostMapping
    public ResponseEntity<Perfil> inserirPerfil(@Valid @RequestBody Perfil perfil) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (!perfil.getDataInicioFormat().equals("")) {
                perfil.setDataInicio(format.parse(perfil.getDataInicioFormat()));
            }
        } catch (ParseException ex) {
        }
        return ResponseEntity.ok(perfilRepository.save(perfil));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Perfil> atualizarPerfil(@PathVariable(value = "id") Long perfilId,
            @Valid @RequestBody Perfil perfilUpdate) {
        Perfil perfil = perfilRepository.findById(perfilId).
                orElseThrow(() -> new ResourceNotFoundException("Perfil", "perfil", perfilId));

        perfil.setDataInicioFormat(perfilUpdate.getDataInicioFormat());
        perfil.setDataFimFormat(perfil.getDataFimFormat());
        
        perfil.setPerfil(perfilUpdate.getPerfil());
        perfil.setProjeto(perfilUpdate.getProjeto());
        perfil.setUsuario(perfilUpdate.getUsuario());
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("Perfil: "+perfil.getDataInicioFormat()+" oi");
        try {
            if (!perfil.getDataInicioFormat().equals("")) {
                perfil.setDataInicio(format.parse(perfil.getDataInicioFormat()));
            }
            if (!perfil.getDataFimFormat().equals("")) {
                perfil.setDataFim(format.parse(perfil.getDataFimFormat()));
            }
        } catch (ParseException ex) {
        }

        return ResponseEntity.ok(perfilRepository.save(perfil));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerfil(@PathVariable(value = "id") Long projetoId) {
        Perfil projeto = perfilRepository.findById(projetoId)
                .orElseThrow(() -> new ResourceNotFoundException("Perfil", "id", projetoId));

        perfilRepository.delete(projeto);

        return ResponseEntity.ok().build();
    }
}
