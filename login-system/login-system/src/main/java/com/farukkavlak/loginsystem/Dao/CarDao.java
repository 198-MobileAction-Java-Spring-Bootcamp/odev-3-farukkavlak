package com.farukkavlak.loginsystem.Dao;

import com.farukkavlak.loginsystem.Entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//Car repository
@Repository
public interface CarDao extends JpaRepository<Car,Long> {

    List<Car> findByBrandAndModel(String brand, String model);

    boolean existsByPlate(String plate);

    Car getByPlate(String plate);
}