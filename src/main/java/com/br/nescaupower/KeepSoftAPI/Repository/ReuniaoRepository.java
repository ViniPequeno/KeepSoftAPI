/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.nescaupower.KeepSoftAPI.Repository;

import com.br.nescaupower.KeepSoftAPI.Models.Reuniao;
import com.br.nescaupower.KeepSoftAPI.Models.ReuniaoUsuario;
import com.br.nescaupower.KeepSoftAPI.Models.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author developer
 */
public interface ReuniaoRepository extends JpaRepository<Reuniao, Long> {

    @Query (value = "SELECT * FROM reuniao r WHERE r.id = ?", nativeQuery = true)
    public Reuniao findById1(Long id);
    
    @Query(value = "SELECT * FROM reuniao r WHERE r.projeto_codigo = ? AND r.realizada = 0", nativeQuery = true)
    public List<Reuniao> findByProjeto(Long id);
    
    @Query(value = "SELECT * FROM reuniao r WHERE r.projeto_codigo = ? AND r.nome = ?", nativeQuery = true)
    public Reuniao isExist(Long projetoId, String nome);

    @Query(value = "SELECT * FROM reuniao r WHERE r.projeto_codigo = ? AND r.nome = ? AND r.id <> ?", nativeQuery = true)
    public Reuniao isExist(Long projetoId, String nome, Long id);
    

}
