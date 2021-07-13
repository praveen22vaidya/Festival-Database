package com.accenture.adf.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.servlet.ModelAndView;

import com.accenture.adf.businesstier.controller.VisitorController;
import com.accenture.adf.businesstier.dao.VisitorDAO;
import com.accenture.adf.businesstier.entity.Visitor;
import com.accenture.adf.exceptions.FERSGenericException;

/**
 * Junit test case to test the class VisitorController
 * 
 */
public class TestVisitorController {

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private MockHttpSession session;
	private ModelAndView modelAndView;
	private VisitorController controller;
	private VisitorDAO visitorDao;

	/**
	 * Set up initial methods required before execution of every method
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		modelAndView = new ModelAndView();
		controller = new VisitorController();
		session = new MockHttpSession();
		response = new MockHttpServletResponse();
		visitorDao = new VisitorDAO();
	}

	/**
	 * Deallocate objects after execution of every method
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		/**
		 * @TODO: Release all the objects here by assigning them null
		 */
		modelAndView = null;
		controller = null;
		session = null;
		response = null;
		visitorDao = null;

	}

	/**
	 * Positive test case to test the method newVisitor
	 */
	//@Test
	public void testNewVisitor_Positive() {
		try {
			request = new MockHttpServletRequest("GET", "/newVistor.htm");

			request.setParameter("USERNAME", "ylee");
			request.setParameter("PASSWORD", "password");
			request.setParameter("FIRSTNAME", "TestVFname");
			request.setParameter("LASTNAME", "lname");
			request.setParameter("EMAIL", "mail");
			request.setParameter("PHONENO", "11111");
			request.setParameter("ADDRESS", "testAddress");
			modelAndView = controller.newVisitor(request, response);
		} catch (Exception exception) {
			fail("Exception" + exception.getMessage());
		}
		assertEquals("/registration.jsp", modelAndView.getViewName());
	}

	/**
	 * Negative test case to test the method newVisitor
	 */
	//@Test
	public void testNewVisitor_Negative() {
		/**
		 * @TODO: Call newVisitor method by passing request object as null and
		 *        asserting the model view name
		 */
		try {
			request = new MockHttpServletRequest("GET", "/newVistor.htm");
			request = null;
			modelAndView = controller.newVisitor(request, response);
			assertNull(modelAndView);
		} catch (FERSGenericException e) {
			fail(e.getMessage());
		} catch (Exception exception) {
			fail("Exception");

		}

	}

	/**
	 * Positive test case to test the method searchVisitor
	 */
	//@Test
	public void testSearchVisitor_Positive() {
		/**
		 * @TODO: Create MockHttpServletRequest object Set request parameters
		 *        for USERNAME and PASSWORD for valid values Call searchVisitor
		 *        method and assert model view name
		 */
		try {
			request = new MockHttpServletRequest("GET", "/searchVisitor.htm");

			request.setParameter("USERNAME", "ylee");
			request.setParameter("PASSWORD", "password");
			modelAndView = controller.searchVisitor(request, response);
			assertEquals("/visitormain.jsp", modelAndView.getViewName());
		} catch (Exception exception) {
			fail("Exception" + exception.getMessage());
		}

	}

	/**
	 * Negative test case of invalid user for method searchVisitor
	 */
	//@Test
	public void testSearchVisitor_Negative_InvalidUser() {
		/**
		 * @TODO: Create MockHttpServletRequest object Set request parameters
		 *        for USERNAME and PASSWORD for invalid values Call
		 *        searchVisitor method and assert model view name
		 */
		try {
			request = new MockHttpServletRequest("GET", "/searchVisitor.htm");

			request.setParameter("USERNAME", "abdgfslh");
			request.setParameter("PASSWORD", "passwordfgsaggs");
			modelAndView = controller.searchVisitor(request, response);
			assertEquals("/index.jsp", modelAndView.getViewName());
		} catch (Exception exception) {
			fail("Exception" + exception.getMessage());
		}

	}

	/**
	 * Negative test case for method searchVisitor
	 */
	//@Test
	public void testSearchVisitor_Negative() {
		/**
		 * @TODO: Call searchVisitor method by passing request object as null
		 *        and asserting the model view name
		 */
		try {
			modelAndView = controller.searchVisitor(null, response);
			assertNull(modelAndView);

		} catch (FERSGenericException e) {
			fail(e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail("Exception");
		}
	}

	/**
	 * Positive test case for method registerVisitor
	 */
	//@Test
	public void testRegisterVisitor_Positive() {
		/**
		 * @TODO: Create MockHttpServletRequest object Set visitor object in
		 *        VISITOR session by calling searchUser method from visitorDAO
		 *        Set request parameters for USERNAME and PASSWORD for valid
		 *        values Call registerVisitor method and assert model view name
		 */
		try {
			request = new MockHttpServletRequest("GET", "/eventreg.htm");
			Visitor visitor;

			visitor = visitorDao.searchUser("ylee", "password");
			session.setAttribute("VISITOR", visitor);
			request.setParameter("eventId", "1002");

			request.setSession(session);

			modelAndView = controller.registerVisitor(request, response);
			assertEquals("/visitormain.jsp", modelAndView.getViewName());
		} catch (Exception exception) {
			fail("Exception" + exception.getMessage());
		}

	}

	/**
	 * Negaative test case for method registerVisitor
	 */
	//@Test
	public void testRegisterVisitor_Negative() {
		/**
		 * @TODO: Call registerVisitor method by passing request object as null
		 *        and asserting the model view name
		 */
		try {
			request = new MockHttpServletRequest("GET", "/eventreg.htm");

			request = null;

			modelAndView = controller.registerVisitor(request, response);

		} catch (FERSGenericException e) {
			fail("request null" + e.getMessage());
		}

		catch (Exception exception) {
			fail("Exception" + exception.getMessage());
		}
	}

	/**
	 * Positive test case for method updateVisitor
	 */
	//@Test
	public void testUpdateVisitor_Positive() {
		/**
		 * @TODO: Create MockHttpServletRequest object Set visitor object in
		 *        VISITOR session by calling searchUser method from visitorDAO
		 *        Set request parameters for all valid user values Call
		 *        updateVisitor method and assert model view name
		 */
		request = new MockHttpServletRequest("GET", "/updatevisitor.htm");
		Visitor visitor;
		try {
			visitor = visitorDao.searchUser("ylee2", "password1");
			session.setAttribute("VISITOR", visitor);
			request.setParameter("username", "ylee1");
			request.setParameter("password", "password1");
			request.setParameter("firstname", "TestVFname1");
			request.setParameter("lastname", "lname");
			request.setParameter("email", "mail");
			request.setParameter("phoneno", "11111");
			request.setParameter("address", "testAddress");
			request.setSession(session);
			try {

				modelAndView = controller.updateVisitor(request, response);
				//assertEquals("{status=success}", modelAndView.getModel());
			} catch (FERSGenericException e) {
				fail("error" + e.getMessage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				fail("exception" + e.getMessage());
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			fail("CNFEexception" + e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			fail("SQLexception" + e.getMessage());
		}

	}

	/**
	 * Negative test case for method updateVisitor
	 */
	//@Test
	public void testUpdateVisitor_Negative() {
		/**
		 * @TODO: Call updateVisitor method by passing request object as null
		 *        and asserting the model view name
		 */
		request = new MockHttpServletRequest("GET", "/updatevisitor.htm");

		request = null;
		try {
			modelAndView = controller.updateVisitor(request, response);
		} catch (FERSGenericException e) {
			fail("FERSGenericerror" + e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail("exception" + e.getMessage());
		}
	}

	/**
	 * Positive test case for method unregisterEvent
	 */
	// @Test
	public void testUnregisterEvent_Positive() {
		/**
		 * @TODO: Create MockHttpServletRequest object Set visitor object in
		 *        VISITOR session by calling searchUser method from visitorDAO
		 *        Set request parameters for all USERNAME, PASSWORD and eventId
		 *        values Call unregisterEvent method and assert model view name
		 */
		request = new MockHttpServletRequest("GET", "/eventunreg.htm");
		Visitor visitor;
		try {
			visitor = visitorDao.searchUser("npatel", "password");
			session.setAttribute("VISITOR", visitor);
			request.setParameter("eventId", "1004");
			request.setSession(session);

			modelAndView = controller.unregisterEvent(request, response);
			assertEquals("/visitormain.jsp", modelAndView.getViewName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			fail("CNFEexception" + e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			fail("SQLexception" + e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail("Eexception" + e.getMessage());
		}

	}

	/**
	 * Negative test case for method unregisterEvent
	 */
	//@Test
	public void testUnregisterEvent_Negative() {
		/**
		 * @TODO: Call unregisterEvent method by passing request object as null
		 *        and asserting the model view name
		 */
		try {
			request = null;
			modelAndView = controller.unregisterEvent(request, response);
			assertEquals("/visitormain.jsp", modelAndView.getViewName());
		} catch (FERSGenericException e) {
			fail("FERSGenericException" + e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail("Exception");
		}

	}

	//@Test
	public void testChangePassword_Positive() {

		try {

			request = new MockHttpServletRequest("GET", "/changePWD.htm");

			Visitor visitor = visitorDao.searchUser("ylee", "password");
			//System.out.println("9999999"+visitor.getPassword());
			session.setAttribute("VISITOR", visitor);
			request.setParameter("password","password");
			request.setSession(session);
			// TODO: Set visitor object to session

			// TODO: Set session object to mock request object

			//request.setParameter("password", "password3");

			modelAndView = controller.changePassword(request, response);
			
			//assertEquals("success", modelAndView.getModelMap().get("status"));

		} catch (Exception exception) {

			fail("Exception");

		}

		
		// To make sure password is reset to the original value after testing

		request.setParameter("password", "password");

		modelAndView = controller.changePassword(request, response);

	}

	//@Test
	public void testChangePassword_PasswordNull() {
		try {
			// request = new MockHttpServletRequest("GET", "/changePWD.htm");
			// TODO: Search visitor with user as ‘ylee’ and password as
			// ‘password’
			request = new MockHttpServletRequest("GET", "/changePWD.htm");

			Visitor visitor = visitorDao.searchUser("ylee", "");
		
			session.setAttribute("VISITOR", visitor);
			request.setSession(session);
			
			
			// TODO: Invoke changePassword() method on controller with mock
			// request and response objects are arguments
			
		modelAndView=controller.changePassword(request, response);
		
		System.out.println(modelAndView);
		
		//assertEquals("error", modelAndView.getModelMap().get("status"));
		
		} catch (Exception exception) {

			fail(exception.getMessage());

		}

		

	}
	

	// TODO: Add a test method to verify change password functionality with
	// visitor as null
	
	@Test
		public void testChangePassword_VisitorNull() {
			try {
				
				request = new MockHttpServletRequest("GET", "/changePWD.htm");

				Visitor visitor = null;
			
				session.setAttribute("VISITOR", visitor);
				request.setSession(session);
				
				
				// TODO: Invoke changePassword() method on controller with mock
				// request and response objects are arguments
				
			modelAndView=controller.changePassword(request, response);
			
			System.out.println(modelAndView);
			
			//assertEquals("error", modelAndView.getModelMap().get("status"));
			
			} catch (Exception exception) {

				fail(exception.getMessage());

			}

			

		}
		
	
	
}
