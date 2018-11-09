/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.nescaupower.KeepSoftAPI.Controller;

import com.br.nescaupower.KeepSoftAPI.Exception.ResourceNotFoundException;
import com.br.nescaupower.KeepSoftAPI.Models.Reuniao;
import com.br.nescaupower.KeepSoftAPI.Models.ReuniaoUsuario;
import com.br.nescaupower.KeepSoftAPI.Models.Usuario;
import com.br.nescaupower.KeepSoftAPI.Repository.ReuniaoRepository;
import com.br.nescaupower.KeepSoftAPI.Repository.ReuniaoUsuarioRepository;
import com.br.nescaupower.KeepSoftAPI.Repository.UsuarioRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
@RequestMapping("/api/reuniao")
public class ReuniaoController {
    
    @Autowired
    ReuniaoRepository reuniaoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    
    @Autowired
    ReuniaoUsuarioRepository reuniaoUsuarioRepository;
    
    @GetMapping
    public List<Reuniao> getAllReuniaos(){
        return reuniaoRepository.findAll();
    }
    
    @GetMapping("findByProjectID/{id}")
    public List<Reuniao> findByProjectID(@PathVariable(value = "id") Long projetoId){
        return reuniaoRepository.findByProjeto(projetoId);
    }
    
    @GetMapping("getUsuariosNotReuniao/{id}/")
    public List<Usuario> findUsuariosReunion(@PathVariable(value = "id") Long id){
        return null;
    }
    
        
    @GetMapping("getUsuariosNotReuniao/{id}/{login}")
    public List<Usuario> findUsuariosReunion(@PathVariable(value = "id") Long id, 
            @PathVariable(value = "login") String login){
        return usuarioRepository.getUsuariosNotReuniao("%"+login+"%", id);
    }

    @GetMapping("usuario/{id}")
    public List<Usuario> findUsuario(@PathVariable(value = "id") Long id){
//        Reuniao reuniao = reuniaoRepository.findById1(id);
        List<ReuniaoUsuario> list = reuniaoUsuarioRepository.findUsuario(id);
        List<Usuario> usuarios = new ArrayList<>();
        for(ReuniaoUsuario reuniaoUsuario : list){
           Usuario usuario = reuniaoUsuario.getUsuario();
           usuarios.add(usuario);
        }
        
        return usuarios;
    }
    
    @GetMapping("/{id}")
    public Reuniao getReuniao(@PathVariable(value = "id") Long reuniaoId){
        return (Reuniao) reuniaoRepository.findById(reuniaoId)
                .orElseThrow(() -> new ResourceNotFoundException("Reuniao", "id", reuniaoId));
    }
    
    
    @PostMapping
    public ResponseEntity<Reuniao> inserirReuniao(@Valid @RequestBody Reuniao reuniao){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (!reuniao.getDataInicioFormat().equals("")) {
                reuniao.setDataInicio(format.parse(reuniao.getDataInicioFormat()));
            }
        } catch (ParseException ex) {
        }
        return ResponseEntity.ok(reuniaoRepository.save(reuniao));
    }
    
    @PostMapping("/usuario")
    public ResponseEntity<ReuniaoUsuario> inserirReuniaoUsuario(@Valid @RequestBody ReuniaoUsuario reuniao){
        return ResponseEntity.ok(reuniaoUsuarioRepository.save(reuniao));
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Reuniao> atualizarReuniao(@PathVariable(value = "id") Long reuniaoId, 
            @Valid @RequestBody Reuniao reuniaoUpdate){
        Reuniao reuniao = reuniaoRepository.findById(reuniaoId).
                orElseThrow(() -> new ResourceNotFoundException("Reuniao", "reuniao", reuniaoId));
        
        reuniao.setAssunto(reuniaoUpdate.getAssunto());
        reuniao.setNome(reuniaoUpdate.getNome());
        reuniao.setResumo(reuniaoUpdate.getResumo());
        reuniao.setRealizada(reuniaoUpdate.isRealizada());
        reuniao.setHoraInicioFormat(reuniaoUpdate.getHoraInicioFormat());
        reuniao.setHoraFimFormat(reuniaoUpdate.getHoraFimFormat());
        reuniao.setLocal(reuniaoUpdate.getLocal());
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (!reuniao.getDataInicioFormat().equals("")) {
                reuniao.setDataInicio(format.parse(reuniao.getDataInicioFormat()));
            }
        } catch (ParseException ex) {
        }
        
        
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
