package com.devinhouse.pharma.repository;

import com.devinhouse.pharma.model.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Integer> {
}
