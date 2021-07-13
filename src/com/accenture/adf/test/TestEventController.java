package com.accenture.adf.test;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertNull;

import static org.junit.Assert.fail;

import org.junit.After;

import org.junit.Before;

import org.junit.Test;

import org.springframework.mock.web.MockHttpServletRequest;

import org.springframework.mock.web.MockHttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.accenture.adf.businesstier.controller.EventController;

import com.accenture.adf.exceptions.FERSGenericException;

/**
 * 
 * Junit test class for EventController
 * 
 * 
 */

public class TestEventController {

	private MockHttpServletRequest request;

	private MockHttpServletResponse response;

	private ModelAndView modelAndView;
	private EventController controller;

	/**
	 * 
	 * Sets up initial objects required in other methods
	 * 
	 * 
	 * 
	 * @throws Exception
	 */

	@Before
	public void setUp() throws Exception {

		modelAndView = new ModelAndView();

		controller = new EventController();

		response = new MockHttpServletResponse();

		request = new MockHttpServletRequest("GET", "/catalog.htm");

	}

	/**
	 * 
	 * Deallocate the objects after execution of every method
	 * 
	 * 
	 * 
	 * @throws Exception
	 */

	@After
	public void tearDown() throws Exception {

		/**
		 * 
		 * @TODO: Release all the objects here by assigning them null
		 */

		modelAndView = null;

		controller = null;

		response = null;

		request = null;

	}

	/**
	 * 
	 * Test case to test the positive scenario for getAvailableEvents method
	 */

	@Test
	public void testGetAvailableEvents_Positive() {

		/**
		 * 
		 * @TODO: Call getAvailableEvents methods and assert it for appropriate
		 * 
		 *        model view name
		 */

		try {

			ModelAndView model = controller.getAvailableEvents(request,

			response);

			assertEquals("/eventCatalog.jsp", model.getViewName());

		} catch (Exception e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

			fail(e.getMessage());

		}

	}

	/**
	 * 
	 * Executes the negative scenario for getAvailableEvents method
	 * 
	 * @throws Exception
	 */

	@Test
	public void testGetAvailableEvents_Negative() throws Exception {

		/**
		 * 
		 * @TODO: Call getAvailableEvents methods by passing request as null and
		 * 
		 *        assert it for appropriate model view name
		 */

		try {

			ModelAndView model = controller.getAvailableEvents(null, response);

			assertNull("error", model.getViewName());

		} catch (FERSGenericException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

			fail(e.getMessage());

		}

	}

}
