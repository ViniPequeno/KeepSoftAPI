/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.nescaupower.KeepSoftAPI.Controller;

import com.br.nescaupower.KeepSoftAPI.Exception.ResourceNotFoundException;
import com.br.nescaupower.KeepSoftAPI.Models.TarefaStatus;
import com.br.nescaupower.KeepSoftAPI.Repository.TarefaStatusRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@RequestMapping("/api/tarefasStatus")
public class TarefaStatusController {
    
    @Autowired
    TarefaStatusRepository tarefasStatusRepository;
    
    @GetMapping
    public List<TarefaStatus> getAllTarefaStatuss(){
        return tarefasStatusRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public TarefaStatus getTarefaStatus(@PathVariable(value = "id") Long tarefaStatusId){
        return (TarefaStatus) tarefasStatusRepository.findById(tarefaStatusId)
                .orElseThrow(() -> new ResourceNotFoundException("TarefaStatus", "id", tarefaStatusId));
    }
    
    @GetMapping("/findCuurentStatusOfTarefa/{tarefaId}")
    public TarefaStatus findCuurentStatusOfTarefa(@PathVariable(value = "tarefaId") Long tarefaId){
        return (TarefaStatus) tarefasStatusRepository.findCuurentStatusOfTarefa(tarefaId);
    }
    
    @GetMapping("/findByTarefa/{tarefaId}")
    public List<TarefaStatus> findByTarefa(@PathVariable(value = "tarefaId") Long tarefaId){
        return (List<TarefaStatus>) tarefasStatusRepository.findByTarefa(tarefaId);
    }
    
    @GetMapping("/findByStatus/{statusId}")
    public List<TarefaStatus> findByStatus(@PathVariable(value = "statusId") Long statusId){
        return (List<TarefaStatus>) tarefasStatusRepository.findByStatus(statusId);
    }
    
    @PostMapping
    public ResponseEntity<TarefaStatus> inserirTarefaStatus(@Valid @RequestBody TarefaStatus tarefaStatus){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            if (!tarefaStatus.getDataInicioFormat().equals("")) {
                tarefaStatus.setDataInicio(format.parse(tarefaStatus.getDataInicioFormat()));
            }
            if (!tarefaStatus.getDataFimFormat().equals("")) {
                tarefaStatus.setDataFim(format.parse(tarefaStatus.getDataFimFormat()));
            }
        } catch (ParseException ex) {
        }
        return ResponseEntity.ok(tarefasStatusRepository.save(tarefaStatus));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<TarefaStatus> atualizarTarefaStatus(@PathVariable(value = "id") Long tarefaStatusId, 
            @Valid @RequestBody TarefaStatus tarefaStatusUpdate){
        TarefaStatus tarefaStatus = tarefasStatusRepository.findById(tarefaStatusId).
                orElseThrow(() -> new ResourceNotFoundException("TarefaStatus", "tarefaStatus", tarefaStatusId));
        
        tarefaStatus.setTarefa(tarefaStatusUpdate.getTarefa());
        tarefaStatus.setStatus(tarefaStatusUpdate.getStatus());
        tarefaStatus.setDataFimFormat(tarefaStatusUpdate.getDataFimFormat());
        
        
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            if (!tarefaStatusUpdate.getDataInicioFormat().equals("")) {
                tarefaStatus.setDataInicio(format.parse(tarefaStatusUpdate.getDataInicioFormat()));
            }
            if (!tarefaStatusUpdate.getDataFimFormat().equals("")) {
                tarefaStatus.setDataFim(format.parse(tarefaStatusUpdate.getDataFimFormat()));
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return ResponseEntity.ok(tarefasStatusRepository.save(tarefaStatus));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTarefaStatus(@PathVariable(value = "id")  Long tarefaId){
        TarefaStatus tarefa = tarefasStatusRepository.findById(tarefaId)
                .orElseThrow(() -> new ResourceNotFoundException("TarefaStatus", "id", tarefaId));
        
        tarefasStatusRepository.delete(tarefa);
        
        return  ResponseEntity.ok().build();
    }
}
