package uz.delivery_system.component;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * @author Dilshod Tadjiev.
 */
@Component
public class EhCacheBean {

    @Autowired
    private EhCacheCacheManager cacheCacheManager;

    public boolean containsUserToken(String username) {
        return this.getCache().get(username) != null;
    }

    public void putUserDetails(UserDetails userDetails) {
        getCache().put(new Element(userDetails.getUsername(), userDetails));
    }

    public void removeUser(String username){
        getCache().remove(username);
    }

    public UserDetails getUserDetails(String username) {
        if (getCache().isKeyInCache(username) && getCache().get(username) != null) {
            return (UserDetails) getCache().get(username).getObjectValue();
        }
        return null;
    }

    private Cache getCache() {
        return cacheCacheManager.getCacheManager().getCache("USERCACHE");
    }
}
