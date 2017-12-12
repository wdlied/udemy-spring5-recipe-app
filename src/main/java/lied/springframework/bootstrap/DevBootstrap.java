package lied.springframework.bootstrap;

import lied.springframework.domain.*;
import lied.springframework.repositories.CategoryRepository;
import lied.springframework.repositories.RecipeRepository;
import lied.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent>{

    private RecipeRepository recipeRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;
    private CategoryRepository categoryRepository;

    public DevBootstrap(RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository categoryRepository) {
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {

        log.debug("initializing data");

        //Get references to UnitOfMeasures
        Optional<UnitOfMeasure> optionalEachUom = unitOfMeasureRepository.findByDescription("each");
        if (!optionalEachUom.isPresent()) {
            throw new RuntimeException("Expected UOM Not found");
        }
        Optional<UnitOfMeasure> optionalTeaspoonUom = unitOfMeasureRepository.findByDescription("teaspoon");
        if (!optionalTeaspoonUom.isPresent()) {
            throw new RuntimeException("Expected UOM Not found");
        }
        Optional<UnitOfMeasure> optionalTablespoonUom = unitOfMeasureRepository.findByDescription("tablespoon");
        if (!optionalTablespoonUom.isPresent()) {
            throw new RuntimeException("Expected UOM Not found");
        }
        Optional<UnitOfMeasure> optionalCupUom = unitOfMeasureRepository.findByDescription("cup");
        if (!optionalCupUom.isPresent()) {
            throw new RuntimeException("Expected UOM Not found");
        }
        Optional<UnitOfMeasure> optionalPinchUom = unitOfMeasureRepository.findByDescription("pinch");
        if (!optionalPinchUom.isPresent()) {
            throw new RuntimeException("Expected UOM Not found");
        }
        Optional<UnitOfMeasure> optionalOunceUom = unitOfMeasureRepository.findByDescription("ounce");
        if (!optionalOunceUom.isPresent()) {
            throw new RuntimeException("Expected UOM Not found");
        }
        Optional<UnitOfMeasure> optionalDashUom = unitOfMeasureRepository.findByDescription("dash");
        if (!optionalDashUom.isPresent()) {
            throw new RuntimeException("Expected UOM Not found");
        }
        Optional<UnitOfMeasure> optionalPintUom = unitOfMeasureRepository.findByDescription("pint");
        if (!optionalPintUom.isPresent()) {
            throw new RuntimeException("Expected UOM Not found");
        }

        //get units of measure
        UnitOfMeasure eachUom = optionalEachUom.get();
        UnitOfMeasure teaspoonUom = optionalTeaspoonUom.get();
        UnitOfMeasure tablespoonUom = optionalTablespoonUom.get();
        UnitOfMeasure cupUom = optionalCupUom.get();
        UnitOfMeasure pinchUom = optionalPinchUom.get();
        UnitOfMeasure ounceUom = optionalOunceUom.get();
        UnitOfMeasure dashUom = optionalDashUom.get();
        UnitOfMeasure pintUom = optionalPintUom.get();

        //Perfect Guacamole
        Recipe guacamoleRecipe = new Recipe();
        guacamoleRecipe.setDescription("Perfect Guacamole");
        guacamoleRecipe.setPrepTime(10);
        guacamoleRecipe.setCookTIme(0);
        guacamoleRecipe.setServings(4);
        guacamoleRecipe.setSource("Simply Recipes");
        guacamoleRecipe.setUrl("http://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamoleRecipe.setDirections("1. Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. Place in a bowl.\n" +
                "2. Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "3. Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this guacamoleRecipe and adjust to your taste.\n" +
                "4. Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz50roCXufg");
        guacamoleRecipe.setDifficulty(Difficulty.EASY);

        guacamoleRecipe.getCategories().add(categoryRepository.findByDescription("Mexican").get());
        guacamoleRecipe.getCategories().add(categoryRepository.findByDescription("American").get());

        Notes notes = new Notes();
        guacamoleRecipe.setNotes(notes);
        notes.setRecipeNotes("Be careful handling chiles if using. Wash your hands thoroughly after handling and do not touch your eyes or the area near your eyes with your hands for several hours.");

        guacamoleRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), eachUom));
        guacamoleRecipe.addIngredient(new Ingredient("Kosher salt", new BigDecimal(0.5), teaspoonUom));
        guacamoleRecipe.addIngredient(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(1), tablespoonUom));
        guacamoleRecipe.addIngredient(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), tablespoonUom));
        guacamoleRecipe.addIngredient(new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal(1), eachUom));
        guacamoleRecipe.addIngredient(new Ingredient("cilantro (leaves and tender stems), finely chopped", new BigDecimal(2), tablespoonUom));
        guacamoleRecipe.addIngredient(new Ingredient("freshly grated black pepper", new BigDecimal(1), dashUom));
        guacamoleRecipe.addIngredient(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(0.5), eachUom));

        recipeRepository.save(guacamoleRecipe);


        //Spicy Grilled Chicken Tacos
        Recipe chickenTacoRecipe = new Recipe();
        chickenTacoRecipe.setDescription("Spicy Grilled Chicken Tacos");
        chickenTacoRecipe.setPrepTime(20);
        chickenTacoRecipe.setCookTIme(15);
        chickenTacoRecipe.setServings(6);
        chickenTacoRecipe.setSource("Simply Recipes");
        chickenTacoRecipe.setUrl("http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        chickenTacoRecipe.setDirections("1. Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "2. Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "3. Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "4. Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "5. Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz50ssdzkmR");
        chickenTacoRecipe.setDifficulty(Difficulty.MODERATE);

        chickenTacoRecipe.getCategories().add(categoryRepository.findByDescription("Mexican").get());
        chickenTacoRecipe.getCategories().add(categoryRepository.findByDescription("American").get());

        notes = new Notes();
        chickenTacoRecipe.setNotes(notes);
        notes.setRecipeNotes("Look for ancho chile powder with the Mexican ingredients at your grocery store, on buy it online. (If you can't find ancho chili powder, you replace the ancho chili, the oregano, and the cumin with 2 1/2 tablespoons regular chili powder, though the flavor won't be quite the same.)\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz50ssX7FCA");

        //Ingredients
        chickenTacoRecipe.addIngredient(new Ingredient("ancho chili powder", new BigDecimal(2), tablespoonUom));
        chickenTacoRecipe.addIngredient(new Ingredient("dried oregano", new BigDecimal(1), teaspoonUom));
        chickenTacoRecipe.addIngredient(new Ingredient("dried cumin", new BigDecimal(1), teaspoonUom));
        chickenTacoRecipe.addIngredient(new Ingredient("sugar", new BigDecimal(1), teaspoonUom));
        chickenTacoRecipe.addIngredient(new Ingredient("salt", new BigDecimal(0.5), teaspoonUom));
        chickenTacoRecipe.addIngredient(new Ingredient("clove garlic, finely chopped", new BigDecimal(1), eachUom));
        chickenTacoRecipe.addIngredient(new Ingredient("finely grated orange zest", new BigDecimal(1), tablespoonUom));
        chickenTacoRecipe.addIngredient(new Ingredient("fresh squeezed orange juice", new BigDecimal(3), tablespoonUom));
        chickenTacoRecipe.addIngredient(new Ingredient("olive oil", new BigDecimal(2), tablespoonUom));
        chickenTacoRecipe.addIngredient(new Ingredient("skinless, boneless chicken thighs (1 1/4 pounds)", new BigDecimal(6), eachUom));

// Serve
        chickenTacoRecipe.addIngredient(new Ingredient("small corn tortillas", new BigDecimal(8), eachUom));
        chickenTacoRecipe.addIngredient(new Ingredient("packed baby arugula", new BigDecimal(3), cupUom));
        chickenTacoRecipe.addIngredient(new Ingredient("medium ripe avocados, sliced", new BigDecimal(2), eachUom));
        chickenTacoRecipe.addIngredient(new Ingredient("radishes, thinly sliced", new BigDecimal(4), eachUom));
        chickenTacoRecipe.addIngredient(new Ingredient("cherry tomatoes, halved", new BigDecimal(0.5), pintUom));
        chickenTacoRecipe.addIngredient(new Ingredient("red onion, thinly sliced", new BigDecimal(0.25), eachUom));
        chickenTacoRecipe.addIngredient(new Ingredient("roughly chopped cilantro", new BigDecimal(1), eachUom));
        chickenTacoRecipe.addIngredient(new Ingredient("sour cream thinned with 1/4 cup milk", new BigDecimal(0.5), cupUom));
        chickenTacoRecipe.addIngredient(new Ingredient("lime, cut into wedges", new BigDecimal(1), eachUom));

        recipeRepository.save(chickenTacoRecipe);

    }
}
