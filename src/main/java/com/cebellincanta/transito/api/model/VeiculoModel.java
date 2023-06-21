package com.cebellincanta.transito.api.model;

import com.cebellincanta.transito.domain.model.StatusVeiculo;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class VeiculoModel {

    private Long id;
    private String nomeProprietario;
    private String marca;
    private String modelo;
    private String placa;
    private StatusVeiculo status;
    private OffsetDateTime dataCadastro;
    private OffsetDateTime dataApreensao;

}
