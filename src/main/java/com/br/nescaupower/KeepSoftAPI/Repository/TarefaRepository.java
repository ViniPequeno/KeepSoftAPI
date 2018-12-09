/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.nescaupower.KeepSoftAPI.Repository;

import com.br.nescaupower.KeepSoftAPI.Models.Tarefa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


/**
 *
 * @author developer
 */
public interface TarefaRepository extends JpaRepository<Tarefa, Long>{
    @Query(value = "SELECT * FROM tarefa t INNER JOIN perfil p "
            + "ON t.perfil_id = p.id WHERE p.projeto_codigo = ?", nativeQuery = true)
    public List<Tarefa> findByProjeto(Long id);
    
    @Query(value = "SELECT * FROM tarefa t where t.sprint_id = ?", nativeQuery = true)
    public List<Tarefa> findBySprint(Long sprintId);
    
    @Query(value = "SELECT * FROM tarefa t INNER JOIN perfil p "
            + "ON t.perfil_id = p.id WHERE p.projeto_codigo =? AND t.titulo =?", nativeQuery = true)
    public Tarefa isExist(Long projeto, String titulo);
    
    @Query(value = "SELECT * FROM tarefa t INNER JOIN perfil p "
            + "ON t.perfil_id = p.id WHERE p.projeto_codigo =? AND t.titulo =? AND t.id <> ?", nativeQuery = true)
    public Tarefa isExist(Long projeto, String titulo, Long id);
}
