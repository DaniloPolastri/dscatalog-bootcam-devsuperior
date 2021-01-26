package com.devsuperior.dscatalog.services;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.repositories.CategoryRepository;
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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository respository;

    @Transactional(readOnly = true) // evitar q eu faco lock no banco de dados e melhorar a perfomace, operacoes somente letiura lembrar de por readOnly = true
    public Page<CategoryDTO> findAllPaged(PageRequest pageRequest){



        // converter a lista normal para stream que permite trabalhar com funcoes de alta ordem por exemplo lambda
        // Map = ela transforma cada elemento original em uma outra coisa ela aplica uma funcao a cada elemento da sua lista
        // Collect = transforma uma stream novamente para uma lista
        //List<Category> list = respository.findAll();

        Page<Category> list = respository.findAll(pageRequest);

        //list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());

        //Page ja e do tipo stream
        return list.map(x -> new CategoryDTO(x));

        /*List<CategoryDTO> listDto = new ArrayList<>();

        for(Category cat : list){
            listDto.add(new CategoryDTO(cat));
        }

        return listDto;*/

    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {
        // Optional e para evitar de voce trabalhar com o valor nulo
        Optional<Category> obj = respository.findById(id);
        Category entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));

        return new CategoryDTO(entity);
    }

    @Transactional
    public CategoryDTO insert(CategoryDTO dto) {
        Category entity = new Category();
        entity.setName(dto.getName());
        entity = respository.save(entity);

        return  new CategoryDTO(entity);

    }

    @Transactional
    public CategoryDTO update(Long id, CategoryDTO dto) {
        /*o findbyid ele efetiva o acesso ao banco de dados, e o getOne ele nao toca no banco de dados vai instancia um obejto provisorio com o esse id naquele objeto
        so quando vc manda salvar ai sim ele vai no banco de dados, a intencao do getOne e para nao ir no banco sem necessidade*/
        try {
            Category entity = respository.getOne(id);
            entity.setName(dto.getName());
            entity = respository.save(entity);
            return new CategoryDTO(entity);
        } catch (EntityNotFoundException e) {
            throw  new ResourceNotFoundException("Id not Found " + id);
        }
    }

    // o delete n recebe o transactional, pq o delete vai quere captura uma excecao q vai vim do banco de dados e se eu por o transaction n consegue fazer isso
    public void delete(Long id) {
        try {
            respository.deleteById(id);
        } catch (EmptyResultDataAccessException e ){
            throw new ResourceNotFoundException("Id not Found " + id);
        } catch (DataIntegrityViolationException e ){
            throw new DataBaseException("Intergrity violation");
        }
    }
}
