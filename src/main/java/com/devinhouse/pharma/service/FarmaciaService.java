package com.devinhouse.pharma.service;

import com.devinhouse.pharma.model.Farmacia;

import java.util.List;

public interface FarmaciaService {

    Farmacia cadastrarFarmacia(Farmacia farmacia);

    List<Farmacia> listarTodasFarmacias();

    Farmacia consultarFarmaciaPorCnpj(Long cnpj);
}
