package com.devinhouse.pharma.repository;

import com.devinhouse.pharma.model.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Integer> {

    List<Estoque> findAllByCnpj(Long cnpj);
}
