package br.edu.utfpr.tdsapi.tdsapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tipolançamento")
public class TipoLançamento {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long codigotl;
    @NotNull
    private int receita;
    @NotNull
    private int despesa;
    
    public Long getcodigotl() {
        return codigotl;
    }
    public void setcodigotl(Long codigotl) {
        this.codigotl = codigotl;
    }
    public int getReceita() {
        return receita;
    }
    public void setReceita(int receita) {
        this.receita = receita;
    }
    public int getDespesa() {
        return despesa;
    }
    public void setDespesa(int despesa) {
        this.despesa = despesa;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigotl == null) ? 0 : codigotl.hashCode());
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
        TipoLançamento other = (TipoLançamento) obj;
        if (codigotl == null) {
            if (other.codigotl != null)
                return false;
        } else if (!codigotl.equals(other.codigotl))
            return false;
        return true;
    }

    
    
}
