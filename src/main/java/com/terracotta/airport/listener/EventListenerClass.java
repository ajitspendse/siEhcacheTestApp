package com.terracotta.airport.listener;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.event.CacheEventListener;

import com.terracotta.airport.AirportRunner;

public class EventListenerClass implements CacheEventListener,Cloneable {

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}


	@Override
	public void notifyElementEvicted(Ehcache paramEhcache, Element paramElement) {
		if (paramElement != null && paramElement.getKey() != null) {
			System.out.println(paramElement.getKey() + " was evicted");
		}
	}

	@Override
	public void notifyElementExpired(Ehcache paramEhcache, Element paramElement) {
		if (paramElement != null && paramElement.getKey() != null) {
			System.out.println(paramElement.getKey() + " was expired");
			//This is not a good idea, but wanted to try it to see the behavior.
			AirportRunner runner = new AirportRunner();	
			runner.getAirportStatus((Cache)paramEhcache, paramElement.getKey().toString());
		}
	}

	@Override
	public void notifyElementPut(Ehcache paramEhcache, Element paramElement)
			throws CacheException {
		if (paramElement != null && paramElement.getKey() != null) {
			System.out.println("Listener " + paramElement.getKey() + " was put");
		}	

	}

	@Override
	public void notifyElementRemoved(Ehcache paramEhcache, Element paramElement)
			throws CacheException {
				if (paramElement != null && paramElement.getKey() != null) {
					System.out.println(paramElement.getKey() + " was removed");
				}
	}

	@Override
	public void notifyElementUpdated(Ehcache paramEhcache, Element paramElement)
			throws CacheException {
		if (paramElement != null && paramElement.getKey() != null) {
			System.out.println(paramElement.getKey() + " was updated");
		}

	}

	@Override
	public void notifyRemoveAll(Ehcache paramEhcache) {
		// TODO Auto-generated method stub
		System.out.println("Remove All from EventListener!");
	}

}
