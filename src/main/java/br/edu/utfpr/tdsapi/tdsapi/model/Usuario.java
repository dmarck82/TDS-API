package br.edu.utfpr.tdsapi.tdsapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigou;
    private String nome;
    private String email;
    private String senha;
    private Long codigopeu;
    
    public Long getcodigou() {
        return codigou;
    }
    public void setcodigou(Long codigou) {
        this.codigou = codigou;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public Long getcodigopeu() {
        return codigopeu;
    }
    public void setcodigopeu(Long codigopeu) {
        this.codigopeu = codigopeu;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigou == null) ? 0 : codigou.hashCode());
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
        Usuario other = (Usuario) obj;
        if (codigou == null) {
            if (other.codigou != null)
                return false;
        } else if (!codigou.equals(other.codigou))
            return false;
        return true;
    }

    
}
