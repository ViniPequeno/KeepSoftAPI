/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.nescaupower.KeepSoftAPI.Controller;

import com.br.nescaupower.KeepSoftAPI.Models.Auxiliares.Login;
import com.br.nescaupower.KeepSoftAPI.Models.Usuario;
import com.br.nescaupower.KeepSoftAPI.Repository.UsuarioRepository;
import com.br.nescaupower.KeepSoftAPI.Utils.GeneratedHashPassword;
import java.security.NoSuchAlgorithmException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author developer
 */
@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<Usuario> signIn(@Valid @RequestBody Login login) {
        try {
            Usuario usuario = usuarioRepository.findByLogin(login.getLogin());
            login.setSenha(GeneratedHashPassword.generateHash(login.getSenha()));
            if (usuario != null) {
                if (usuario.getSenha().equals(login.getSenha())) {
                    return ResponseEntity.ok(usuario);
                } else {
                    return ResponseEntity.ok(null);
                }
            }else{
                return ResponseEntity.ok(null);
            }
        } catch (NoSuchAlgorithmException ex) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
