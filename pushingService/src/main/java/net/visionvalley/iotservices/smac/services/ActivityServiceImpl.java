package net.visionvalley.iotservices.smac.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import net.visionvalley.iotservices.smac.entities.Activity;
import net.visionvalley.iotservices.smac.entities.User;
import net.visionvalley.iotservices.smac.repositories.ActivityRepository;

@Service
public class ActivityServiceImpl implements ActivityService {
    private final ActivityRepository activityRepo;

    @Autowired
    public ActivityServiceImpl(ActivityRepository activityRepo) {
        this.activityRepo = activityRepo;
    }
    @Override
    public Activity save(Activity activity) {
        if (activity.getId() == null) { // new activity (user logged in)
            Activity firstActivity = this.findFirst();
            if (firstActivity != null) {
                long total = firstActivity.getTotalVisitors();
                activity.setTotalVisitors(++total);
                firstActivity.setTotalVisitors(total);
                this.activityRepo.save(firstActivity);
            }
        }
        return this.activityRepo.save(activity);
    }

    @Override
    public Activity findFirst() {
        return this.activityRepo.findFirstBy();
    }

    @Override
    public Activity findLast(User user) {
        return activityRepo.findFirstByUserOrderByIdDesc(user);
    }

  

    @Override
    public Activity findOne(long id) {
        return null;
    }

    @Override
    public List<Activity> findAll() {
        return this.activityRepo.findAll();
    }

    @Override
    public void delete(Long id) {
       // this.activityRepo.deleteById(id);
    }

}