package br.com.lucianoneves.api.cadastro.repositories;

import br.com.lucianoneves.api.cadastro.models.CadastroModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CadastroRepository extends JpaRepository<CadastroModel, UUID> {
    public boolean existsByEmail(String email);

}
