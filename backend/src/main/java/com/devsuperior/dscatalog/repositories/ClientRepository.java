package com.devsuperior.dscatalog.repositories;

import com.devsuperior.dscatalog.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
