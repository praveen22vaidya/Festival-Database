package com.accenture.adf.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.AssertThrows;

import com.accenture.adf.businesstier.entity.Event;
import com.accenture.adf.businesstier.entity.Visitor;
import com.accenture.adf.businesstier.service.VisitorServiceImpl;

/**
 * Junit test class for VisitorServiceImpl
 *
 */
public class TestVisitorServiceImpl {

	private List<Event> visitorList;	
	private Visitor visitor;
	private VisitorServiceImpl visitorServiceImpl;

	/**
	 * Set up the initial methods 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {		
		visitorServiceImpl = new VisitorServiceImpl();
		visitor = new Visitor();
	}

	/**
	 * Deallocates the objects after execution of every method
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		/**
					 
		 * @TODO: Release all the objects here by assigning them null  
		 */
		visitorServiceImpl=null;
		visitor=null;
	
	}

	/**
	 * Test case for method createVisitor
	 */
	@Test
	public void testCreateVisitor() {
		
		/**
		 * @TODO: Set the appropriate values for visitor object and
		 * call the method createVisitor by passing an argument of this visitor 
		 * object and then asserting the returned type of this method
		 */		
		visitor.setUserName("subbu1234");
		visitor.setFirstName("subramany1");
		visitor.setLastName("sri1");
		visitor.setEmail("sri@gmail1");
		visitor.setPhoneNumber("12771");
		visitor.setPassword("password");
		visitor.setAddress("mysor");
	try{
		boolean stat=visitorServiceImpl.createVisitor(visitor);
		System.out.println(stat);
		assertTrue("not inserted",stat);
	}
	 
	 catch (Exception exception) {
		fail("Exception is :" + exception.getMessage());
	}
		
		
		
	
	}

	/**
	 * Test case for method createVisitor
	 */
	@Test
	public void testSearchVisitor() {
		/**
		 * @TODO: Call searchVisitor method by passing the appropriate arguments 
		 * and then asserting the returned type visitor username with the argument passed
		 */	
		String name = "npatel";				
		visitor=visitorServiceImpl.searchVisitor(name, "password");
		assertNotNull(visitor);
		assertEquals(name, visitor.getUserName());
		
		
	}

	/**
	 * Test case for method RegisterVisitor
	 */
	@Test
	public void testRegisterVisitor() {
		/**
		 * @TODO: Call RegisterVisitor method by passing visitor object which 
		 * can be retrieved using searchVisitor method and then asserting the returned
		 * type of RegisterVisitor method 
		 */	
		visitor=visitorServiceImpl.searchVisitor("subbu1234", "password");
		assertNotNull(visitor);
		visitorServiceImpl.RegisterVisitor(visitor,1125);
		
		
		
	}

	/**
	 * Test case for method showRegisteredEvents
	 */
	@Test
	public void testShowRegisteredEvents() {
		/**
		 * @TODO: Call showRegisteredEvents method by passing visitor object which 
		 * can be retrieved using searchVisitor method and then asserting the returned
		 * type of showRegisteredEvents method 
		 */		
		visitor=visitorServiceImpl.searchVisitor("subbu1234", "password");
		assertNotNull(visitor);
		visitorList=visitorServiceImpl.showRegisteredEvents(visitor);
		assertNotNull("empty",visitorList);
		
	}

	/**
	 * Test case for method updateVisitorDetails
	 */
	@Test
	public void testUpdateVisitorDetails() {
		/**
		 * @TODO: Call updateVisitorDetails method by passing the visitor object which
		 * can be retrieved using searchVisitor method and then asserting the returned
		 * type of updateVisitorDetails
		 */		
		visitor=visitorServiceImpl.searchVisitor("subbu1234", "password");
		assertNotNull(visitor);
		int ret=visitorServiceImpl.updateVisitorDetails(visitor);
		assertTrue("not updated",ret>0);
		
	}

	/**
	 * Test case for method unregisterEvent
	 */
	@Test
	public void testUnregisterEvent() {
		/**
		 * @TODO: Call unregisterEvent method by passing the visitor object which can be
		 * retrieved using searchVisitor method and then asserting the returned type 
		 * of unregisterEvent
		 */	
		visitor=visitorServiceImpl.searchVisitor("subbu1234", "password");
		assertNotNull(visitor);
		
		visitorServiceImpl.unregisterEvent(visitor,1004);
		
		
	}

}
