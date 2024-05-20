package entities;

import javax.persistence.*;

@Entity
@Table(name = "positions")
public class Position {
    @Id
    @Column(length = 2)
    private String id;

    @Basic
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
