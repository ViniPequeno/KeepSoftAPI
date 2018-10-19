/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.nescaupower.KeepSoftAPI.Controller;

import com.br.nescaupower.KeepSoftAPI.Exception.ResourceNotFoundException;
import com.br.nescaupower.KeepSoftAPI.Models.Convite;
import com.br.nescaupower.KeepSoftAPI.Repository.ConviteRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;
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
@RequestMapping("/api/convite")
public class ConviteController {

    @Autowired
    ConviteRepository conviteRepository;

    @GetMapping
    public List<Convite> getAllConvitres() {
        return conviteRepository.findAll();
    }

    @GetMapping("/{id}")
    public Convite getConvite(@PathVariable(value = "id") Long conviteId) {
        return (Convite) conviteRepository.findById(conviteId)
                .orElseThrow(() -> new ResourceNotFoundException("Convite", "id", conviteRepository));
    }

    @GetMapping("/findByProjeto/{projeto}")
    public List<Convite> findByProjeto(@PathVariable(value = "projeto") Long projeto) {
        return conviteRepository.findByProjeto(projeto);
    }

    @GetMapping("/findByReceiverID/{receiver}")
    public List<Convite> findByReceiverID(@PathVariable(value = "receiver") Long receiver) {
        return conviteRepository.findByReceiverID(receiver);
    }

    @GetMapping("/findByProjetoUsuariosDestinario/{projeto}/{destinario}")
    public Convite findByProjetoUsuariosDestinario(@PathVariable(value = "projeto") Long projeto,
            @PathVariable(value = "destinario") Long destinario) {
        return conviteRepository.findByProjetoUsuariosDestinario(projeto, destinario);
    }

    @GetMapping("/findByProjetoUsuarios/{projeto}/{remetente}/{destinario}")
    public List<Convite> findByProjetoUsuarios(@PathVariable(value = "projeto") Long projeto,
            @PathVariable(value = "remetente") Long remetente,
            @PathVariable(value = "destinario") Long destinario) {
        return conviteRepository.findByProjetoUsuarios(projeto, remetente, destinario);
    }

    @PostMapping
    public ResponseEntity<Convite> inserirConvite(@Valid @RequestBody Convite projeto) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss'Z'");
        format.setTimeZone(TimeZone.getTimeZone("GMT-4:00"));
        try {
            projeto.setData(format.parse(projeto.getDataFormat()));
        } catch (ParseException ex) {
        }
        System.out.println(projeto.getDataFormat());
        return ResponseEntity.ok(conviteRepository.save(projeto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Convite> atualizarConvite(@PathVariable(value = "id") Long conviteId,
            @Valid @RequestBody Convite conviteUpdate) {
        Convite convite = conviteRepository.findById(conviteId).
                orElseThrow(() -> new ResourceNotFoundException("Convite", "convite", conviteId));

        convite.setData(conviteUpdate.getData());
        convite.setCodProjeto(conviteUpdate.getCodProjeto());
        convite.setDestinatarioId(conviteUpdate.getDestinatarioId());
        convite.setRemetenteId(conviteUpdate.getRemetenteId());
        convite.setFuncao(conviteUpdate.getFuncao());

        return ResponseEntity.ok(conviteRepository.save(convite));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteConvite(@PathVariable(value = "id") Long conviteId) {
        Convite projeto = conviteRepository.findById(conviteId)
                .orElseThrow(() -> new ResourceNotFoundException("Convite", "id", conviteId));

        System.out.println("COnvite: " + projeto.getId());
        conviteRepository.delete(projeto);

        return ResponseEntity.ok().build();
    }
}
