/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.nescaupower.KeepSoftAPI.Repository;

import com.br.nescaupower.KeepSoftAPI.Models.Perfil;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author vinic
 */
public interface PerfilRepository extends JpaRepository<Perfil, Long> {

    @Query(value = "SELECT * FROM perfil p WHERE p.projeto= ?1", nativeQuery = true)
    public List<Perfil> findByProjeto(Long projeto);

    @Query(value = "SELECT * FROM perfil p WHERE p.codProjeto= ? and p.idUsuario= ?",
            nativeQuery = true)
    public Perfil findByUserIdAndProjectID(Long projeto, Long usuario);
}
