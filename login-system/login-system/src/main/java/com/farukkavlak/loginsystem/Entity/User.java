package com.farukkavlak.loginsystem.Entity;

import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="UserTable")
@RequiredArgsConstructor
public class User{

    //ID generate in sequence(0,1,2...)
    @Id
    @SequenceGenerator(name = "user_seq",sequenceName = "user_seq")
    @GeneratedValue(generator = "user_seq")
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "surname",nullable = false)
    private String surname;
    
    
    @Column(name = "username",nullable = false)
    private String username;
    
    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "password",nullable = false)
    private String password;

    //One user can have many cars
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_car_fk",referencedColumnName = "id")
    List<Car> cars = new ArrayList<>();
}
