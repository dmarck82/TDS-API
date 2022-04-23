package br.edu.utfpr.tdsapi.tdsapi.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="lancamento")
public class Lançamento {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigol;

    private String descricao;

    @Column(name = "data_vencimento")
    private LocalDate datavencimento;

    @Column(name = "data_pagamento")
    private LocalDate datapagamento;

    private BigDecimal valor;

    private String observacao;

    @Enumerated(EnumType.STRING)
    private TipoLançamento tipo;

    @ManyToOne
    @JoinColumn(name = "codigo_categoria")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "codigo_pessoa")
    private Pessoa pessoa;
    
    public Long getCodigol() {
        return codigol;
    }
    public void setCodigol(Long codigol) {
        this.codigol = codigol;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public LocalDate getDatavencimento() {
        return datavencimento;
    }
    public void setDatavencimento(LocalDate datavencimento) {
        this.datavencimento = datavencimento;
    }
    public LocalDate getDatapagamento() {
        return datapagamento;
    }
    public void setDatapagamento(LocalDate datapagamento) {
        this.datapagamento = datapagamento;
    }
    public BigDecimal getValor() {
        return valor;
    }
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    public String getObservacao() {
        return observacao;
    }
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    public TipoLançamento getTipo() {
        return tipo;
    }
    public void setTipo(TipoLançamento tipo) {
        this.tipo = tipo;
    }
    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    public Pessoa getPessoa() {
        return pessoa;
    }
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigol == null) ? 0 : codigol.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Lançamento other = (Lançamento) obj;
        if (codigol == null) {
            if (other.codigol != null)
                return false;
        } else if (!codigol.equals(other.codigol))
            return false;
        return true;
    }

}
