package br.edu.utfpr.tdsapi.tdsapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "categoria")
public class Categoria {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoc;

    @NotNull
    @Size(min = 3, max = 45)
    private String nome;

    public Long getcodigoc() {
        return codigoc;
    }

    public void setcodigoc(Long codigoc) {
        this.codigoc = codigoc;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigoc == null) ? 0 : codigoc.hashCode());
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
        Categoria other = (Categoria) obj;
        if (codigoc == null) {
            if (other.codigoc != null)
                return false;
        } else if (!codigoc.equals(other.codigoc))
            return false;
        return true;
    }

}
