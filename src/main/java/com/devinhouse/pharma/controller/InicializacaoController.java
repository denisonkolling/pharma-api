package com.devinhouse.pharma.controller;

import com.devinhouse.pharma.model.Endereco;
import com.devinhouse.pharma.model.Estoque;
import com.devinhouse.pharma.model.Farmacia;
import com.devinhouse.pharma.model.Medicamento;
import com.devinhouse.pharma.repository.EstoqueRepository;
import com.devinhouse.pharma.repository.FarmaciaRepository;
import com.devinhouse.pharma.repository.MedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.devinhouse.pharma.model.TipoMedicamento.COMUM;
import static com.devinhouse.pharma.model.TipoMedicamento.CONTROLADO;

@RestController
@RequestMapping("/inicializacao")
public class InicializacaoController {

    @Autowired
    private FarmaciaRepository farmaciaRepository;

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Autowired
    private EstoqueRepository estoqueRepository;

    @PostMapping
    public ResponseEntity<?> cargaInicialdeDados() {
        criarFarmacias();
        criarMedicamentos();
        criarEstoque();
        return new ResponseEntity<>(HttpStatus.OK);
    }


    public void criarFarmacias() {

        List<Farmacia> listaFarmacias = new ArrayList<>();

        Farmacia farmacia01 = new Farmacia(90561736000121L, "DevMed Ltda", "Farmácia DevMed", "devmed@farmacia.com", "(44)4444-4444", "(44)9444-4441", new Endereco(88888999L, "Rua Porto Real", 67, "Westeros", "Berlim", "SC", null, 15.23456, 2.8678687));
        Farmacia farmacia02 = new Farmacia(43178995000198L, "MedHouse Ltda", "Farmácia MedHouse", "medhouse@farmacia.com", "(55)5555-5555", "(45)95555-5555", new Endereco(8877799L, "Rua Madrid", 76, "Winterfell", "Estocolmo", "SC", null, 19.254356, 3.8987687));

        listaFarmacias.add(farmacia01);
        listaFarmacias.add(farmacia02);

        for (Farmacia farmacia : listaFarmacias) {
            Farmacia salvarFarmacia = farmaciaRepository.save(farmacia);
        }

    }

    public void criarMedicamentos() {

        List<Medicamento> listaMedicamentos = new ArrayList<>();

        Medicamento medicamento01 = new Medicamento(1010, "Programapan", "Matrix", "2x ao dia", "Lorem ipsum dolor sit amet, consectetur, adipiscing elit. Nunc eleifend", 11.00F, COMUM);
        Medicamento medicamento02 = new Medicamento(7473, "Cafex", "Colombia Farm", "4x ao dia", "Pellentesque non ultricies mauris, ut lobortis elit. Cras nec cursus nibh", 51.50F, COMUM);
        Medicamento medicamento03 = new Medicamento(2233, "Estomazol", "Acme", "1x ao dia", "Sed risus mauris, consectetur eget egestas vitae", 22.50F, COMUM);
        Medicamento medicamento04 = new Medicamento(8880, "Gelox", "Ice", "2x ao dia", "Quisque quam orci, vulputate sit amet", 11.50F, COMUM);
        Medicamento medicamento05 = new Medicamento(5656, "Aspirazol", "Acme", "3x ao dia", "Sed faucibus, libero iaculis pulvinar consequat, augue elit eleifend", 10.50F, CONTROLADO);
        Medicamento medicamento06 = new Medicamento(4040, "Propolizol", "Bee", "5x ao dia", "Nunc euismod ipsum purus, sit amet finibus libero ultricies in", 10.50F, CONTROLADO);

        listaMedicamentos.add(medicamento01);
        listaMedicamentos.add(medicamento02);
        listaMedicamentos.add(medicamento03);
        listaMedicamentos.add(medicamento04);
        listaMedicamentos.add(medicamento05);
        listaMedicamentos.add(medicamento06);

        for (Medicamento medicamento : listaMedicamentos) {
            Medicamento salvarMedicamento = medicamentoRepository.save(medicamento);
        }

    }

    public void criarEstoque() {

        List<Estoque> listaEstoques = new ArrayList<>();

        Estoque estoque01 = new Estoque(90561736000121L, 1010, 12, LocalDateTime.now());
        Estoque estoque02 = new Estoque(90561736000121L, 7473, 10, LocalDateTime.now());
        Estoque estoque03 = new Estoque(43178995000198L, 7473, 2, LocalDateTime.now());
        Estoque estoque04 = new Estoque(43178995000198L, 2233, 15, LocalDateTime.now());
        Estoque estoque05 = new Estoque(43178995000198L, 8880, 16, LocalDateTime.now());
        Estoque estoque06 = new Estoque(43178995000198L, 4040, 22, LocalDateTime.now());

        listaEstoques.add(estoque01);
        listaEstoques.add(estoque02);
        listaEstoques.add(estoque03);
        listaEstoques.add(estoque04);
        listaEstoques.add(estoque05);
        listaEstoques.add(estoque06);

        for (Estoque estoque : listaEstoques) {
            Estoque salvarEstoque = estoqueRepository.save(estoque);
        }

    }

}
