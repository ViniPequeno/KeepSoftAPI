package com.br.nescaupower.KeepSoftAPI.Models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "perfil")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class Perfil implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private com.br.nescaupower.KeepSoftAPI.Enum.Perfil perfil;

    private String dataInicioFormat;
    private String dataFimFormat;
    
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataInicio;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataFim;

    @ManyToOne
    private Projeto projeto;
    @ManyToOne
    private Usuario usuario;

    public Perfil() {
        id = new Long(0);
    }

    public Perfil(Long id, Date dataInicio, Date dataFim) {
        this.id = id;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataInicio() {

        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public com.br.nescaupower.KeepSoftAPI.Enum.Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(com.br.nescaupower.KeepSoftAPI.Enum.Perfil perfil) {
        this.perfil = perfil;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDataInicioFormat() {
        return dataInicioFormat;
    }

    public void setDataInicioFormat(String dataInicioFormat) {
        this.dataInicioFormat = dataInicioFormat;
    }

    public String getDataFimFormat() {
        return dataFimFormat;
    }

    public void setDataFimFormat(String dataFimFormat) {
        this.dataFimFormat = dataFimFormat;
    }
}
