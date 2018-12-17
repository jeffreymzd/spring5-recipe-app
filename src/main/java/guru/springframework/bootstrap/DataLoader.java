package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private RecipeRepository recipeRepository;
    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public DataLoader(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    private List<Recipe> getRecipeList() {
        log.debug("Start loading recipe objects...");
        List<Recipe> recipesList = new ArrayList<>(2);
        Recipe recipe_guacamole = new Recipe();
        Recipe recipe_grilled_chicken_taco = new Recipe();

        // get UoMs
        Optional<UnitOfMeasure> eachUomOption = unitOfMeasureRepository.findByDescription("Each");
        if (!eachUomOption.isPresent()) {
            throw new RuntimeException("Expected UoM not found");
        }

        Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        if (!tableSpoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UoM not found");
        }

        Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        if (!teaSpoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UoM not found");
        }

        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");
        if (!dashUomOptional.isPresent()) {
            throw new RuntimeException("Expected UoM not found");
        }

        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");
        if (!pintUomOptional.isPresent()) {
            throw new RuntimeException("Expected UoM not found");
        }

        Optional<UnitOfMeasure> cupUomOptional = unitOfMeasureRepository.findByDescription("Cup");
        if (!cupUomOptional.isPresent()) {
            throw new RuntimeException("Expected UoM not found");
        }

        UnitOfMeasure eachUom = eachUomOption.get();
        UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure teaSpoonUom = teaSpoonUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure pintUom = pintUomOptional.get();
        UnitOfMeasure cupUom = cupUomOptional.get();

        // get Category
        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
        if (!mexicanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category not found");
        }
        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
        if (!americanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category not found");
        }

        Category mexicanCategory = mexicanCategoryOptional.get();
        Category americanCategory = americanCategoryOptional.get();

        // Collect Ingredients
        // 1. Perfect Guacamole
        Ingredient avocados = new Ingredient("ripe avocados", BigDecimal.valueOf(2), null);
        Ingredient kosher_salt = new Ingredient("Kosher salt", BigDecimal.valueOf(0.5), teaSpoonUom);
        Ingredient lime_or_lemon_juice = new Ingredient("fresh lime juice or lemon juice", BigDecimal.ONE, tableSpoonUom);
        Ingredient onion = new Ingredient("minced red onion or thinly sliced green onion", BigDecimal.valueOf(0.25), cupUom);
        Ingredient chile = new Ingredient("serrano chiles, stems and seeds removed, minced", BigDecimal.valueOf(2), null);
        Ingredient cilantro = new Ingredient("cilantro (leaves and tender stems), finely chopped", BigDecimal.valueOf(2), tableSpoonUom);
        Ingredient black_pepper = new Ingredient("freshly grated black pepper", BigDecimal.ONE, dashUom);
        Ingredient tomato = new Ingredient("ripe tomato, seeds and pulp removed, chopped", BigDecimal.valueOf(2), eachUom);

        recipe_guacamole.addIngredient(avocados)
                .addIngredient(kosher_salt)
                .addIngredient(lime_or_lemon_juice)
                .addIngredient(onion)
                .addIngredient(chile)
                .addIngredient(cilantro)
                .addIngredient(black_pepper)
                .addIngredient(tomato);

        // 2. Spicy Grilled Chicken Taco
        Ingredient chili_powder = new Ingredient("ancho chili powder", BigDecimal.valueOf(2), teaSpoonUom);
        Ingredient dried_oregano = new Ingredient("dried oregano", BigDecimal.ONE, teaSpoonUom);
        Ingredient dried_cumin = new Ingredient("dried cumin", BigDecimal.ONE, teaSpoonUom);
        Ingredient sugar = new Ingredient("sugar", BigDecimal.ONE, teaSpoonUom);
        Ingredient salt = new Ingredient("salt", BigDecimal.valueOf(0.5), teaSpoonUom);
        Ingredient garlic = new Ingredient("clove garlic, finely chopped", BigDecimal.ONE, null);
        Ingredient orange_zest = new Ingredient("finely grated orange zest", BigDecimal.ONE, teaSpoonUom);
        Ingredient orange_juice = new Ingredient("fresh-squeezed orange juice", BigDecimal.valueOf(3), tableSpoonUom);
        Ingredient olive_oil = new Ingredient("olive oil", BigDecimal.valueOf(2), tableSpoonUom);
        Ingredient chicken_thigh = new Ingredient("skinless, boneless chicken thighs (1 1/4 pounds)", BigDecimal.valueOf(6), null);
        Ingredient corn_tortillas = new Ingredient("corn tortillas", BigDecimal.valueOf(8), null);
        Ingredient baby_arugula = new Ingredient("packed baby arugula (3 ounces)", BigDecimal.valueOf(3), cupUom);
        Ingredient avocados2 = new Ingredient("medium ripe avocados, sliced", BigDecimal.valueOf(2), null);
        Ingredient radish = new Ingredient("radishes, thinly sliced", BigDecimal.valueOf(4), null);
        Ingredient cherry_tomato = new Ingredient("cherry tomatoes, halved", BigDecimal.valueOf(0.5), pintUom);
        Ingredient red_onion = new Ingredient("red onion, thinly sliced", BigDecimal.valueOf(1d / 4), null);
        Ingredient cilantro2 = new Ingredient("Roughly chopped cilantro", null, null);
        Ingredient sour_cream = new Ingredient("sour sour_cream thinned with 1/4 cup milk", BigDecimal.valueOf(1 / 2d), cupUom);
        Ingredient lime = new Ingredient("lime, cut into wedges", BigDecimal.ONE, null);

        recipe_grilled_chicken_taco.addIngredient(chili_powder)
                .addIngredient(dried_oregano)
                .addIngredient(dried_cumin)
                .addIngredient(sugar)
                .addIngredient(salt)
                .addIngredient(garlic)
                .addIngredient(orange_zest)
                .addIngredient(orange_juice)
                .addIngredient(olive_oil)
                .addIngredient(chicken_thigh)
                .addIngredient(corn_tortillas)
                .addIngredient(baby_arugula)
                .addIngredient(avocados2)
                .addIngredient(radish)
                .addIngredient(cherry_tomato)
                .addIngredient(red_onion)
                .addIngredient(cilantro2)
                .addIngredient(sour_cream)
                .addIngredient(lime);

        // Recipe of Perfect Guacamole
        recipe_guacamole.setDescription("Perfect Guacamole");
        recipe_guacamole.setDifficulty(Difficulty.EASY);
        recipe_guacamole.setPrepTime(10);
        recipe_guacamole.setServings(4);
        recipe_guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        recipe_guacamole.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.");

        Note guacNote = new Note(recipe_guacamole,
                "For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                        "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries (see our Strawberry Guacamole).\n" +
                        "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                        "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                        "For a deviled egg version with guacamole, try our Guacamole Deviled Eggs!");

        recipe_guacamole.getCategories().add(americanCategory);
        recipe_guacamole.getCategories().add(mexicanCategory);
        recipe_guacamole.addNote(guacNote);

        // Recipe of Grilled Chicken Taco
        recipe_grilled_chicken_taco.setDescription("Spicy Grilled Chicken Tacos");
        recipe_grilled_chicken_taco.setDifficulty(Difficulty.MODERATE);
        recipe_grilled_chicken_taco.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        recipe_grilled_chicken_taco.setCookTime(15);
        recipe_grilled_chicken_taco.setPrepTime(20);
        recipe_grilled_chicken_taco.setServings(6);
        recipe_grilled_chicken_taco.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "Spicy Grilled Chicken Tacos\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.");

        Note tacoNote = new Note(recipe_grilled_chicken_taco,
                "Look for ancho chile powder with the Mexican ingredients at your grocery store, on buy it online." +
                        "(If you can't find ancho chili powder, you replace the ancho chili, the oregano, and the cumin with 2 1/2 tablespoons regular chili powder, though the flavor won't be quite the same.)");

        recipe_grilled_chicken_taco.getCategories().add(mexicanCategory);
        recipe_grilled_chicken_taco.getCategories().add(americanCategory);
        recipe_grilled_chicken_taco.addNote(tacoNote);

        // Collects final recipes

        recipesList.add(recipe_guacamole);
        recipesList.add(recipe_grilled_chicken_taco);
        return recipesList;
    }

    @Override
    @Transactional // use this annotation to get everything run in the same transaction
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipeList());
        log.debug("Data persisted!");
    }
}
