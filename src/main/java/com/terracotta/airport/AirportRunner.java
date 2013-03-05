package com.terracotta.airport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.search.Attribute;
import net.sf.ehcache.search.Query;
import net.sf.ehcache.search.Result;
import net.sf.ehcache.search.Results;

import org.codehaus.jackson.map.ObjectMapper;

import com.terracotta.airport.model.Airport;
import com.terracotta.airport.model.AirportStatus;



public class AirportRunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//runStressTest();
		runAirportTests();

	}
	public static void runStressTest(){
		CacheManager mgr = null;
		try {
			mgr = CacheManager.getInstance();
			Cache c = mgr.getCache("airports_cache");
			int i=1;
			while(i++>0){
				Element e = new Element("element"+i,"element"+i);
				c.put(e);
				if(i%1000==0){
					System.out.print(".");
				}
				if(i%10000==0){
					Thread.sleep(2000);
					System.out.println(i);
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		finally {
			System.out.println("We shut down");
			if(mgr != null){
				mgr.shutdown();
			}
		}		
	}
	public static void runAirportTests(){
		CacheManager mgr = null;
		try {
			mgr = CacheManager.getInstance();
			//mgr = new CacheManager(
			//		"/Users/glennrenfro/Documents/workspace-sts-2.9.1.RELEASE/TrainEhCache3.5.3/src/ehcache.xml");

			AirportRunner airportRunner = new AirportRunner();
			airportRunner.initializeAirportCache(mgr);
			//Cache airportCache = airportRunner.loadAirports(mgr);
			airportRunner.testCache(mgr);
			airportRunner.testCache(mgr);
			String code = "ATL";
	//		mgr.addCache("statusCache");
			Cache airportStatusCache = mgr.getCache("statusCache");
			airportRunner.getAirportStatus(airportStatusCache, code);
			// airportRunner.reloadAirportStatuses(airportCache,airportStatusCache);
			airportRunner.getAirportStatus(airportStatusCache, code);
			code = "IAD";
			airportRunner.getAirportStatus(airportStatusCache, code);
			airportRunner.getAirportStatus(airportStatusCache, code);
			code = "SFO";
			airportRunner.getAirportStatus(airportStatusCache, code);
			airportRunner.searchAirport(airportStatusCache, code);
			code = "FUBAR";
			airportRunner.searchAirport(airportStatusCache, code);
			System.out.println(airportStatusCache.get(code));
			airportRunner.searchGoodAirports(airportStatusCache);
			//airportRunner.reloadAirportStatuses(airportCache,airportStatusCache);
			
			for(int a= 0;a<21;a++){
				Thread.sleep(5000);
				try{
					System.out.println(airportStatusCache.get("ATL").getValue());
				}catch(NullPointerException npe){
					System.out.println("Missing the ATL");
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		finally {
			System.out.println("We shut down");
			if(mgr != null){
				mgr.shutdown();
			}
		}		
	}
    public void testCache(CacheManager mgr){
    	Cache c = mgr.getCache("airports_cache");
		c.setNodeBulkLoadEnabled(true);
		BufferedReader in=null;
		try{
		URL service = new URL(
				"http://airportcode.riobard.com/search?q=&fmt=json");
		URLConnection con = service.openConnection();
		in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));

		ObjectMapper mapper = new ObjectMapper();
		Airport[] airports = mapper.readValue(in, Airport[].class);
		int i=0;
		for (Airport airport : airports) {
			if(airport.getLocation().indexOf("United States") > 0){
				System.out.println();
				System.out.println(c.get(airport.getCode()));
				i++;
				if(i>100){
					break;
				}
			}
			else
				System.out.print(".");
		}
		
		in.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
    	
    }
	public Cache loadAirports(CacheManager mgr) {

		Cache c = null;
		try {
			c = mgr.getCache("airports_cache");
			c.setNodeBulkLoadEnabled(true);
			URL service = new URL(
					"http://airportcode.riobard.com/search?q=&fmt=json");
			URLConnection con = service.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));

			ObjectMapper mapper = new ObjectMapper();
			Airport[] airports = mapper.readValue(in, Airport[].class);
			
			for (Airport airport : airports) {
				Element e = new Element(airport.getCode(), airport);
				boolean cached = false;
				if (c.isKeyInCache(airport.getCode())) {
					airport = (Airport) c.get(airport.getCode()).getValue();
					cached = true;
				} else {
					if (airport.getLocation().indexOf("United States") > 0) {
						c.put(e);
					} else {
						continue;
					}
				}
				System.out.println(cached + " " + airport);
			}
			
			in.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			c.setNodeBulkLoadEnabled(false);
		}
		return c;
	}
	public Cache initializeAirportCache(CacheManager mgr) {

		Cache c = null;
		try {
			c = mgr.getCache("airports_cache");
			c.setNodeBulkLoadEnabled(true);
			URL service = new URL(
					"http://airportcode.riobard.com/search?q=&fmt=json");
			URLConnection con = service.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));

			ObjectMapper mapper = new ObjectMapper();
			Airport[] airports = mapper.readValue(in, Airport[].class);
			ArrayList<Element>airportList = new ArrayList<Element>();
			for (Airport airport : airports) {
				Element e = new Element(airport.getCode(), airport);
					if (airport.getLocation().indexOf("United States") > 0) {
						c.put(e);
					} else {
						continue;
					}
				System.out.println(airport);
			}
			
			in.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			c.setNodeBulkLoadEnabled(false);
		}
		System.out.println(c.get("SFO").getValue());
		return c;
	}
	public AirportStatus getAirportStatus(Cache c, String code) {
		AirportStatus status = null;
		if (c.isKeyInCache(code)) {
			System.out.println("cache hit");
			return (AirportStatus) c.get(code).getValue();
		}
		try {
			URL service = new URL("http://services.faa.gov/airport/status/"
					+ code + "?format=json");
			URLConnection con = service.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			ObjectMapper mapper = new ObjectMapper();
			status = mapper.readValue(in, AirportStatus.class);
			System.out.println(status);
			c.put(new Element(status.getIATA(), status));
			System.out.println("Cache Miss on " + code + " - "
					+ status.getIATA());
			in.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();//no Action
		}
		if(status == null){
			System.out.println("CACHE FAIL for "+code);
		}else{
			System.out.println("*"+status.getStatus().getReason()+"*");
		}
		return status;
	}

	public void reloadAirportStatuses(Cache airportCache, Cache airportStatus) {
		List<String> airports = airportCache.getKeys();
		for (String code : airports) {
			try{
				getAirportStatus(airportStatus, code);
			}catch(Exception e){
				continue;
			}
			System.out.println("+" + code + "+");
		}
	}

	public void searchAirport(Cache c, String code) {

		try {
			Attribute<String> iata = c.getSearchAttribute("iata");
			Query query = c.createQuery().addCriteria(iata.eq(code))
					.includeKeys().end();
			Results results = query.execute();
			System.out.println(results.size());
			
		} catch (Exception e) {
			System.out.println("You did it");
			e.printStackTrace();
		}
	}
	public void searchGoodAirports(Cache c) {

		try {
			CacheConfiguration config = c.getCacheConfiguration();
			Attribute<String> reason = c.getSearchAttribute("reason");
			Query query = c.createQuery().addCriteria(reason.eq("No known delays for this airport."))
					.includeKeys().end();
			Results results = query.execute();
		
			System.out.println(results.size());
			for(Result result: results.all()){
				System.out.println(result.getKey());
			}
			
		} catch (Exception e) {
			System.out.println("You did it");
			e.printStackTrace();
		}
	}
	public void getAllUSAirports(Cache c){
		try {
			CacheConfiguration config = c.getCacheConfiguration();
			Attribute<String> reason = c.getSearchAttribute("location");
			Query query = c.createQuery().addCriteria(reason.ilike("*United States"))
					.includeKeys().end();
			Results results = query.execute();
		
			System.out.println(results.size());
			for(Result result: results.all()){
				System.out.println(result.getKey());
			}
			
		} catch (Exception e) {
			System.out.println("You did it");
			e.printStackTrace();
		}
	}

}
