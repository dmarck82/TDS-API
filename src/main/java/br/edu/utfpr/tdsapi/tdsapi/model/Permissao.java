package br.edu.utfpr.tdsapi.tdsapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "permissao")
public class Permissao {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigope;
    private String descricao;
    public Long getcodigope() {
        return codigope;
    }
    public void setcodigope(Long codigope) {
        this.codigope = codigope;
    }
    public String getdescricao() {
        return descricao;
    }
    public void setdescricao(String descricao) {
        this.descricao = descricao;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigope == null) ? 0 : codigope.hashCode());
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
        Permissao other = (Permissao) obj;
        if (codigope == null) {
            if (other.codigope != null)
                return false;
        } else if (!codigope.equals(other.codigope))
            return false;
        return true;
    }

    
}
