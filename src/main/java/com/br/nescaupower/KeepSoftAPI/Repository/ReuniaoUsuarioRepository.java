/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.nescaupower.KeepSoftAPI.Repository;

import com.br.nescaupower.KeepSoftAPI.Models.Reuniao;
import com.br.nescaupower.KeepSoftAPI.Models.ReuniaoUsuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author developer
 */
public interface ReuniaoUsuarioRepository extends JpaRepository<ReuniaoUsuario, Long>{
    
    @Query(value = "SELECT * FROM reuniao_usuario WHERE reuniao_id = ?", nativeQuery = true)
    public List<ReuniaoUsuario> findUsuario(Long id);
    
    @Query(value = "SELECT * FROM reuniao_usuario WHERE reuniao_id =? AND usuario_id = ?", nativeQuery = true)
    public ReuniaoUsuario findReuniaoUsuario(Long reuniao, Long id);
}
