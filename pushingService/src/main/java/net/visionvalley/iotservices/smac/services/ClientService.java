package net.visionvalley.iotservices.smac.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.visionvalley.iotservices.smac.entities.Client;
import net.visionvalley.iotservices.smac.repositories.ClientRepository;


@Service
public class ClientService {
	 @Autowired
	    private ClientRepository clientRepository;

	   
	    public void save(Client client){	       
	        clientRepository.save(client);
	    }

}
