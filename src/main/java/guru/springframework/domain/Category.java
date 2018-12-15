package guru.springframework.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Category extends BaseEntity {
    private String description;
    @ManyToMany(mappedBy = "categories") // property on the other side of the relationship
    private Set<Recipe> recipes = new HashSet<>();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + super.getId() +
                ", description='" + description + '\'' +
                ", recipes=" + recipes +
                '}';
    }
}
