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
    public NinjaDTO criarNinja( @RequestBody NinjaDTO ninja ){
        return ninjaService.criarNinja( ninja ) ;
    }

    //Mostrar todos os ninjas ( READ )
    @GetMapping("/listar")
    public List<NinjaModel> listarTodosOsNinjas() {
        return ninjaService.listarNinjas() ;
    }

    //Procurar Ninja por ID  ( READ )
    @GetMapping("/listar/{id}")
    public NinjaModel listarNinjasPorID( @PathVariable UUID id, @RequestBody NinjaModel ninjaAtualizado ) {
        return ninjaService.atualizarNinja(id, ninjaAtualizado );
    }

    //Alterar dados dos ninjas ( UPDATE )
    @PutMapping("/alterar/{id}")
    public String alterarNinjasPorID( @PathVariable UUID id ) {
        return "Alterar ninjas por ID";
    }

    //Deletar Ninjas  ( DELETE )
    @DeleteMapping("/deletar/{id}")
    public void deletarNinjasPorID( @PathVariable UUID id ) {
        ninjaService.deletarPorID(id);
    }

}
