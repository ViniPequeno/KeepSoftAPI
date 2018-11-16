/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.nescaupower.KeepSoftAPI.Repository;

import com.br.nescaupower.KeepSoftAPI.Models.TarefaStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author developer
 */
public interface TarefaStatusRepository extends JpaRepository<TarefaStatus, Long> {

    @Query(value = "SELECT * FROM tarefa_status WHERE tarefa_id = ? AND data_fim is null", nativeQuery = true)
    public TarefaStatus findStatusOfTarefa(Long tarefaId);

    @Query(value = "SELECT * FROM tarefa_status WHERE tarefa_id = ?", nativeQuery = true)
    public List<TarefaStatus> findByTarefa(Long tarefaId);

    @Query(value = "SELECT * FROM tarefa_status WHERE status_id = ?", nativeQuery = true)
    public List<TarefaStatus> findByStatus(Long statusId);
}
