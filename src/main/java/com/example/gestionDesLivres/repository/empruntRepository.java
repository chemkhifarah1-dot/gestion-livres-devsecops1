package com.example.gestionDesLivres.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.gestionDesLivres.entity.Emprunt;

@Repository
public interface empruntRepository extends JpaRepository<Emprunt, Long> {
}