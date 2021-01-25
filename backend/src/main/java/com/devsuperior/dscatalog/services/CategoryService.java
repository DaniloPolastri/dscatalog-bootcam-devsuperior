package com.devsuperior.dscatalog.services;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.repositories.CategoryRepository;
import com.devsuperior.dscatalog.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository respository;

    @Transactional(readOnly = true) // evitar q eu faco lock no banco de dados e melhorar a perfomace, operacoes somente letiura lembrar de por readOnly = true
    public List<CategoryDTO> findAll(){



        // converter a lista normal para stream que permite trabalhar com funcoes de alta ordem por exemplo lambda
        // Map = ela transforma cada elemento original em uma outra coisa ela aplica uma funcao a cada elemento da sua lista
        // Collect = transforma uma stream novamente para uma lista
        List<Category> list = respository.findAll();
        return list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());

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
        Category entity = obj.orElseThrow(() -> new EntityNotFoundException("Entity not found"));

        return new CategoryDTO(entity);
    }
}
