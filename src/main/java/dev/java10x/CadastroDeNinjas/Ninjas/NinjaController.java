package dev.java10x.CadastroDeNinjas.Ninjas;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class NinjaController {

    @GetMapping("/boasvindas")
    public String boasVindas(){
        return "Essa é a minha primeira mensagem!";
    }

    //Adicionar ninja  ( CREATE )
    @PostMapping("/criar")
    public String criarNinja(){
        return "Ninja Criado";
    }

    //Mostrar todos os ninjas ( READ )
    @GetMapping("/todos")
    public String mostrarTodosOsNinjas() {
        return "Mostrar ninjas";
    }

    //Procurar Ninja por ID  ( READ )
    @GetMapping("/todosID")
    public String mostrarTodosOsNinjasPorID() {
        return "Mostrar ninjas por ID";
    }

    //Alterar dados dos ninjas ( UPDATE )
    @PutMapping("/alterarID")
    public String alterarNinjasPorID() {
        return "Alterar ninjas por ID";
    }

    //Deletar Ninjas  ( DELETE )
    @DeleteMapping("/deletarID")
    @PutMapping("/alterarID")
    public String deletarNinjasPorID() {
        return "Deletar ninjas por ID";
    }

}
