package com.devinhouse.pharma.repository;

import com.devinhouse.pharma.model.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Integer> {
}
