package br.edu.utfpr.tdsapi.tdsapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name ="endereço")
public class Endereço {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoe;
    @NotNull @Size(min = 3, max = 60)
    private String logradouro;
    @NotNull @Size(min = 1, max = 20)
    private String numero;
    @NotNull @Size(min = 3, max = 50)
    private String complemento;
    @NotNull @Size(min = 3, max = 30)
    private String bairro;
    @NotNull @Size(min = 3, max = 10)
    private String cep;
    @NotNull @Size(min = 3, max = 25)
    private String cidade;
    @NotNull @Size(min = 3, max = 25)
    private String estado;
    private Long codigope;
    
    public Long getcodigoe() {
        return codigoe;
    }
    public void setcodigoe(Long codigoe) {
        this.codigoe = codigoe;
    }
    public String getLogradouro() {
        return logradouro;
    }
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getComplemento() {
        return complemento;
    }
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public Long getcodigope() {
        return codigope;
    }
    public void setcodigope(Long codigope) {
        this.codigope = codigope;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigoe == null) ? 0 : codigoe.hashCode());
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
        Endereço other = (Endereço) obj;
        if (codigoe == null) {
            if (other.codigoe != null)
                return false;
        } else if (!codigoe.equals(other.codigoe))
            return false;
        return true;
    }

    
}
