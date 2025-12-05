package dev.java10x.CadastroDeNinjas.Ninjas;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class NinjaService {


    private final NinjaRepository ninjaRepository;
    private final NinjaMapper ninjaMapper;

    public NinjaService(NinjaRepository ninjaRepository, NinjaMapper ninjaMapper) {
        this.ninjaRepository = ninjaRepository;
        this.ninjaMapper = ninjaMapper;
    }

    //Listar todos os meus ninjas
    public List<NinjaDTO> listarNinjas(){
        List<NinjaModel> ninjas = ninjaRepository.findAll();

        return ninjas.stream()
                .map(ninjaMapper::map)
                .collect(Collectors.toList());
    }

    //Lista por ID
    public NinjaDTO listarPorID( UUID id ){
        Optional<NinjaModel> ninjaPorID = ninjaRepository.findById(id);
        return ninjaPorID.map(ninjaMapper::map).orElse(null);
    }

    //Criar um novo ninja
    public NinjaDTO criarNinja( NinjaDTO ninjaDTO ){
        NinjaModel ninja = new NinjaMapper().map(ninjaDTO);
        ninjaRepository.save(ninja);
        return ninjaMapper.map(ninja);
    }

    //Deletar o ninja
    public NinjaModel deletarPorID(UUID id) {
        NinjaModel ninja = ninjaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ninja de ID: "+ id +" não encontrado"));
        ninjaRepository.delete(ninja);
        return ninja;
    }

    // Atualizar o ninja
    public NinjaDTO atualizarNinja(UUID id, NinjaDTO ninjaDTO) {
        NinjaModel ninjaExistente = ninjaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ninja de ID: " + id + " não encontrado"));

        NinjaModel ninjaAtualizado = ninjaMapper.map(ninjaDTO);
        ninjaAtualizado.setId(id);
        NinjaModel ninjaSalvo = ninjaRepository.save(ninjaAtualizado);

        return ninjaMapper.map(ninjaSalvo);
    }




}
