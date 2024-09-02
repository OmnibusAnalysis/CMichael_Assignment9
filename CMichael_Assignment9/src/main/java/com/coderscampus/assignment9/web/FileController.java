package com.coderscampus.assignment9.web;

import com.coderscampus.assignment9.domain.Recipe;
import com.coderscampus.assignment9.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping("/all-recipes")
    public List<Recipe> getRecipes() throws IOException {
        List<Recipe> recipes = fileService.getRecipes();
        return recipes;
    }

    @GetMapping("/glutenfree")
    private List<Recipe> getGlutenFree() throws IOException {

        List<Recipe> glutenFree = fileService.getRecipes()
                .stream()
                .filter(x->x.getGlutenFree())
                .collect(Collectors.toList());
//		System.out.println(glutenFree.toString());
        return glutenFree;
    }

    @GetMapping("/vegan")
    private List<Recipe> getVegan() throws IOException {
        List<Recipe> veganRecipes = fileService.getRecipes()
                .stream()
                .filter(x->x.getVegan())
                .collect(Collectors.toList());
        return veganRecipes;
    }

    @GetMapping("/veganandglutenfree")
    private List<Recipe> getVeganAndGlutenFree() throws IOException {
        List<Recipe> veganAndGlutenFreeRecipes = fileService.getRecipes()
                .stream()
                .filter(x->x.getGlutenFree() && x.getVegan())
                .collect(Collectors.toList());
        System.out.println(veganAndGlutenFreeRecipes.size());
        return veganAndGlutenFreeRecipes;
    }

    @GetMapping("/vegetarian")
    private List<Recipe> getVegetarian() throws IOException {
        List<Recipe> vegetarian = fileService.getRecipes()
                .stream()
                .filter(Recipe::getVegetarian)
                .collect(Collectors.toList());
        System.out.println(vegetarian.size());
        return vegetarian;
    }

}


