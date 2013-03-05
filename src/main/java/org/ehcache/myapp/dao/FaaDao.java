package org.ehcache.myapp.dao;

import net.sf.ehcache.Element;

public interface FaaDao {
	public Element getData(Element airportCode);
}
