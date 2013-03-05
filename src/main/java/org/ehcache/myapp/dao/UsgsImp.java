package org.ehcache.myapp.dao;

import org.ehcache.siehcache.EhcacheAdapterHeaders;
import org.springframework.integration.support.MessageBuilder;

import net.sf.ehcache.Element;

public class UsgsImp implements Usgs {
	public Object convert(com.sun.syndication.feed.synd.SyndEntryImpl myString)
	{
		//System.out.println("******************************************"+myString.toString());
		String data = myString.getDescription().getValue();
		int index = data.indexOf("alt=\"")+5;
		data = data.substring(index);
		index = data.indexOf(" ");
		
		String longitude = data.substring(0, index);
		data = data.substring(index);
		String latitude = data.substring(1,data.indexOf("\""));
		System.out.println("********"+longitude+"*****"+latitude);
		String key = longitude+","+latitude;
		return MessageBuilder.withPayload(new Element(key,key)).setHeader(EhcacheAdapterHeaders.COMMAND,EhcacheAdapterHeaders.PUT)
		.build();
	}
	public void printMe(Object myString)
	{
		System.out.println(myString);
	}
}
