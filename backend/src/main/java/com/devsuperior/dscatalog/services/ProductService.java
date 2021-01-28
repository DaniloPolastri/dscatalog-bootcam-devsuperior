package com.devsuperior.dscatalog.services;

import com.devsuperior.dscatalog.dto.ProductDTO;
import com.devsuperior.dscatalog.entities.Product;
import com.devsuperior.dscatalog.repositories.ProductRepository;
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
public class ProductService {

    @Autowired
    private ProductRepository respository;

    @Transactional(readOnly = true) // evitar q eu faco lock no banco de dados e melhorar a perfomace, operacoes somente letiura lembrar de por readOnly = true
    public Page<ProductDTO> findAllPaged(PageRequest pageRequest){
        Page<Product> list = respository.findAll(pageRequest);
        return list.map(x -> new ProductDTO(x));


    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<Product> obj = respository.findById(id);
        Product entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));

        return new ProductDTO(entity, entity.getCategories());
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product entity = new Product();
        //entity.setName(dto.getName());
        entity = respository.save(entity);
        return  new ProductDTO(entity);

    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        try {
            Product entity = respository.getOne(id);
           // entity.setName(dto.getName());
            entity = respository.save(entity);
            return new ProductDTO(entity);
        } catch (EntityNotFoundException e) {
            throw  new ResourceNotFoundException("Id not Found " + id);
        }
    }

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
