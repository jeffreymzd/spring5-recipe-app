package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecipeServiceImplTest {

    public static final String DESCRIPTION = "Guacamole";
    @Mock
    RecipeRepository recipeRepository;
    @InjectMocks
    RecipeServiceImpl recipeService;

    Recipe returnRecipe;

    @BeforeEach
    void setUp() {
        returnRecipe = new Recipe();
        returnRecipe.setId(1L);
        returnRecipe.setDescription(DESCRIPTION);
    }

    @Test
    void getRecipes() {
        assertNull(recipeService.findById(1L));
    }

    @Test
    void findById() {
        Set<Recipe> recipeSet = new HashSet<>();
        recipeSet.add(returnRecipe);
        when(recipeRepository.findAll()).thenReturn(recipeSet);
        Recipe savedRecipe = recipeService.findById(1L);

        assertEquals(returnRecipe.getDescription(), savedRecipe.getDescription());
        verify(recipeRepository).findAll();
    }

    @Test
    void save() {
        when(recipeRepository.save(any())).thenReturn(returnRecipe);
        Recipe savedRecipe = recipeRepository.save(returnRecipe);
        assertEquals(returnRecipe, savedRecipe);
        verify(recipeRepository).save(any());
    }

    @Test
    void delete() {
        recipeService.delete(returnRecipe);
        verify(recipeRepository).delete(any());
    }

    @Test
    void deleteById() {
        recipeService.deleteById(1L);
        verify(recipeRepository).deleteById(any());
    }
}