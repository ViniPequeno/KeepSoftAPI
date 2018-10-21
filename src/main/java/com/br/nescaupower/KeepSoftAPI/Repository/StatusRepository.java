/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.nescaupower.KeepSoftAPI.Repository;

import com.br.nescaupower.KeepSoftAPI.Models.Status;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author developer
 */
public interface StatusRepository extends JpaRepository<Status, Long>{
    
    @Query(value = "SELECT * FROM status WHERE projeto_codigo = ?", nativeQuery = true)
    List<Status> findByProjeto(Long projetoId);
    
}
