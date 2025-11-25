package dev.java10x.CadastroDeNinjas.Ninjas;

import dev.java10x.CadastroDeNinjas.NinjaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NinjaRepository extends JpaRepository<NinjaModel, UUID> {



}
