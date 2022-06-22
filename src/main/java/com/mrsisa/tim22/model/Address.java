package com.mrsisa.tim22.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Address {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String city;
    @Column
    private String country;
    @Column
    private String streetName;
    @Column
    private int streetNumber;
    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.MERGE, mappedBy = "address")
    private Set<SystemEntity> systemEntities = new HashSet<>();

    public Address(String city, String country, String streetName, int streetNumber) {
        this.city = city;
        this.country = country;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
    }
    public void addSystemEntity(SystemEntity entity) {
        systemEntities.add(entity);
    }
    public void removeSystemEntity(SystemEntity entity) {
        systemEntities.remove(entity);
    }


}
