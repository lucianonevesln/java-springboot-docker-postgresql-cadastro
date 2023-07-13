package br.com.lucianoneves.api.cadastro.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CadastroDto {
    @NotBlank
    @Size(max = 100)
    private String nome;
    @NotBlank
    @Size(max = 100)
    @Email
    private String email;

    public String getNome() {
        return nome;
    }

    public void setName(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
