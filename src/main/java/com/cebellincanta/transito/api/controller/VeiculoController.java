package com.cebellincanta.transito.api.controller;

import com.cebellincanta.transito.domain.exception.NegocioExpection;
import com.cebellincanta.transito.domain.model.Proprietario;
import com.cebellincanta.transito.domain.model.Veiculo;
import com.cebellincanta.transito.domain.repository.VeiculoRepository;
import com.cebellincanta.transito.domain.service.RegistroVeiculoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoRepository veiculoRepository;
    private final RegistroVeiculoService registroVeiculoService;

    @GetMapping
    public List<Veiculo> listar()
    {
        return veiculoRepository.findAll();
    }

    @GetMapping("/{veiculoId}")
    public ResponseEntity<Veiculo> buscar(@PathVariable Long veiculoId) {
        return veiculoRepository.findById(veiculoId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Veiculo cadastrar(@Valid @RequestBody Veiculo veiculo) {
        return registroVeiculoService.cadastrar(veiculo);
    }

    @PutMapping("/{veiculoId}")
    public ResponseEntity<Veiculo> atualizar(@Valid @PathVariable Long veiculoId,
                                                  @RequestBody Veiculo veiculo) {
        if (!veiculoRepository.existsById(veiculoId)){
            return ResponseEntity.notFound().build();
        }
        veiculo.setId(veiculoId);
        Veiculo veiculoAtualizar = veiculoRepository.save(veiculo);

        return ResponseEntity.ok(veiculoAtualizar);
    }

    @DeleteMapping ("/{veiculoId}")
    public ResponseEntity<Void> delete(@PathVariable Long veiculoId) {
        if (!veiculoRepository.existsById(veiculoId)){
            return ResponseEntity.notFound().build();
        }
        veiculoRepository.deleteById(veiculoId);

        return ResponseEntity.noContent().build();
    }


}
