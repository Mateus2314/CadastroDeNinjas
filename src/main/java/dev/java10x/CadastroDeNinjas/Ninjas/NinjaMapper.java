package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.stereotype.Component;

@Component
public class NinjaMapper {

    public NinjaModel map(NinjaDTO ninjaDTO ){

        NinjaModel ninjaModel = new NinjaModel();

        ninjaModel.setId(ninjaDTO.getId());
        ninjaModel.setNome(ninjaDTO.getNome());
        ninjaModel.setCla(ninjaDTO.getCla());
        ninjaModel.setRank(ninjaDTO.getRank());
        ninjaModel.setTecnicas(ninjaDTO.getTecnicas());
        ninjaModel.setIdade(ninjaDTO.getIdade());
        ninjaModel.setEmail(ninjaDTO.getEmail());
        ninjaModel.setMissoes(ninjaDTO.getMissoes());
        ninjaModel.setSobreNome(ninjaDTO.getSobreNome());

        return ninjaModel;
    }

    public NinjaDTO map(NinjaModel ninjaModel ){
        NinjaDTO ninjaDTO = new NinjaDTO();

        ninjaDTO.setId(ninjaModel.getId());
        ninjaDTO.setNome(ninjaModel.getNome());
        ninjaDTO.setCla(ninjaModel.getCla());
        ninjaDTO.setRank(ninjaModel.getRank());
        ninjaDTO.setTecnicas(ninjaModel.getTecnicas());
        ninjaDTO.setIdade(ninjaModel.getIdade());
        ninjaDTO.setEmail(ninjaModel.getEmail());
        ninjaDTO.setMissoes(ninjaModel.getMissoes());
        ninjaDTO.setSobreNome(ninjaModel.getSobreNome());

        return ninjaDTO;

    }

}
