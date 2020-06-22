package com.solosos.restaurant.repository;

import com.solosos.restaurant.model.Linked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface LinkedRepository extends JpaRepository<Linked,Long> {
}
