package by.gsu.scherbak.tables;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.time.LocalDate;

//При помощи criteria API делать выборки по условию. Найти все корабли с датой
//выпуска выше даты какой-то. Найти все корабли, капитан которого старше возраста.

/*
 * Ship map class for table "Ship_captain"
 *
 * @version      1.1 23.05.2025
 * @author       Scherbak Andrey
 * */
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Ship_captain")
public class Captain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;                 //id of the captain (primary key)
    private Integer age;                //age of the captain
    private Integer experience;         //experience of the captain
    private String name;                //name of the captain
    private String surname;             //surname of the captain
    private String lastname;            //lastname of the captain
    @Basic
    private LocalDate birthDate;        //birthdate of the captain
    @OneToMany(mappedBy = "captain", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ship> ships;           //List of captain's ships

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Captain{" +
                "birthDate=" + birthDate +
                ", lastname='" + lastname + '\'' +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", experience=" + experience +
                ", age=" + age +
                ", id=" + id +
                '}';
    }
}
