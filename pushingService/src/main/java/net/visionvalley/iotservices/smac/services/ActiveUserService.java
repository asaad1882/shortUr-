package net.visionvalley.iotservices.smac.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Sets;
@Service
public class ActiveUserService {
	private LoadingCache<String, UserStats> statsByUser = CacheBuilder.newBuilder().build(new CacheLoader<String, UserStats>() {

	    @Override
	    public UserStats load(String key) throws Exception {
	      return new UserStats();
	    }
	    
	  });
	  
	  public void mark(String username) {
	    statsByUser.getUnchecked(username).mark();
	  }
	  public void unMark(String username){
		  statsByUser.invalidate(username);
	  }
	  
	  public List<String> getActiveUsers() {
	    List<String> active = new ArrayList<>();
	    for (String user : statsByUser.asMap().keySet()) {
	      // has the user checked in within the last 5 seconds?
	     // if ((System.currentTimeMillis() - statsByUser.getUnchecked(user).lastAccess()) < 5000) {
	        active.add(user);
	      //}
	    }
	    return active;
	  }
	  
	  private static class UserStats {
	    
	    private AtomicLong lastAccess = new AtomicLong(System.currentTimeMillis());
	    
	    private void mark() {
	      lastAccess.set(System.currentTimeMillis());
	    }
	    
	    private long lastAccess() {
	      return lastAccess.get();
	    }
	    
	}
}
