package br.com.example.sistema_manutencao.repository;

import br.com.example.sistema_manutencao.model.Manutencao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManutencaoRepository extends JpaRepository<Manutencao, Long> {
}
