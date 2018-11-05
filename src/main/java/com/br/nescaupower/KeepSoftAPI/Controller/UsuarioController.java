/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.nescaupower.KeepSoftAPI.Controller;

import com.br.nescaupower.KeepSoftAPI.Exception.ResourceNotFoundException;
import com.br.nescaupower.KeepSoftAPI.Models.Auxiliares.AlterarSenha;
import com.br.nescaupower.KeepSoftAPI.Models.Auxiliares.Login;
import com.br.nescaupower.KeepSoftAPI.Models.Usuario;
import com.br.nescaupower.KeepSoftAPI.Repository.UsuarioRepository;
import com.br.nescaupower.KeepSoftAPI.Utils.GeneratedHashPassword;
import java.security.NoSuchAlgorithmException;
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
 * @author vinic
 */
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController  {
    @Autowired
    UsuarioRepository usuarioRepository;
    
    @GetMapping
    public List<Usuario> getAllUsuarios(){
        return usuarioRepository.findAll();
    }
    
    @GetMapping("{id}")
    public Usuario getUsuario(@PathVariable(value = "id") Long usuarioId){
        return (Usuario) usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", usuarioId));
    }
    
    @GetMapping("/getByLogin/{login}")
    public Usuario getUsuarioByLogin(@PathVariable(value = "login") String login){
        return (Usuario) usuarioRepository.findByLogin(login);
    }
    
    @GetMapping("/getByEmail/{email}")
    public Usuario getUsuarioByEmail(@PathVariable(value = "email") String email){
        return (Usuario) usuarioRepository.findByEmail(email);
    }
        
    @GetMapping("/getByLoginOrName/{search}/{id}")
    public List<Usuario> getUsuarioByLoginOrName(@PathVariable(value = "search") String serach,
            @PathVariable(value = "id") Long id){
        return usuarioRepository.findByLoginOrName("%"+serach+"%", "%"+serach+"%", id);
    }
    
    
    @PostMapping
    public ResponseEntity<Usuario> inserirUsuario(@Valid @RequestBody Usuario usuario){
        try {
            usuario.setSenha(GeneratedHashPassword.generateHash(usuario.getSenha()));   
            return ResponseEntity.ok(usuarioRepository.save(usuario));
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }
    
    

    
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable(value = "id") Long usuarioId, 
            @Valid @RequestBody Usuario usuarioUpdate){
        Usuario usuario = usuarioRepository.findById(usuarioId).
                orElseThrow(() -> new ResourceNotFoundException("Usuario", "usuario", usuarioId));
        
        usuario.setEmail(usuarioUpdate.getEmail());
        usuario.setNome(usuarioUpdate.getNome());
        usuario.setTelefone(usuarioUpdate.getTelefone());
        usuario.setReceiverEmail(usuarioUpdate.isReceiverEmail());
        usuario.setReceiverNotification(usuarioUpdate.isReceiverNotification());
        
        return ResponseEntity.ok(usuarioRepository.save(usuario));
    }
    
    @PutMapping("/alterarSenha")
    public ResponseEntity<Usuario> atualizarSenhaUsuario(@Valid @RequestBody AlterarSenha alterarSenha){
        Usuario usuario = usuarioRepository.findById(alterarSenha.getId()).
                orElseThrow(() -> new ResourceNotFoundException("Usuario", "usuario", alterarSenha.getId()));

        
        try {
            alterarSenha.setSenha(GeneratedHashPassword.generateHash(alterarSenha.getSenha()));
            
            usuario.setSenha(alterarSenha.getSenha());

            return ResponseEntity.ok(usuarioRepository.save(usuario));
        } catch (NoSuchAlgorithmException ex) {
            return ResponseEntity.badRequest().body(null);
        }
        
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable(value = "id")  Long usuarioId){
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", usuarioId));
        
        usuarioRepository.delete(usuario);
        
        return  ResponseEntity.ok().build();
    }
}
