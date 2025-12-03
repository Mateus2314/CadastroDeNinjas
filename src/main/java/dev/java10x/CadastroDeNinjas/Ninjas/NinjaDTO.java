package dev.java10x.CadastroDeNinjas.Ninjas;

import dev.java10x.CadastroDeNinjas.Missoes.MissoesModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NinjaDTO {

    private UUID id;
    private String nome ;
    private String cla ;
    private Character rank ;
    private String tecnicas;
    private int idade ;
    private String email;
    private MissoesModel missoes ;
    private String sobreNome ;

}
