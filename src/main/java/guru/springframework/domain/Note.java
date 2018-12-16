package guru.springframework.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Note extends BaseEntity {
    public Note() {
    }

    public Note(Recipe recipe, String description) {
        this.recipe = recipe;
        this.description = description;
    }

    @ManyToOne
    private Recipe recipe;
    @Lob
    private String description;
}
