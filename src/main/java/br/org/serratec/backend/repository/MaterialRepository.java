package br.org.serratec.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.backend.model.Material;

public interface MaterialRepository extends JpaRepository<Material, Long> {

}
