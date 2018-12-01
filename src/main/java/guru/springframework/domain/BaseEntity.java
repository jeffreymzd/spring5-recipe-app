package guru.springframework.domain;

public abstract class BaseEntity {
    private Long id;

    Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
    }
}
