package com.example.gestionDesLivres.controller;

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

import com.example.gestionDesLivres.entity.livre;
import com.example.gestionDesLivres.service.auteurService;
import com.example.gestionDesLivres.service.livreService;

@Controller
@RequestMapping("/livres")
public class LivreViewController {

    @Autowired
    private livreService livreService;

    @Autowired
    private auteurService auteurService;

    @GetMapping
    public String index(@Nonnull Model model) {
        model.addAttribute("livres", livreService.getAll());
        return "livres/index";
    }

    @GetMapping("/new")
    public String form(@Nonnull Model model) {
        model.addAttribute("livre", new livre());
        model.addAttribute("auteurs", auteurService.getAll());
        return "livres/form";
    }

    @PostMapping
    public String save(@Nonnull @ModelAttribute("livre") livre livre,
                       @RequestParam("auteurId") Long auteurId) {
        livre.setAuteur(auteurService.getById(auteurId).orElseThrow());
        livreService.save(livre);
        return "redirect:/livres";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, @Nonnull Model model) {
        model.addAttribute("livre", livreService.getById(id).orElseThrow());
        model.addAttribute("auteurs", auteurService.getAll());
        return "livres/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        livreService.delete(id);
        return "redirect:/livres";
    }
}