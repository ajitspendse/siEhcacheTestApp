package org.ehcache.myapp.router;

import net.sf.ehcache.Element;

public class CacheCallerRouter {

	public static String END_CHANNEL = "endChannel";
	public static String REQUEST_CHANNEL = "faaDataChannel";
	public String cacheRouter(Object o){
		Element payLoad = (Element)o;
		if (payLoad.getValue()!=null){
			return END_CHANNEL;
		}
		return REQUEST_CHANNEL;
	}
}
