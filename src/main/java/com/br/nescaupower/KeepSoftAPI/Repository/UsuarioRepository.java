/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.nescaupower.KeepSoftAPI.Repository;

import com.br.nescaupower.KeepSoftAPI.Models.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author vinic
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
    @Query (value = "SELECT * FROM usuario u WHERE u.id = ?", nativeQuery = true)
    public Usuario findById1(Long id);
    
    @Query(value = "select * from Usuario where login = ?", nativeQuery = true)
    public Usuario findByLogin(String login);
    
    @Query(value = "select * from Usuario where email = ?", nativeQuery = true)
    public Usuario findByEmail(String email);
    
    @Query(value = "SELECT * FROM usuario WHERE (login LIKE ? OR nome LIKE ?) AND id <> ?",
            nativeQuery = true)
    public List<Usuario> findByLoginOrName(String search, String search2, Long id);
    
    @Query(value = "SELECT * FROM usuario u WHERE u.login LIKE ? AND "
            + "u.id NOT IN (SELECT u1.id FROM usuario u1 "
            + "INNER JOIN reuniao_usuario r ON r.usuario_id = u1.id WHERE r.reuniao_id = ?)", nativeQuery = true)
    public List<Usuario> getUsuariosNotReuniao(String login, Long id);
}
