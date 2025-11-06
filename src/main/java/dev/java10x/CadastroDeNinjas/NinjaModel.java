package dev.java10x.CadastroDeNinjas;

import jakarta.persistence.*;

//Entity ele transforma uma classe em uma entidade do BD
// JPA = Java Persistence API
@Entity
@Table(name = "tb_cadastro")
public class NinjaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;
    private String nome ;
    private String cla ;
    private Character rank ;
    private String tecnicas;
    private int idade ;

    public NinjaModel() {
    }

    public NinjaModel(String nome, String cla, Character rank, String tecnicas, int idade) {
        this.nome = nome;
        this.cla = cla;
        this.rank = rank;
        this.tecnicas = tecnicas;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCla() {
        return cla;
    }

    public void setCla(String cla) {
        this.cla = cla;
    }

    public Character getRank() {
        return rank;
    }

    public void setRank(Character rank) {
        this.rank = rank;
    }

    public String getTecnicas() {
        return tecnicas;
    }

    public void setTecnicas(String tecnicas) {
        this.tecnicas = tecnicas;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
