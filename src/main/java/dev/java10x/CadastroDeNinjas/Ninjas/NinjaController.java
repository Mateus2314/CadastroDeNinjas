package dev.java10x.CadastroDeNinjas.Ninjas;


import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

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
    @GetMapping("/listar")
    public List<NinjaModel> listarTodosOsNinjas() {
        return ninjaService.listarNinjas() ;
    }

    //Procurar Ninja por ID  ( READ )
    @GetMapping("/listar/{id}")
    public NinjaModel listarNinjasPorID( @PathVariable UUID id ) {
        return ninjaService.listarPorID(id) ;
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
