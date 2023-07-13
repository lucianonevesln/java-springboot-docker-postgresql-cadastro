package br.com.lucianoneves.api.cadastro.services;

import br.com.lucianoneves.api.cadastro.models.CadastroModel;
import br.com.lucianoneves.api.cadastro.repositories.CadastroRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CadastroService {
    final CadastroRepository cadastroRepository;

    public CadastroService(CadastroRepository cadastroRepository) {
        this.cadastroRepository = cadastroRepository;
    }

    @Transactional
    public CadastroModel save(CadastroModel cadastroModel){
        return cadastroRepository.save(cadastroModel);
    }

    public boolean existsEmail(String email){
        return cadastroRepository.existsByEmail(email);
    }

    public List<CadastroModel> findAll() {
        return cadastroRepository.findAll();
    }

    public Optional<CadastroModel> findById(UUID id) {
        return cadastroRepository.findById(id);
    }

    @Transactional
    public void delete(CadastroModel cadastroModel) {
        cadastroRepository.delete(cadastroModel);
    }

}
