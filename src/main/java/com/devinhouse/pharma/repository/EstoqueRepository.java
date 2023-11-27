package com.devinhouse.pharma.repository;

import com.devinhouse.pharma.model.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueRepository extends JpaRepository<Estoque, Integer> {
}
