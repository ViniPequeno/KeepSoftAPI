/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.nescaupower.KeepSoftAPI.Repository;

import com.br.nescaupower.KeepSoftAPI.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author vinic
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{}