package com.example.pokemon.controller;

import com.example.pokemon.entity.Pokemon;
import com.example.pokemon.repository.CategoryRepository;
import com.example.pokemon.repository.PokemonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pokemon")
public class PokemonController {

    private final PokemonRepository pokemonRepository;
    private final CategoryRepository categoryRepository;

    public PokemonController(PokemonRepository pokemonRepository, CategoryRepository categoryRepository) {
        this.pokemonRepository = pokemonRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping({"", "/list"}) // <-- Use this combined mapping
    public String listPokemon(Model model) {
        model.addAttribute("pokemonList", pokemonRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "pokemon/list";
    }

    @GetMapping("/add")
    public String showCreateForm(Model model) {
        model.addAttribute("pokemon", new Pokemon());
        model.addAttribute("categories", categoryRepository.findAll());
        return "pokemon/create";
    }

    @PostMapping("/add")
    public String savePokemon(@ModelAttribute Pokemon pokemon) {
        pokemonRepository.save(pokemon);
        return "redirect:/pokemon";
    }

    @GetMapping("/delete/{id}")
    public String deletePokemon(@PathVariable Long id) {
        pokemonRepository.deleteById(id);
        return "redirect:/pokemon";
    }
}