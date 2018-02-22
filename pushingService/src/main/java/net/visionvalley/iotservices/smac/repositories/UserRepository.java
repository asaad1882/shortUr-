package net.visionvalley.iotservices.smac.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import net.visionvalley.iotservices.smac.entities.User;

/**
 * User repository for CRUD operations.
 */
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
