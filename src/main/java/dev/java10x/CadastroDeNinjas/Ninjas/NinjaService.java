package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NinjaService {


    private NinjaRepository ninjaRepository;
    private NinjaMapper ninjaMapper;

    public NinjaService(NinjaRepository ninjaRepository, NinjaMapper ninjaMapper) {
        this.ninjaRepository = ninjaRepository;
        this.ninjaMapper = ninjaMapper;
    }

    //Listar todos os meus ninjas
    public List<NinjaModel> listarNinjas(){
        return ninjaRepository.findAll() ;
    }

    //Lista por ID
    public NinjaModel listarPorID( UUID id ){
        Optional<NinjaModel> ninjaPorID = ninjaRepository.findById(id);
        return ninjaPorID.orElse(null);
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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ninja não encontrado"));
        ninjaRepository.delete(ninja);
        return ninja;
    }

    // Atualizar o ninja
    public NinjaModel atualizarNinja( UUID id, NinjaModel ninjaAtualizado ) {
        if (ninjaRepository.existsById(id)){
           ninjaAtualizado.setId(id);
           return ninjaRepository.save(ninjaAtualizado);
        }
        return null;
    }




}
