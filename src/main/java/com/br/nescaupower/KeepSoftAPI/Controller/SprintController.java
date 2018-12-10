/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.nescaupower.KeepSoftAPI.Controller;

import com.br.nescaupower.KeepSoftAPI.Exception.ResourceNotFoundException;
import com.br.nescaupower.KeepSoftAPI.Models.Sprint;
import com.br.nescaupower.KeepSoftAPI.Repository.SprintRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@RequestMapping("/api/sprint")
public class SprintController {

    @Autowired
    SprintRepository sprintRepository;

    @GetMapping
    public List<Sprint> getAllSprints() {
        return sprintRepository.findAll();
    }

    @GetMapping("/{id}")
    public Sprint getSprint(@PathVariable(value = "id") Long sprintId) {
        return (Sprint) sprintRepository.findById(sprintId)
                .orElseThrow(() -> new ResourceNotFoundException("Sprint", "id", sprintId));
    }

    @GetMapping("/findByTitulo/{titulo}")
    public Sprint getSprint(@PathVariable(value = "titulo") String titulo) {
        return sprintRepository.findByTitulo(titulo);
    }

    @GetMapping("/findByProjectID/{projetoId}")
    public List<Sprint> findByProjectID(@PathVariable(value = "projetoId") Long projetoId) {
        return sprintRepository.findByProjectID(projetoId);
    }
    
    @GetMapping("/getCountInProject/{projetoId}")
    public int getCountInProject(@PathVariable(value = "projetoId") Long projetoId) {
        return sprintRepository.getCountInProject(projetoId);
    }
    
    @GetMapping("/findTitlesByProject/{id}")
    public List<String> findTitlesByProject(@PathVariable(value = "id") Long projetoId) {
        return sprintRepository.findNamesByProjeto(projetoId);
    }

    @PostMapping
    public ResponseEntity<Sprint> inserirSprint(@Valid @RequestBody Sprint sprint) {
        if (sprintRepository.isExist(sprint.getProjeto().getCodigo(), sprint.getTitulo()) == null) {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            try {
                sprint.setDataInicio(formato.parse(sprint.getDataInicioFormat()));
                sprint.setDataFim(formato.parse(sprint.getDataFimFormat()));
            } catch (ParseException ex) {
                Logger.getLogger(SprintController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return ResponseEntity.ok(sprintRepository.save(sprint));
        } else {
            return null;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sprint> atualizarSprint(@PathVariable(value = "id") Long sprintId,
            @Valid @RequestBody Sprint sprintUpdate) {
        if (sprintRepository.isExist(sprintUpdate.getProjeto().getCodigo(), 
                sprintUpdate.getTitulo(), sprintUpdate.getId()) == null) {
            Sprint sprint = sprintRepository.findById(sprintId).
                    orElseThrow(() -> new ResourceNotFoundException("Sprint", "sprint", sprintId));

            sprint.setDataInicioFormat(sprintUpdate.getDataInicioFormat());
            sprint.setDataFimFormat(sprintUpdate.getDataFimFormat());

            sprint.setDataInicio(sprintUpdate.getDataInicio());
            sprint.setDataFim(sprintUpdate.getDataFim());
            sprint.setDescricao(sprintUpdate.getDescricao());
            sprint.setTitulo(sprintUpdate.getTitulo());
            sprint.setProjeto(sprintUpdate.getProjeto());

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            try {
                sprint.setDataInicio(formato.parse(sprintUpdate.getDataInicioFormat()));
                sprint.setDataFim(formato.parse(sprintUpdate.getDataFimFormat()));
            } catch (ParseException ex) {
                Logger.getLogger(SprintController.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Editou "+sprint.getTitulo());
            return ResponseEntity.ok(sprintRepository.save(sprint));
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSprint(@PathVariable(value = "id") Long sprintId) {
        Sprint sprint = sprintRepository.findById(sprintId)
                .orElseThrow(() -> new ResourceNotFoundException("Sprint", "id", sprintId));

        sprintRepository.delete(sprint);

        return ResponseEntity.ok().build();
    }
}
