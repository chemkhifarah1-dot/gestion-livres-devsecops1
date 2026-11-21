package com.example.gestionDesLivres.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gestionDesLivres.entity.livre;

@Repository
public interface livreRepository extends JpaRepository<livre, Long> {
    List<livre> findByDisponibleTrue();
}