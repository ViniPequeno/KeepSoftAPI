/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.nescaupower.KeepSoftAPI.Repository;

import com.br.nescaupower.KeepSoftAPI.Models.TarefaStatus;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author developer
 */
public interface TarefaStatusRepository extends JpaRepository<TarefaStatus, Long>{
    
}