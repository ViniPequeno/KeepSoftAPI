/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.nescaupower.KeepSoftAPI.Repository;

import com.br.nescaupower.KeepSoftAPI.Models.Reuniao;
import com.br.nescaupower.KeepSoftAPI.Models.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author developer
 */
public interface ReuniaoRepository extends JpaRepository<Reuniao, Long> {

    @Query(value = "SELECT * FROM reuniao r WHERE r.projeto_codigo = ? AND r.realizada = 0", nativeQuery = true)
    public List<Reuniao> findByProjeto(Long id);

    @Query(value = "SELECT * FROM usuario u WHERE u.id != ? AND u.id NOT IN (SELECT u1.id FROM usuario u1 INNER JOIN reuniao_usuario r ON r.usuario_id WHERE r.reuniao_id = ?2)", nativeQuery = true)
    public List<Usuario> findUsuariosReunion(Long id, Long ReuniaoId);

    @Query(value = "SELECT u1.id, u1.login, u1.email, u1.nome, '', u1.telefone, u1.receiver_email, u1.receiver_notification FROM usuario u1 INNER JOIN reuniao_usuario r ON r.usuario_id WHERE r.reuniao_id = ?", nativeQuery = true)
    public List<Usuario> findUsuario(Long id);

}
