/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.nescaupower.KeepSoftAPI.Controller;

import com.br.nescaupower.KeepSoftAPI.Exception.ResourceNotFoundException;
import com.br.nescaupower.KeepSoftAPI.Models.Usuario;
import com.br.nescaupower.KeepSoftAPI.Repository.UsuarioRepository;
import com.br.nescaupower.KeepSoftAPI.Utils.GeneratedHashPassword;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vinic
 */
@RestController
@RequestMapping("/api")
public class UsuarioController  {
    @Autowired
    UsuarioRepository usuarioRepository;
    
    @GetMapping("/usuarios")
    public List<Usuario> getAllUsuarios(){
        return usuarioRepository.findAll();
    }
    
    @PostMapping("/usuarios")
    public Usuario inserirUsuario(@Valid @RequestBody Usuario usuario){
        try {
            usuario.setSenha(GeneratedHashPassword.generateHash(usuario.getSenha()));   
            return usuarioRepository.save(usuario);
        } catch (NoSuchAlgorithmException ex) {
            return null;
        }
    }
}
