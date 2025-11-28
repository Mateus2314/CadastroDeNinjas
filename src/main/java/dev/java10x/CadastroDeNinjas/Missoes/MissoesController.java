package dev.java10x.CadastroDeNinjas.Missoes;

//LOCALHOST:8080/

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("missoes")
public class MissoesController  {

    @GetMapping("/listar")
    public String listarMissao(){
        return "Missão listadas com sucesso" ;
    }

    @PostMapping("/criar")
    public String criarMissao(){
        return "Missão criada com sucesso" ;
    }

    @PutMapping("/alterar")
    public String alterarMissao(){
        return "Missão alterada com sucesso" ;
    }

    @DeleteMapping("/deletar")
    public String deletarMissao(){
        return "Missão deletada com sucesso" ;
    }
}
