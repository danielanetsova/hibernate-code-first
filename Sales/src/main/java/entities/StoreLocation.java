package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "store_locations")
public class StoreLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "location_name")
    private String locationName;

    @OneToMany(targetEntity = Sale.class, mappedBy = "location")
    private Set<Sale> sales;

    public StoreLocation(){};

    public StoreLocation(String locationName) {
        this.locationName = locationName;
    }

    public long getId() {
        return id;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
