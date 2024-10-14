package org.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Entity
@Builder
@Data
public class MartialArt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String origin;


//    // Constructor
//    public MartialArt() {}
//
//    public MartialArt(String name) {
//        this.name = name;
//    }

//    // Getters
//    public Long getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getOrigin() {
//        return origin;
//    }
//
//    // Setters
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setOrigin(String origin) {
//        this.origin = origin;
//    }
}
