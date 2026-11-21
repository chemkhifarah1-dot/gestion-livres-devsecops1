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

import com.example.gestionDesLivres.entity.auteur;
import com.example.gestionDesLivres.service.auteurService;

@Controller
@RequestMapping("/auteurs")
public class auteurController {

    @Autowired
    private auteurService auteurService;

    @GetMapping
    public String index(@Nonnull Model model) {
        model.addAttribute("auteurs", auteurService.getAll());
        return "auteurs/index";
    }

    @GetMapping("/new")
    public String form(@Nonnull Model model) {
        model.addAttribute("auteur", new auteur());
        return "auteurs/form";
    }

    @PostMapping
    public String save(@ModelAttribute auteur auteur) {
        auteurService.save(auteur);
        return "redirect:/auteurs";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, @Nonnull Model model) {
        model.addAttribute("auteur",
                     auteurService.getById(id).orElseThrow());
        return "auteurs/form";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        auteurService.delete(id);
        return "redirect:/auteurs";
    }
}