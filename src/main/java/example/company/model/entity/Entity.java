package example.company.model.entity;

public abstract class Entity {
    protected Long id;

    public Long getId() {
        return id;
    }

    // TODO разрешать менять id-шник как-то не оч
    public void setId(Long id) {
        this.id = id;
    }
}
