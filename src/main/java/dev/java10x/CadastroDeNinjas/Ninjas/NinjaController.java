package dev.java10x.CadastroDeNinjas.Ninjas;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasvindas")
    @Operation(
            summary = "Mensagem de boas-vindas",
            description = "Retorna uma mensagem simples de boas-vindas para quem acessar a API.",
            tags = {"Utilitário"}
    )
    public String boasVindas() {
        return "Essa é a minha primeira mensagem!";
    }

    @PostMapping("/criar")
    @Operation(
            summary = "Cria um novo ninja",
            description = "Recebe os dados de um ninja e insere no banco de dados.",
            tags = {"Ninjas"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja criado com sucesso!",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{ \"message\": \"Ninja criado com sucesso: Naruto (ID): 123e4567-e89b-12d3-a456-426614174000\" }"))),
            @ApiResponse(responseCode = "400", description = "Erro na criação do ninja.")
    })
    public ResponseEntity<String> criarNinja(
            @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Objeto Ninja a ser criado",
                    required = true,
                    content = @Content(schema = @Schema(implementation = NinjaDTO.class),
                            examples = @ExampleObject(value = "{ \"nome\": \"Naruto\", \"idade\": 17, \"aldeia\": \"Konoha\" }"))
            )
            NinjaDTO ninja) {

        NinjaDTO novoNinja = ninjaService.criarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja criado com sucesso: " + novoNinja.getNome() + " (ID): " + novoNinja.getId());
    }

    @GetMapping("/listar")
    @Operation(
            summary = "Lista todos os ninjas",
            description = "Retorna uma lista com todos os ninjas cadastrados.",
            tags = {"Ninjas"}
    )
    @ApiResponse(responseCode = "200", description = "Lista de ninjas retornada com sucesso.",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = NinjaDTO.class)))
    public ResponseEntity<List<NinjaDTO>> listarTodosOsNinjas() {
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        return ResponseEntity.ok(ninjas);
    }

    @GetMapping("/listar/{id}")
    @Operation(
            summary = "Busca ninja por ID",
            description = "Retorna os dados de um ninja específico pelo seu ID.",
            tags = {"Ninjas"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja encontrado.",
                    content = @Content(schema = @Schema(implementation = NinjaDTO.class))),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado.")
    })
    public ResponseEntity<?> listarNinjasPorID(
            @Parameter(description = "UUID do ninja a ser buscado", required = true)
            @PathVariable UUID id) {

        NinjaDTO ninja = ninjaService.listarPorID(id);
        if (ninja != null) {
            return ResponseEntity.ok(ninja);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja de ID: " + id + " não encontrado");
    }

    @PutMapping("/alterar/{id}")
    @Operation(
            summary = "Atualiza dados de um ninja",
            description = "Altera os dados de um ninja existente pelo seu ID.",
            tags = {"Ninjas"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja atualizado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado.")
    })
    public ResponseEntity<?> alterarNinjasPorID(
            @Parameter(description = "UUID do ninja a ser atualizado", required = true)
            @PathVariable UUID id,
            @RequestBody NinjaDTO ninjaDTO) {
        try {
            NinjaDTO ninjaAtualizado = ninjaService.atualizarNinja(id, ninjaDTO);
            return ResponseEntity.ok("Ninja com ID: " + id + " atualizado com sucesso!");
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja de ID: " + id + " não encontrado!");
        }
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(
            summary = "Deleta um ninja",
            description = "Remove um ninja do banco de dados pelo seu ID.",
            tags = {"Ninjas"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado.")
    })
    public ResponseEntity<String> deletarNinjasPorID(
            @Parameter(description = "UUID do ninja a ser deletado", required = true)
            @PathVariable UUID id) {
        try {
            ninjaService.deletarPorID(id);
            return ResponseEntity.ok("Ninja com ID: " + id + " deletado com sucesso!");
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja de ID: " + id + " não encontrado!");
        }
    }
}


