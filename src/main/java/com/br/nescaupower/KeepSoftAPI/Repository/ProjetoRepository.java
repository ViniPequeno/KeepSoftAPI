/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.nescaupower.KeepSoftAPI.Repository;

import com.br.nescaupower.KeepSoftAPI.Models.Projeto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author vinic
 */
public interface ProjetoRepository extends JpaRepository<Projeto, Long>{
    @Query(value = "SELECT * FROM projeto p WHERE p.nome= ?1", nativeQuery = true)
    public Projeto findByName(String name);
    
    @Query(value = "SELECT * FROM projeto p WHERE p.usuario_adm_id = ?1", nativeQuery = true)
    public List<Projeto> findByUserID(Long id);
    
    @Query(value = "SELECT * FROM projeto p INNER JOIN perfil pe on p.codigo = pe.projeto_codigo "
            + " WHERE pe.usuario_id = ?1 AND pe.data_inicio_format != ''", nativeQuery = true)
    public List<Projeto> findByParticipantingUserID(Long userId);
}

