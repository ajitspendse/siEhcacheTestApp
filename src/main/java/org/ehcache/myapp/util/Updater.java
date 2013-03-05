package org.ehcache.myapp.util;

import java.util.Iterator;
import java.util.TimerTask;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.ehcache.myapp.dao.FaaDao;
import org.ehcache.myapp.service.CacheCaller;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public  class Updater extends TimerTask {

	AbstractApplicationContext context;
	CacheCaller getService;
	CacheCaller putService;
	FaaDao dao ;
	CacheManager mgr;
	public Updater(){
		context = new ClassPathXmlApplicationContext(
				"classpath:META-INF/spring/integration/*-context.xml");
		getService = (CacheCaller) context
				.getBean("getGateway");
		putService = (CacheCaller) context
				.getBean("putGateway");
		mgr = CacheManager.getCacheManager("TestCache");
		dao = (FaaDao) context.getBean("faaDao");
		System.out.println("Updating NOW");
	}
        public void run() {
        	System.out.println("RUNNING NOW");
        	Iterator<String> airportCodes = org.ehcache.myapp.util.Airport
    				.getAirportCodes(mgr).iterator();
        	
        	while (airportCodes.hasNext()) {
    			final String input = airportCodes.next();
    			Element element = new Element(input,"");
    			element = dao.getData(element);
    			System.out.println(element);
    			if(element != null)
    			{
    				putService.putData(element);
    			}
    			//System.out.println("WE ARE GETTING CALLED IN THE TIMER... Test Cache Get ******"
				//	+ ((Element) getService.getData(element)).getObjectValue());
    			
    			System.out.print(".");
        	}
        }
    }

