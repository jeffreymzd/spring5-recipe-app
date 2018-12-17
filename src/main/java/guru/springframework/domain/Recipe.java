package guru.springframework.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "recipes")
public class Recipe extends BaseEntity {
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    @Lob
    private String directions;
    private Byte[] image;
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    // note entry will also be removed when this recipe entry is removed -- CascadeType.ALL
    // no need to set up repository
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "recipe")
    private Set<Note> notes = new HashSet<>();

    public Note addNote(Note note) {
        notes.add(note);
        return note;
    }

    // note entry will also be removed when this recipe entry is removed -- CascadeType.ALL
    // no need to set up repository
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe", fetch = FetchType.EAGER)
    private Set<Ingredient> ingredients = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "recipe_category" // specify join table name
            , joinColumns = @JoinColumn(name = "recipe_id") // specify join column name 1
            , inverseJoinColumns = @JoinColumn(name = "category_id")) // specify join column name 2
    private Set<Category> categories = new HashSet<>();

    public Recipe addIngredient(Ingredient ingredient) {
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }
}
