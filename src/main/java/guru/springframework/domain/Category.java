package guru.springframework.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"recipes"})
@Entity
public class Category extends BaseEntity {
    private String description;
    @ManyToMany(mappedBy = "categories") // property on the other side of the relationship
    private Set<Recipe> recipes = new HashSet<>();
}
