package net.visionvalley.iotservices.smac.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import net.visionvalley.iotservices.smac.entities.Client;


public interface ClientRepository extends JpaRepository<Client,String>{

}
