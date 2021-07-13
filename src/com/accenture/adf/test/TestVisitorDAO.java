package com.accenture.adf.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.accenture.adf.businesstier.dao.VisitorDAO;
import com.accenture.adf.businesstier.entity.Event;
import com.accenture.adf.businesstier.entity.Visitor;

/**
 * JUnit test case for VisitorDAO class for testing all repository methods to
 * call database sub-routines
 * 
 */
public class TestVisitorDAO {

	private Visitor visitor;
	private VisitorDAO visitorDAO;
	private ArrayList<Event> registeredEvents;

	/**
	 * Setting up initial objects
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		visitor = new Visitor();
		visitorDAO = new VisitorDAO();
		registeredEvents = new ArrayList<Event>();
	}

	/**
	 * Deallocating objects after execution of every method
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		/**
		 * @TODO: Release all the objects here by assigning them null
		 */
		visitor = null;
		visitorDAO = null;
		registeredEvents = null;

	}

	/**
	 * Test case for method insertData
	 */
	@Test
	public void testInsertData() {
		/**
		 * @TODO: Create visitor object by setting appropriate values Call
		 *        insertData method by passing this visitor object Search this
		 *        new visitor object by calling searchUser method Assert the
		 *        values of username
		 */

		
		
		  visitor.setUserName("Rdarshan1"); 
		  visitor.setPassword("password1");
		  visitor.setFirstName("darshan"); 
		  visitor.setLastName("r");
		  visitor.setEmail("darshan@gmail.com");
		  visitor.setPhoneNumber("9448461934");
		  visitor.setAddress("bangalore"); 
		  try { boolean ret =visitorDAO.insertData(visitor);
		  assertTrue("User name already used!",  ret);
		  } catch (ClassNotFoundException e) { 
			  // TODO Auto-generated
		  fail("CNFE\t" + e.getMessage());
		  
		  } catch (SQLException e) {
			  			  // TODO Auto-generated catch block 
			  fail("SQL Error\t" + e.getMessage()); }
		  catch (Exception e) {
			  // TODO		  Auto-generated catch block
			  fail("Runtime-Error\t" + e.getMessage());
		  }
		 

	}

	/**
	 * Test case for method searchUser
	 */
	@Test
	public void testSearchUser() {
		/**
		 * @TODO: Call searchUser method for valid values of username and
		 *        password and assert the value of username for the returned
		 *        type of method
		 */
		try {
			visitor = visitorDAO.searchUser("npatel", "password");

		assertNotNull(visitor.getUserName());
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			fail("CNFE\t" + e.getMessage());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			fail("SQL Error\t" + e.getMessage());
		}
	}

	/**
	 * Test case for method registerVisitorToEvent
	 */
	@Test
	public void testRegisterVisitorToEvent() {
		/**
		 * @TODO: Fetch visitor object by calling searchUser for valid values of
		 *        username and password Pass this visitor object and valid
		 *        eventid to registerVisitorToEvent method and assert the value
		 */
		try {
			visitor = visitorDAO.searchUser("ravi singh", "abcde");
			assertNotNull("visitor not present",visitor);

			try {
				visitorDAO.registerVisitorToEvent(visitor, 1002);
				registeredEvents = visitorDAO.registeredEvents(visitor);
				assertNotNull(registeredEvents);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				fail("error" + e.getMessage());
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			fail("CNFE\t" + e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			fail("SQL Error\t" + e.getMessage());
		}
	}

	/**
	 * Test case for method registeredEvents
	 */
	@Test
	public void testRegisteredEvents() {
		/**
		 * @TODO: Fetch visitor object by calling searchUser for valid values of
		 *        username and password Pass this visitor object and valid
		 *        eventid to registeredEvents method and assert the value
		 */
		try {
			visitor=visitorDAO.searchUser("bsmith2", "password");
			if(visitor==null)
				assertFalse("user not present",true);
			//assertNotNull(visitor);
			registeredEvents = visitorDAO.registeredEvents(visitor);
			assertNotNull("not registered",registeredEvents);
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			fail("CNFE\t" + e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			fail("SQL Error\t" + e.getMessage());
		}
	}

	/**
	 * Test case for method updateVisitor
	 */
	@Test
	public void testUpdateVisitor() {
		/**
		 * @TODO: Fetch visitor object by calling searchUser for valid values of
		 *        username and password Update the value in this visitor object
		 *        Pass this visitor object to updateVisitor method and assert
		 *        the value of changed value
		 */
		try {
			visitor=visitorDAO.searchUser("bsmith3", "password");
			visitor.setUserName("bsmith2");
			int stat=visitorDAO.updateVisitor(visitor);
			if(stat==0){
				assertFalse(true);
			}
		
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			fail("CNFE\t" + e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			fail("SQL Error\t" + e.getMessage());
		}
		
	}

	/**
	 * Test case for method registeredEvents
	 */
	@Test
	public void testUnregisterEvent() {
		/**
		 * @TODO: Fetch visitor object by calling searchUser for valid values of
		 *        username and password Pass this visitor object and valid
		 *        eventid to unregisterEvent method and assert the value
		 */
		try {System.out.println("***");
			visitor=visitorDAO.searchUser("ravi singh", "abcde");
			System.out.println("***");
			assertNotNull(visitor);
			
			try {
				int size=visitorDAO.registeredEvents(visitor).size();
				//int size=registeredEvents.size();
				System.out.println("hi "+size);
				visitorDAO.unregisterEvent(visitor, 1002);
				int size1=visitorDAO.registeredEvents(visitor).size();
				System.out.println("after "+size1);
				if(size!=size1){
					assertTrue(true);
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				fail("exception" + e.getMessage());
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			fail("CNFE\t" + e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			fail("SQL Error\t" + e.getMessage());
		}
	}

}
