package com.solosos.restaurant.repository;

import com.solosos.restaurant.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface MenuItemRepository extends JpaRepository<MenuItem,Long> {
}
