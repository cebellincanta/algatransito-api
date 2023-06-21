package com.cebellincanta.transito.domain.service;

import com.cebellincanta.transito.domain.exception.NegocioExpection;
import com.cebellincanta.transito.domain.model.Proprietario;
import com.cebellincanta.transito.domain.model.StatusVeiculo;
import com.cebellincanta.transito.domain.model.Veiculo;
import com.cebellincanta.transito.domain.repository.ProprietarioRepository;
import com.cebellincanta.transito.domain.repository.VeiculoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class RegistroVeiculoService {

    private VeiculoRepository veiculoRepository;
    private RegistroProprietarioService registroProprietarioService;
    public Veiculo cadastrar(Veiculo novoVeiculo){
        if( novoVeiculo.getId() != null)
        {
            throw new NegocioExpection("Veuclo a ser cadastrado nao deve possuir um código");
        }
        boolean placaEmUso = veiculoRepository.findByPlaca(novoVeiculo.getPlaca())
                .filter(p -> !p.equals(novoVeiculo))
                .isPresent();
        if(placaEmUso){
            throw new NegocioExpection("PLaca já existente");
        }
        novoVeiculo.setProprietario(registroProprietarioService.buscar(novoVeiculo.getProprietario().getId()));
        novoVeiculo.setStatus(StatusVeiculo.REGULAR);
        novoVeiculo.setDataCadastro(LocalDateTime.now());

        return veiculoRepository.save(novoVeiculo);
    }
}
