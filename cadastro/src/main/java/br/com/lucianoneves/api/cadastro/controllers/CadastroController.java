package br.com.lucianoneves.api.cadastro.controllers;

import br.com.lucianoneves.api.cadastro.dtos.CadastroDto;
import br.com.lucianoneves.api.cadastro.models.CadastroModel;
import br.com.lucianoneves.api.cadastro.services.CadastroService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/cadastro")
public class CadastroController {
    final CadastroService cadastroService;

    public CadastroController(CadastroService cadastroService) {
        this.cadastroService = cadastroService;
    }

    @PostMapping
    public ResponseEntity<Object> saveCadastro(@RequestBody @Valid CadastroDto cadastroDto){
        if (cadastroService.existsEmail(cadastroDto.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: e-mail já existe!");
        }
        CadastroModel cadastroModel= new CadastroModel();
        BeanUtils.copyProperties(cadastroDto, cadastroModel);
        cadastroModel.setDataDeCadastro(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(cadastroService.save(cadastroModel));
    }

    @GetMapping
    public ResponseEntity<List<CadastroModel>> getAllCadastros() {
        return ResponseEntity.status(HttpStatus.OK).body(cadastroService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneCadastro(@PathVariable(value = "id") UUID id) {
        Optional<CadastroModel> cadastroModelOptional = cadastroService.findById(id);
        if (cadastroModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(cadastroModelOptional.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cadastro não encontrado!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCadastro(@PathVariable(value = "id") UUID id) {
        Optional<CadastroModel> cadastroModelOptional = cadastroService.findById(id);
        if (cadastroModelOptional.isPresent()) {
            cadastroService.delete(cadastroModelOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body("Cadastro deletado com sucesso!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cadastro não encontrado!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCadastro(@PathVariable(value = "id") UUID id,
                                                 @RequestBody @Valid CadastroDto cadastroDto) {
        Optional<CadastroModel> cadastroModelOptional = cadastroService.findById(id);
        if (cadastroModelOptional.isPresent()) {
            CadastroModel cadastroModel = new CadastroModel();
            BeanUtils.copyProperties(cadastroDto, cadastroModel);
            cadastroModel.setId(cadastroModelOptional.get().getId());
            cadastroModel.setDataDeCadastro(cadastroModelOptional.get().getDataDeCadastro());
            return ResponseEntity.status(HttpStatus.OK).body(cadastroService.save(cadastroModel));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cadastro não encontrado!");
    }

}
