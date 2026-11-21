package com.example.gestionDesLivres.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gestionDesLivres.entity.livre;
import com.example.gestionDesLivres.repository.livreRepository;

@Service
public class livreService {

    @Autowired
    private livreRepository livreRepository;

    public List<livre> getAll() {
        return livreRepository.findAll();
    }

    public void save(livre l) {
        livreRepository.save(l);
    }

    public Optional<livre> getById(Long id) {
        return livreRepository.findById(id);
    }

    public void delete(Long id) {
        livreRepository.deleteById(id);
    }

    public List<livre> findDisponibles() {
        return livreRepository.findByDisponibleTrue();
    }
}