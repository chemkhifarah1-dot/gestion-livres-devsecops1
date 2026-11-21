package com.example.gestionDesLivres.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gestionDesLivres.entity.auteur;
import com.example.gestionDesLivres.repository.auteurRepository;

@Service
public class auteurService {

    @Autowired
    private auteurRepository auteurRepository;
    
    public List<auteur> getAll() {
        return auteurRepository.findAll();
    }

    public void save(auteur a) {
        auteurRepository.save(a);
    }

    public Optional<auteur> getById(Long id) {
        return auteurRepository.findById(id);
    }

    public void delete(Long id) {
        auteurRepository.deleteById(id);
    }
}