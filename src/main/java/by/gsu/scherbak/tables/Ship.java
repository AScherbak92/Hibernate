package by.gsu.scherbak.tables;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/*
 * Ship map class for table "Ship"
 *
 * @version      1.1 23.05.2025
 * @author       Scherbak Andrey
 * */
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;                 //id of the ship (primary key)
    private Integer tonnage;            //Tonnage of the ship
    @Basic
    private LocalDate constructionDate; //Date of counstruction of the ship
    @ManyToOne
    @JoinColumn(name = "captain_id")
    private Captain captain;            //Captain of the ship
    @Column(unique = true, nullable = false)
    private String shipName;            //Name of the ship

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTonnage() {
        return tonnage;
    }

    public void setTonnage(Integer tonnage) {
        this.tonnage = tonnage;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public Captain getCaptain() {
        return captain;
    }

    public void setCaptain(Captain captain) {
        this.captain = captain;
    }

    public LocalDate getConstructionDate() {
        return constructionDate;
    }

    public void setConstructionDate(LocalDate constructionDate) {
        this.constructionDate = constructionDate;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "id=" + id +
                ", tonnage=" + tonnage +
                ", constructionDate=" + constructionDate +
                ", captain=" + (captain != null ? captain.getName() : "null") +
                ", shipName='" + shipName + '\'' +
                '}';
    }
}

