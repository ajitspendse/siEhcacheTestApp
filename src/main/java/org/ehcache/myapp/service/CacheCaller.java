package org.ehcache.myapp.service;

import org.springframework.integration.Message;

public interface CacheCaller {
		public Object putData(Object data);
		public Object getData(Object data);
		public Object printData(Object data);
	
		
	}
