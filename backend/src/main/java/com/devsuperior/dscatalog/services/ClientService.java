package com.devsuperior.dscatalog.services;

import com.devsuperior.dscatalog.dto.ClientDTO;
import com.devsuperior.dscatalog.entities.Client;
import com.devsuperior.dscatalog.repositories.ClientRepository;
import com.devsuperior.dscatalog.services.exceptions.DataBaseException;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true) // evitar q eu faco lock no banco de dados e melhorar a perfomace, operacoes somente letiura lembrar de por readOnly = true
    public Page<ClientDTO> findAllPaged(PageRequest pageRequest){

        // converter a lista normal para stream que permite trabalhar com funcoes de alta ordem por exemplo lambda
        // Map = ela transforma cada elemento original em uma outra coisa ela aplica uma funcao a cada elemento da sua lista
        // Collect = transforma uma stream novamente para uma lista
        //List<Client> list = repository.findAll();

        Page<Client> list = repository.findAll(pageRequest);

        //list.stream().map(x -> new ClientDTO(x)).collect(Collectors.toList());

        //Page ja e do tipo stream
        return list.map(x -> new ClientDTO(x));

        /*List<ClientDTO> listDto = new ArrayList<>();

        for(Client cat : list){
            listDto.add(new ClientDTO(cat));
        }

        return listDto;*/

    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        // Optional e para evitar de voce trabalhar com o valor nulo
        Optional<Client> obj = repository.findById(id);
        Client entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));

        return new ClientDTO(entity);
    }

    @Transactional
    public ClientDTO insert(ClientDTO dto) {
        Client entity = new Client();
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
        entity = repository.save(entity);

        return  new ClientDTO(entity);

    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto) {

        try {
            Client entity = repository.getOne(id);
            entity.setName(dto.getName());
            entity.setCpf(dto.getCpf());
            entity.setIncome(dto.getIncome());
            entity.setBirthDate(dto.getBirthDate());
            entity.setChildren(dto.getChildren());

            entity = repository.save(entity);
            return new ClientDTO(entity);
        } catch (EntityNotFoundException e) {
            throw  new ResourceNotFoundException("Id not Found " + id);
        }
    }

    // o delete n recebe o transactional, pq o delete vai quere captura uma excecao q vai vim do banco de dados e se eu por o transaction n consegue fazer isso
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e ){
            throw new ResourceNotFoundException("Id not Found " + id);
        } catch (DataIntegrityViolationException e ){
            throw new DataBaseException("Intergrity violation");
        }
    }
}
