package com.example.gestionDesLivres.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gestionDesLivres.entity.Emprunt;
import com.example.gestionDesLivres.repository.empruntRepository;
@Service
public class empruntService {

    @Autowired
    private empruntRepository empruntRepository;

    public List<Emprunt> getAll() {
        return empruntRepository.findAll();
    }

    public void save(Emprunt e) {
        empruntRepository.save(e);
    }

    public Optional<Emprunt> getById(Long id) {
        return empruntRepository.findById(id);
    }

    public void delete(Long id) {
        empruntRepository.deleteById(id);
    }
}