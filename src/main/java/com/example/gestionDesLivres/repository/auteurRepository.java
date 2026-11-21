package com.example.gestionDesLivres.repository;

import com.example.gestionDesLivres.entity.auteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface auteurRepository extends JpaRepository<auteur, Long> {
}