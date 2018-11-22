/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.nescaupower.KeepSoftAPI.Repository;

import com.br.nescaupower.KeepSoftAPI.Models.Auxiliares.QuantidadeNotificacoes;
import com.br.nescaupower.KeepSoftAPI.Models.Convite;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author developer
 */
public interface ConviteRepository extends JpaRepository<Convite, Long>{
    
    @Query(value = "SELECT * FROM convite c WHERE c.projeto_codigo= ?1 AND c.remetente_id_id= ?2 "
            + "AND c.destinatarioId= ?3", nativeQuery = true)
    public List<Convite> findByProjetoUsuarios(Long projeto, Long remetente, Long destinario);
    
    @Query(value = "SELECT * FROM convite c WHERE c.projeto_codigo= ?1 AND c.destinatario_id_id= ?2 ", nativeQuery = true)
    public Convite findByProjetoUsuariosDestinario(Long projeto,  Long destinario);
    
    @Query(value = "SELECT * FROM convite c WHERE c.projeto_codigo= ?1", nativeQuery = true)
    public List<Convite> findByProjeto(Long projeto);
    
    @Query(value = "SELECT * FROM convite c WHERE c.destinatario_id_id= ?1", nativeQuery = true)
    public List<Convite> findByReceiverID(Long destinario);
    
    @Query(value = "SELECT * FROM convite c WHERE c.destinatario_id_id= ? AND c.visto = 0", nativeQuery = true)
    public List<Convite> findByReceiverIDNotVistos(Long destinario);
    
    @Query(value = "SELECT count(*) as quantidade FROM convite c WHERE c.destinatario_id_id= ? AND c.visto = 0", nativeQuery = true)
    public int findNotVistos(Long destinario);
}
