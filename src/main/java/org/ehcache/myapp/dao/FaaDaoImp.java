package org.ehcache.myapp.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import net.sf.ehcache.Element;

public class FaaDaoImp implements FaaDao{

	public Element getData(Element airportCode){
		Element result = null;
		String value="";
		String key= (String)airportCode.getKey();
        try {  
            URL service = new URL("http://services.faa.gov/airport/status/"+key+"?format=json");  
            URLConnection con = service.openConnection();  
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));  
            String line;  
            
            while ((line = in.readLine()) != null) {  
                System.out.println(line);  
                value+=line;
            }  
            in.close();  
            System.out.println(value);
            result = new Element(airportCode.getKey(),value);
        } catch (MalformedURLException e) {  
            e.printStackTrace();  
        }
        catch (FileNotFoundException fe){
        	System.out.println("Airport code "+airportCode+" not tracked by the FAA");
        } 
        catch (IOException e) {  
            System.out.println("Can't Reach Host because "+e.getMessage());  
        } 
        return result;
	}

}
