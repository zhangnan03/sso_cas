package cn.symdata.common;
import net.sf.ehcache.Cache; 
import net.sf.ehcache.CacheManager; 
import net.sf.ehcache.config.CacheConfiguration; 
import net.sf.ehcache.store.MemoryStoreEvictionPolicy; 
import net.sf.ehcache.Element; 
/**
 * 
 *@Copyright:Copyright (c) 2012-2015
 *@Company:zplay.cn
 *@Title:
 *@Description:
 *@Author:wangdezhen#zplay.cn
 *@Since:2015年1月14日  下午3:04:13
 *@Version:1.0
 */
public class EhCacheManager {
	private static final CacheManager cacheManager = CacheManager.getInstance(); 
	private static Cache cache = new Cache(new CacheConfiguration("passwordRetryCache", 5000).memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.FIFO).timeoutMillis(300).timeToLiveSeconds( 60 * 60)); 
    static { 
        cacheManager.addCache(cache); 
    } 
    public static void putItem(String key, Object item) { 
        if (cache.get(key) != null) { 
            cache.remove(key); 
        } 
        Element element = new Element(key, item); 
        cache.put(element); 
    } 
    public static void removeItem(String key) { 
        cache.remove(key); 
    } 
    public static void updateItem(String key, Object value) { 
        putItem(key, value); 
    } 
    public static Object getItem(String key) { 
    	Element element=  cache.get(key); 
        if(null!=element) 
        { 
            return element.getObjectValue(); 
        } 
        return null; 
    } 
}
