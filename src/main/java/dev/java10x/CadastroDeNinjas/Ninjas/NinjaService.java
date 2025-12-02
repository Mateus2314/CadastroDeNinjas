package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NinjaService {


    private NinjaRepository ninjaRepository;

    public NinjaService(NinjaRepository ninjaRepository) {
        this.ninjaRepository = ninjaRepository;
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
    public NinjaModel criarNinja( NinjaModel ninja ){
        return ninjaRepository.save(ninja);
    }




}
