/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.nescaupower.KeepSoftAPI.Repository;

import com.br.nescaupower.KeepSoftAPI.Models.Convite;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author developer
 */
public interface ConviteRepository extends JpaRepository<Convite, Long>{
    
    @Query(value = "SELECT * FROM convite c WHERE c.codProjeto= ?1 AND c.remetenteId= ?2 "
            + "AND c.destinatarioId= ?3", nativeQuery = true)
    public List<Convite> findByProjetoUsuarios(Long projeto, Long remetente, Long destinario);
    
    @Query(value = "SELECT * FROM convite c WHERE c.codProjeto= ?1 AND c.destinatarioId= ?2 ", nativeQuery = true)
    public List<Convite> findByProjetoUsuariosDestinario(Long projeto,  Long destinario);
    
    @Query(value = "SELECT * FROM convite c WHERE c.codProjeto= ?1", nativeQuery = true)
    public List<Convite> findByProjeto(Long projeto);
    
    @Query(value = "SELECT * FROM convite c WHERE c.destinatarioId= ?1", nativeQuery = true)
    public List<Convite> findByReceiverID(Long destinario);
}
