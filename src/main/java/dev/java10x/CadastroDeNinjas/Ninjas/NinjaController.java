package dev.java10x.CadastroDeNinjas.Ninjas;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninja ){

       NinjaDTO novoNinja = ninjaService.criarNinja( ninja ) ;
       return ResponseEntity.status(HttpStatus.CREATED)
               .body("Ninja criado com sucesso: " + novoNinja.getNome() + " (ID): "+ novoNinja.getId());
    }

    //Mostrar todos os ninjas ( READ )
    @GetMapping("/listar")
    public ResponseEntity<List<NinjaDTO>>  listarTodosOsNinjas() {
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        return ResponseEntity.ok(ninjas);
    }

    //Procurar Ninja por ID  ( READ )
    @GetMapping("/listar/{id}")
    public ResponseEntity<?>  listarNinjasPorID( @PathVariable UUID id ) {

        if ( listarNinjasPorID(id) != null  ){
            NinjaDTO ninja = ninjaService.listarPorID(id);
            return ResponseEntity.ok(ninja);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja de ID: "+id+" não encontrado")  ;
    }

    // Alterar dados dos ninjas ( UPDATE )
    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterarNinjasPorID(@PathVariable UUID id, @RequestBody NinjaDTO ninjaDTO) {
        try {
            NinjaDTO ninjaAtualizado = ninjaService.atualizarNinja(id, ninjaDTO);
            return ResponseEntity.ok("Ninja com ID: " + id + " atualizado com sucesso!");
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja de ID: " + id + " não encontrado!");
        }
    }

    //Deletar Ninjas  ( DELETE )
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarNinjasPorID(@PathVariable UUID id) {
        try {
            ninjaService.deletarPorID(id);
            return ResponseEntity.ok("Ninja com ID: " + id + " deletado com sucesso!");
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja de ID: " + id + " não encontrado!");
        }
    }

}
