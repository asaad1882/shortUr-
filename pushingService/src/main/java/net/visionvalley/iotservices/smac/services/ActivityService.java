package net.visionvalley.iotservices.smac.services;

import java.util.List;

import org.springframework.data.domain.Page;

import net.visionvalley.iotservices.smac.entities.Activity;
import net.visionvalley.iotservices.smac.entities.User;

public interface ActivityService {

	Activity findFirst();

	Activity findLast(User user);

	

	Activity findOne(long id);

	List<Activity> findAll();

	void delete(Long id);

	Activity save(Activity activity);

}
