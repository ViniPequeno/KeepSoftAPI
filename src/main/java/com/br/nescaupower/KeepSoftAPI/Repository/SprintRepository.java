/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.nescaupower.KeepSoftAPI.Repository;

import com.br.nescaupower.KeepSoftAPI.Models.Perfil;
import com.br.nescaupower.KeepSoftAPI.Models.Sprint;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author developer
 */
public interface SprintRepository extends JpaRepository<Sprint, Long>{
    @Query(value ="SELECT * FROM sprint s WHERE s.titulo = ?", nativeQuery = true)
    public Sprint findByTitulo(String titulo);
    
    @Query(value ="SELECT * FROM sprint s WHERE s.projeto_codigo= ?", nativeQuery = true)
    public List<Sprint> findByProjectID(Long projeto);
    
    @Query(value = "SELECT * FROM sprint s WHERE s.projeto_codigo = ? AND s.titulo = ?", nativeQuery = true)
    public Sprint isExist(Long projeto, String titulo);
    
    @Query(value = "SELECT * FROM sprint s WHERE s.projeto_codigo = ? AND s.titulo = ? AND s.titulo <> ?", nativeQuery = true)
    public Sprint isExist(Long projeto, String titulo, Long id);
}
