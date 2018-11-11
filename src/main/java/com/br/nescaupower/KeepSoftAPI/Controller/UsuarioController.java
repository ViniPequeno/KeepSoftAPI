/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.nescaupower.KeepSoftAPI.Controller;

import com.br.nescaupower.KeepSoftAPI.Exception.ResourceNotFoundException;
import com.br.nescaupower.KeepSoftAPI.Models.Auxiliares.AlterarSenha;
import com.br.nescaupower.KeepSoftAPI.Models.Usuario;
import com.br.nescaupower.KeepSoftAPI.Repository.UsuarioRepository;
import com.br.nescaupower.KeepSoftAPI.Utils.GeneratedHashPassword;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vinic
 */
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @GetMapping("{id}")
    public Usuario getUsuario(@PathVariable(value = "id") Long usuarioId) {
        return (Usuario) usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", usuarioId));
    }

    @GetMapping("imagem/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> getUsuarioImagem(HttpServletRequest request, @PathVariable(value = "id") Long usuarioId) {
        try {
            HttpHeaders headers = new HttpHeaders();
            File file = new File("imagens/pedro.png");
            InputStream is = new BufferedInputStream(new FileInputStream(file));
            String mimeType = URLConnection.guessContentTypeFromStream(is);
            headers.setContentType(MediaType.valueOf(mimeType));
            return new ResponseEntity<byte[]>(getImagem(is), headers, HttpStatus.OK);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private byte[] getImagem(InputStream in) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int len;

        // read bytes from the input stream and store them in buffer
        while ((len = in.read(buffer)) != -1) {
            // write bytes from the buffer into output stream
            os.write(buffer, 0, len);
        }

        return os.toByteArray();
    }

    @GetMapping("/getByLogin/{login}")
    public Usuario getUsuarioByLogin(@PathVariable(value = "login") String login) {
        return (Usuario) usuarioRepository.findByLogin(login);
    }

    @GetMapping("/getByEmail/{email}")
    public Usuario getUsuarioByEmail(@PathVariable(value = "email") String email) {
        return (Usuario) usuarioRepository.findByEmail(email);
    }

    @GetMapping("/getByLoginOrName/{search}/{id}")
    public List<Usuario> getUsuarioByLoginOrName(@PathVariable(value = "search") String serach,
            @PathVariable(value = "id") Long id) {
        return usuarioRepository.findByLoginOrName("%" + serach + "%", "%" + serach + "%", id);
    }

    @PostMapping
    public ResponseEntity<Usuario> inserirUsuario(@Valid @RequestBody Usuario usuario) {
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
            @Valid @RequestBody Usuario usuarioUpdate) {
        Usuario usuario = usuarioRepository.findById(usuarioId).
                orElseThrow(() -> new ResourceNotFoundException("Usuario", "usuario", usuarioId));

        usuario.setEmail(usuarioUpdate.getEmail());
        usuario.setNome(usuarioUpdate.getNome());
        usuario.setTelefone(usuarioUpdate.getTelefone());
        usuario.setReceiverEmail(usuarioUpdate.isReceiverEmail());
        usuario.setReceiverNotification(usuarioUpdate.isReceiverNotification());
        usuario.setImagem(usuarioUpdate.getImagem());
        try {
            File file = new File("imagens/" + usuario.getLogin() + ".png");
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(usuario.getImagem());
            FileDescriptor fd = fos.getFD();
            fos.flush();
            fd.sync();
            fos.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ResponseEntity.ok(usuarioRepository.save(usuario));
    }

    @PutMapping("/alterarSenha")
    public ResponseEntity<Usuario> atualizarSenhaUsuario(@Valid @RequestBody AlterarSenha alterarSenha) {
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
    public ResponseEntity<?> deleteUsuario(@PathVariable(value = "id") Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", usuarioId));

        usuarioRepository.delete(usuario);

        return ResponseEntity.ok().build();
    }
}
