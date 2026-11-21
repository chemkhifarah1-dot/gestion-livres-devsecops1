package com.example.gestionDesLivres.controller;

import java.time.LocalDate;

import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gestionDesLivres.entity.Emprunt;
import com.example.gestionDesLivres.service.empruntService;
import com.example.gestionDesLivres.service.livreService;

@Controller
@RequestMapping("/emprunts")
public class empruntController {

    @Autowired
    private empruntService empruntService;

    @Autowired
    private livreService livreService;

    @GetMapping
    public String index(@Nonnull Model model) {
        model.addAttribute("emprunts", empruntService.getAll());
        return "emprunts/index";
    }

    @GetMapping("/new")
    public String form(@Nonnull Model model) {
        model.addAttribute("emprunt", new Emprunt());
        model.addAttribute("livres", livreService.getAll());
        return "emprunts/form";
    }
@PostMapping
public String save(@Nonnull @ModelAttribute Emprunt emprunt,
                   @RequestParam Long livreId) {
    emprunt.setLivre(livreService.getById(livreId).orElseThrow());
    emprunt.setDateEmprunt(LocalDate.now());
    emprunt.setDateRetourPrevue(LocalDate.now().plusDays(5));
    empruntService.save(emprunt);
    return "redirect:/emprunts";
}

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, @Nonnull Model model) {
        model.addAttribute("emprunt", empruntService.getById(id).orElseThrow());
        model.addAttribute("livres", livreService.getAll());
        return "emprunts/form";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id,
                         @ModelAttribute Emprunt emprunt,
                         @RequestParam Long livreId) {
        emprunt.setId(id);
        emprunt.setLivre(livreService.getById(livreId).orElseThrow());
            emprunt.setDateRetourPrevue(LocalDate.now().plusDays(14));
        empruntService.save(emprunt);
        return "redirect:/emprunts";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        empruntService.delete(id);
        return "redirect:/emprunts";
    }
}