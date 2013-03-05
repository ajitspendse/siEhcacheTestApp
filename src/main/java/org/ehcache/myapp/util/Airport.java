package org.ehcache.myapp.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class Airport {
	public static List<String> getAirportCodes(CacheManager mgr) {
		Cache c = mgr.getCache("airports_cache");
		List<String> airports = c.getKeys();
		List<String> result = new ArrayList<String>();
		Iterator<String> iter = airports.iterator();
		while (iter.hasNext()) {
			String val = iter.next();
			System.out.println(val);
			StringTokenizer tok = new StringTokenizer(val, ",");
			tok.nextToken();// skip rank
			String airport = tok.nextToken().substring(1);
			System.out.println(airport);
			result.add(airport);// get AirportCode
		}
		return result;
	}

	public static void putAirports(CacheManager mgr) throws Exception {
		Cache c = mgr.getCache("airports_cache");
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(
					"/Users/glennrenfro/Documents/airportcodes.csv"));
			while (reader.ready()) {
				String val = reader.readLine();
				System.out.println(val);
				Element element = new Element(val, val);
				c.put(element);
			}
			reader.close();
		} finally {
			if (reader != null) {
				reader.close();
			}
		}

	}

}
