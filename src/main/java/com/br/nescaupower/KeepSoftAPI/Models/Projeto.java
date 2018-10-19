/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.nescaupower.KeepSoftAPI.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
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
    private Long codigo;
    private String nome;
    private String descricao;
    private String dataCriacaoFormat;
    private String dataFinalizacaoFormat;
    private String dataPrevFinalizacaoFormat;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataCriacao;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataFinalizacao;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataPrevFinalizacao;
    
    
    @ManyToOne
    private Usuario usuarioAdm;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Perfil> perfils;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sprint> sprints;

    public Projeto() {
    }

    public Projeto(Long codigo, String nome, String descricao, Date dataCriacao, Date dataFinalizacao, Date dataPrevFinalizacao, Usuario usuarioAdm) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.dataFinalizacao = dataFinalizacao;
        this.dataPrevFinalizacao = dataPrevFinalizacao;
        this.usuarioAdm = usuarioAdm;
    }

    
    
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
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

    public List<Perfil> getPerfils() {
        return perfils;
    }

    public void setPerfils(List<Perfil> perfils) {
        this.perfils = perfils;
    }

    public List<Sprint> getSprints() {
        return sprints;
    }

    public void setSprints(List<Sprint> sprints) {
        this.sprints = sprints;
    }

    public String getDataCriacaoFormat() {
        return dataCriacaoFormat;
    }

    public void setDataCriacaoFormat(String dataCriacaoFormat) {
        this.dataCriacaoFormat = dataCriacaoFormat;
    }

    public String getDataFinalizacaoFormat() {
        return dataFinalizacaoFormat;
    }

    public void setDataFinalizacaoFormat(String dataFinalizacaoFormat) {
        this.dataFinalizacaoFormat = dataFinalizacaoFormat;
    }

    public String getDataPrevFinalizacaoFormat() {
        return dataPrevFinalizacaoFormat;
    }

    public void setDataPrevFinalizacaoFormat(String dataPrevFinalizacaoFormat) {
        this.dataPrevFinalizacaoFormat = dataPrevFinalizacaoFormat;
    }
    
    
    
    
    
}
