/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.nescaupower.KeepSoftAPI.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author vinic
 */
@Entity
@Table(name ="projeto")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class Projeto implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long codigo;
    private String nome;
    private String descricao;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataCriacao;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataFinalizacao;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataPrevFinalizacao;
    
    @ManyToOne
    private Usuario usuarioAdm;

    public Projeto() {
    }

    public Projeto(long codigo, String nome, String descricao, Date dataCriacao, Date dataFinalizacao, Date dataPrevFinalizacao, Usuario usuarioAdm) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.dataFinalizacao = dataFinalizacao;
        this.dataPrevFinalizacao = dataPrevFinalizacao;
        this.usuarioAdm = usuarioAdm;
    }

    
    
    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataFinalizacao() {
        return dataFinalizacao;
    }

    public void setDataFinalizacao(Date dataFinalizacao) {
        this.dataFinalizacao = dataFinalizacao;
    }

    public Date getDataPrevFinalizacao() {
        return dataPrevFinalizacao;
    }

    public void setDataPrevFinalizacao(Date dataPrevFinalizacao) {
        this.dataPrevFinalizacao = dataPrevFinalizacao;
    }

    public Usuario getUsuarioAdm() {
        return usuarioAdm;
    }

    public void setUsuarioAdm(Usuario usuarioAdm) {
        this.usuarioAdm = usuarioAdm;
    }
    
    
    
}
