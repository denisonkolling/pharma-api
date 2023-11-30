package com.devinhouse.pharma.repository;

import com.devinhouse.pharma.model.Estoque;
import com.devinhouse.pharma.model.IdEstoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, IdEstoque> {

    List<Estoque> findAllByCnpj(Long cnpj);

    Estoque findByCnpjAndNroRegistro(Long cnpj, Integer nroRegistro);

}
