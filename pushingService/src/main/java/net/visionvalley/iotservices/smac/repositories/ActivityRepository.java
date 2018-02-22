package net.visionvalley.iotservices.smac.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.visionvalley.iotservices.smac.entities.Activity;
import net.visionvalley.iotservices.smac.entities.User;

@Repository
public interface ActivityRepository extends JpaRepository<Activity,Long> {
    Activity findFirstBy();
    Activity findFirstByUserOrderByIdDesc(User user);
    Page<Activity> findByUser(User user, Pageable pageable);
}
