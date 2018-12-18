package guru.springframework.services;

import guru.springframework.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();

    Recipe findById(Long id);

    Recipe save(Recipe object);

    void delete(Recipe object);

    void deleteById(Long id);
}
