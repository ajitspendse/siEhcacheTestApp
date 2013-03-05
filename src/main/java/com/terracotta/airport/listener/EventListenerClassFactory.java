package com.terracotta.airport.listener;

import java.util.Properties;

import net.sf.ehcache.event.CacheEventListener;
import net.sf.ehcache.event.CacheEventListenerFactory;

public class EventListenerClassFactory extends CacheEventListenerFactory {

	@Override
	public CacheEventListener createCacheEventListener(Properties paramProperties) {
		// TODO Auto-generated method stub
		if(paramProperties.contains("statusCache")) {
			return new EventListenerClass();
		}
		return new EventListenerClass();

	}

}
