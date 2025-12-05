package dev.java10x.CadastroDeNinjas.Ninjas;
import dev.java10x.CadastroDeNinjas.Missoes.MissoesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/ninjas/ui")
public class NinjaControllerUI {

    private final NinjaService ninjaService;
    private final MissoesService missoesService;

    public NinjaControllerUI(NinjaService ninjaService, MissoesService missoesService) {
        this.ninjaService = ninjaService;
        this.missoesService = missoesService;
    }

    // Página inicial
    @GetMapping
    public String home() {
        return "index"; // criaremos um index.html simples
    }

    // Listar todos os ninjas
    @GetMapping("/listar")
    public String listarTodosOsNinjas(Model model) {
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        model.addAttribute("ninjas", ninjas);
        return "listarNinjas"; // nome do template Thymeleaf
    }

    // Mostrar detalhes de um ninja específico
    @GetMapping("/detalhes/{id}")
    public String detalhesDoNinja(@PathVariable UUID id, Model model) {
        NinjaDTO ninja = ninjaService.listarPorID(id);
        model.addAttribute("ninja", ninja);
        return "detalhesNinja"; // criaremos um detalhesNinja.html
    }

    // Formulário para criar novo ninja
    @GetMapping("/novo")
    public String novoNinjaForm(Model model) {
        model.addAttribute("ninja", new NinjaDTO());
        model.addAttribute("missoes", missoesService.listarMissoes());
        return "formNinja"; // criaremos um formNinja.html
    }

    // Salvar novo ninja
    @PostMapping("/salvar")
    public String salvarNinja(@ModelAttribute NinjaDTO ninja) {
        ninjaService.criarNinja(ninja);
        return "redirect:/ninjas/ui/listar";
    }

    // Mostrar formulário de edição
    @GetMapping("/editar/{id}")
    public String editarNinjaForm(@PathVariable UUID id, Model model) {
        NinjaDTO ninja = ninjaService.listarPorID(id);
        if (ninja == null) {
            return "redirect:/ninjas/ui/listar";
        }
        model.addAttribute("ninja", ninja);
        model.addAttribute("missoes", missoesService.listarMissoes());
        return "formEditarNinja";
    }


    // Processar atualização
    @PostMapping("/atualizar/{id}")
    public String atualizarNinja(@PathVariable UUID id, @ModelAttribute NinjaDTO ninjaDTO) {
        ninjaService.atualizarNinja(id, ninjaDTO);
        return "redirect:/ninjas/ui/listar";
    }

    // Deletar ninja
    @GetMapping("/deletar/{id}")
    public String deletarNinja(@PathVariable UUID id) {
        ninjaService.deletarPorID(id);
        return "redirect:/ninjas/ui/listar";
    }
}


