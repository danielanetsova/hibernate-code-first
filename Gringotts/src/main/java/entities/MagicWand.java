package entities;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "magic_wands")
public class MagicWand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "magic_wand_creator", length = 100)
    private String magicWandCreator;

    @Positive
    @Column(name = "magic_wand_size")
    private byte magicWandSize;

    public MagicWand() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMagicWandCreator() {
        return magicWandCreator;
    }

    public void setMagicWandCreator(String magicWandCreator) {
        this.magicWandCreator = magicWandCreator;
    }

    public byte getMagicWandSize() {
        return magicWandSize;
    }

    public void setMagicWandSize(byte magicWandSize) {
        this.magicWandSize = magicWandSize;
    }
}
