package guru.springframework.domain;

import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
public class Note extends BaseEntity {
    @Lob
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + super.getId() +
                ", description='" + description + '\'' +
                '}';
    }
}
