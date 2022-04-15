package br.edu.utfpr.tdsapi.tdsapi.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="lancamento")
public class Lançamento {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigol;
    @NotNull @Size(min = 3, max = 45)
    private String descricao;
    @NotNull
    private Date datavencimento;
    @NotNull
    private Date datapagamento;
    @NotNull
    private Float valor;
    @Size(min = 3, max = 50)
    private String observacao;
    private Long codigocl;
    private Long codigotll;
    private Long codigopl;
    
    public Long getcodigol() {
        return codigol;
    }
    public void setcodigol(Long codigol) {
        this.codigol = codigol;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Date getDatavencimento() {
        return datavencimento;
    }
    public void setDatavencimento(Date datavencimento) {
        this.datavencimento = datavencimento;
    }
    public Date getDatapagamento() {
        return datapagamento;
    }
    public void setDatapagamento(Date datapagamento) {
        this.datapagamento = datapagamento;
    }
    public Float getValor() {
        return valor;
    }
    public void setValor(Float valor) {
        this.valor = valor;
    }
    public String getObservacao() {
        return observacao;
    }
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    public Long getcodigocl() {
        return codigocl;
    }
    public void setcodigocl(Long codigocl) {
        this.codigocl = codigocl;
    }
    public Long getCodigoTLL() {
        return codigotll;
    }
    public void setCodigoTLL(Long codigotll) {
        this.codigotll = codigotll;
    }
    public Long getCodigoPL() {
        return codigopl;
    }
    public void setCodigoPL(Long codigopl) {
        this.codigopl = codigopl;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigocl == null) ? 0 : codigocl.hashCode());
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
        if (codigocl == null) {
            if (other.codigocl != null)
                return false;
        } else if (!codigocl.equals(other.codigocl))
            return false;
        return true;
    }

}
