package br.edu.utfpr.tdsapi.tdsapi.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
public class Endereço {

    @NotNull @Size(min = 3, max = 60)
    private String logradouro;
    @NotNull @Size(min = 1, max = 20)
    private String numero;
    @NotNull @Size(min = 3, max = 50)
    private String complemento;
    @Size(min = 3, max = 30)
    private String bairro;
    @NotNull @Size(min = 3, max = 10)
    private String cep;
    @NotNull @Size(min = 3, max = 25)
    private String cidade;
    @NotNull @Size(min = 3, max = 25)
    private String estado;
    
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
}