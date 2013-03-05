/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehcache.myapp;

import java.util.Iterator;
import java.util.Timer;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;
import org.ehcache.myapp.service.CacheCaller;
import org.ehcache.myapp.util.Updater;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Starts the Spring Context and will initialize the Spring Integration routes.
 * 
 * @author Your Name Here
 * @version 1.0
 * 
 */
public final class Main {

	private static final Logger LOGGER = Logger.getLogger(Main.class);

	private Main() {
	}

	/**
	 * Load the Spring Integration Application Context
	 * 
	 * @param args
	 *            - command line arguments
	 */
	public static void main(final String... args) throws Exception {
		final AbstractApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:META-INF/spring/integration/*-context.xml");

		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Exiting application...bye.");
		}
		CacheManager mgr = CacheManager.getCacheManager("TestCache");
		org.ehcache.myapp.util.Airport.putAirports(mgr);
		testHarness(context);
		// System.exit(0);

	}

	public static void testHarness(AbstractApplicationContext context)
			throws Exception {

		context.registerShutdownHook();
		CacheManager mgr = CacheManager.getCacheManager("TestCache");
		Iterator<String> airportCodes = org.ehcache.myapp.util.Airport
				.getAirportCodes(mgr).iterator();

		final CacheCaller service = (CacheCaller) context
				.getBean("cacheAsideGateway");
		final CacheCaller getService = (CacheCaller) context
				.getBean("getGateway");
		final CacheCaller putService = (CacheCaller) context
				.getBean("putGateway");

		while (airportCodes.hasNext()) {

			final String input = airportCodes.next();

			try {
				Element element = new Element(input, null);
				System.out.println("Calling Gateway: "
						+ service.putData(element));
				System.out.println("Test Cache Get ******"
						+ ((Element) getService.getData(element))
								.getObjectValue());
			} catch (Exception e) {
				LOGGER.error("An exception was caught: " + e);
			}

		}
		checkUpdates();
	}

	public static void checkUpdates() {
		Timer timer = new Timer();
		timer.schedule(new Updater(), 1000, 120 * 1000);
	}
}
