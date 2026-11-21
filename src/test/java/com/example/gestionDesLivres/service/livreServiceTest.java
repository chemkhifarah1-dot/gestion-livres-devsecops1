package com.example.gestionDesLivres.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.gestionDesLivres.entity.livre;
import com.example.gestionDesLivres.repository.livreRepository;

@ExtendWith(MockitoExtension.class)
class livreServiceTest {

    @Mock
    private livreRepository repo;

    @InjectMocks
    private livreService service;

    @Test
    void getAll_retourneLaListeDuRepository() {
        livre l1 = new livre();
        l1.setTitre("Livre A");
        when(repo.findAll()).thenReturn(List.of(l1));

        List<livre> resultat = service.getAll();

        assertEquals(1, resultat.size());
        assertEquals("Livre A", resultat.get(0).getTitre());
    }

    @Test
    void save_appelleLeRepository() {
        livre l = new livre();
        l.setTitre("Livre B");

        service.save(l);

        verify(repo).save(l);
    }

    @Test
    void getById_retourneLeLivreTrouve() {
        livre l = new livre();
        l.setId(1L);
        when(repo.findById(1L)).thenReturn(Optional.of(l));

        Optional<livre> resultat = service.getById(1L);

        assertTrue(resultat.isPresent());
        assertEquals(Long.valueOf(1L), resultat.get().getId());
    }

    @Test
    void getById_retourneVideSiInexistant() {
        when(repo.findById(99L)).thenReturn(Optional.empty());

        assertTrue(service.getById(99L).isEmpty());
    }

    @Test
    void delete_appelleDeleteById() {
        service.delete(1L);

        verify(repo).deleteById(1L);
    }

    @Test
    void findDisponibles_retourneLesLivresDisponibles() {
        livre l1 = new livre();
        livre l2 = new livre();
        when(repo.findByDisponibleTrue()).thenReturn(List.of(l1, l2));

        List<livre> resultat = service.findDisponibles();

        assertEquals(2, resultat.size());
        verify(repo).findByDisponibleTrue();
    }
}
