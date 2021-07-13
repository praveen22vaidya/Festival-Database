package com.accenture.adf.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.management.Query;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.accenture.adf.businesstier.dao.EventDAO;
import com.accenture.adf.businesstier.entity.Event;
import com.accenture.adf.businesstier.entity.Visitor;
import com.accenture.adf.exceptions.FERSGenericException;
import com.accenture.adf.helper.FERSDataConnection;
import com.mysql.jdbc.Statement;

/**
 * Junit test class for EventDAO class
 *
 */
public class TestEventDAO {

	private static Connection connection = null;
	private static PreparedStatement statement = null;
	private static java.sql.Statement st=null;
	private static ResultSet resultSet = null;
	private ArrayList<Event> showAllEvents;
	private EventDAO dao;	

	/**
	 * Sets up database connection before other methods are executed in this class
	 * @throws Exception
	 */
	
	@BeforeClass
	public static void setUpDatabaseConnection() throws Exception {
		connection = FERSDataConnection.createConnection();
	}

	/**
	 * Closes the database connection after all the methods are executed
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownDatabaseConnection() throws Exception {
		/**
		 * @TODO: Close connection object here  
		 */
		connection.close();
	}

	/**
	 * Sets up the objects required in other methods
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		showAllEvents = new ArrayList<Event>();
		dao = new EventDAO();
	}

	/**
	 * Deallocate the resources after execution of method
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		/**
		 * @TODO: Release all the objects here by assigning them null  
		 */
		showAllEvents=null;
		dao=null;
	}

	/**
	 * Positive test case to test the method showAllEvents
	 */
	@Test
	public void testShowAllEvents_Positive() {
		/**
		 * @TODO: Call showAllEvents method and assert it for
		 * size of returned type list
		 */		
		try {
			showAllEvents=dao.showAllEvents();
			int size=showAllEvents.size();
			assertTrue(size>0);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			fail("Class Not Found Exception\t."+e.getMessage());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
		}
	}	

	/**
	 * Junit test case to test positive case for updateEventDeletions
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test	
	public void testUpdateEventDeletions_Positive() throws ClassNotFoundException, SQLException {
		/**
		 * @TODO: Find out seats available for an event by opening a connection
		 * and calling the query SELECT SEATSAVAILABLE FROM EVENT WHERE EVENTID = ?
		 * Call the updateEventDeletions for eventId
		 * Again find out the seats available for this event
		 * testSeatsAvailableBefore should be 1 more then testSeatsAvailableAfter
		 */		
		
		try{
			//String sqry="SELECT SEATSAVAILABLE FROM EVENT WHERE EVENTID = 1001";
			
			st = connection.createStatement();
			
			resultSet = st.executeQuery("SELECT SEATSAVAILABLE FROM EVENT WHERE EVENTID = 1001");
			
			int testSeatsAvailableBefore=0;
			while(resultSet.next()){
			testSeatsAvailableBefore=resultSet.getInt("seatsavailable");
			}
			System.out.println("before"+testSeatsAvailableBefore);
			
			dao.updateEventDeletions(1001);
			
			st = connection.createStatement();
			
			resultSet = st.executeQuery("SELECT SEATSAVAILABLE FROM EVENT WHERE EVENTID = 1001");
			
			int testSeatsAvailableAfter=0;
			while(resultSet.next()){
			testSeatsAvailableAfter=resultSet.getInt("seatsavailable");
			}
			System.out.println("after"+testSeatsAvailableAfter);
			
			assertFalse(testSeatsAvailableBefore>testSeatsAvailableAfter);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			fail(e.getMessage());
		}
				
		
	}

	/**
	 * Negative test case for method updateEventDeletions
	 */
	@Test
	public void testUpdateEventDeletions_Negative() {
		/**
		 * @TODO: Call updateEventDeletions for incorrect eventid and it should
		 * throw an exception
		 */	
		try{
			//String sqry="SELECT SEATSAVAILABLE FROM EVENT WHERE EVENTID = 1001";
			
			st = connection.createStatement();
			
			resultSet = st.executeQuery("SELECT SEATSAVAILABLE FROM EVENT WHERE EVENTID = 100");
			
			int testSeatsAvailableBefore=0;
			while(resultSet.next()){
			testSeatsAvailableBefore=resultSet.getInt("seatsavailable");
			}
			System.out.println("before"+testSeatsAvailableBefore);
			
			dao.updateEventDeletions(100);
			
			st = connection.createStatement();
			
			resultSet = st.executeQuery("SELECT SEATSAVAILABLE FROM EVENT WHERE EVENTID = 100");
			
			int testSeatsAvailableAfter=0;
			while(resultSet.next()){
			testSeatsAvailableAfter=resultSet.getInt("seatsavailable");
			}
			System.out.println("after"+testSeatsAvailableAfter);
			
			assertTrue(testSeatsAvailableBefore>testSeatsAvailableAfter);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			fail(e.getMessage());
		}
				
	}

	/**
	 * Positive test case for method updateEventNominations
	 */
	@Test
	public void testUpdateEventNominations_Positive() {
		/**
		 * @TODO: Find out seats available for an event by opening a connection
		 * and calling the query SELECT SEATSAVAILABLE FROM EVENT WHERE EVENTID = ?
		 * Call the updateEventNominations for eventId
		 * Again find out the seats available for this event
		 * testSeatsAvailableBefore should be 1 less then testSeatsAvailableAfter
		 */	
		try{
			//String sqry="SELECT SEATSAVAILABLE FROM EVENT WHERE EVENTID = 1001";
			
			st = connection.createStatement();
			
			resultSet = st.executeQuery("SELECT SEATSAVAILABLE FROM EVENT WHERE EVENTID = 1001");
			
			int testSeatsAvailableBefore=0;
			while(resultSet.next()){
			testSeatsAvailableBefore=resultSet.getInt("seatsavailable");
			}
			System.out.println("update before"+testSeatsAvailableBefore);
			
			dao.updateEventNominations(1001);
			
			st = connection.createStatement();
			
			resultSet = st.executeQuery("SELECT SEATSAVAILABLE FROM EVENT WHERE EVENTID = 1001");
			
			int testSeatsAvailableAfter=0;
			while(resultSet.next()){
			testSeatsAvailableAfter=resultSet.getInt("seatsavailable");
			}
			System.out.println("update after"+testSeatsAvailableAfter);
			
			assertFalse(testSeatsAvailableBefore<testSeatsAvailableAfter);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			fail(e.getMessage());
		}
				
	}

	/**
	 * Negative test case for method updateEventNominations
	 */
	@Test
	   public void testUpdateEventNominations_Negative() {
		/**
		 * @TODO: Call updateEventNominations for incorrect eventid and it should
		 * throw an exception
		 */	
		try{
			//String sqry="SELECT SEATSAVAILABLE FROM EVENT WHERE EVENTID = 1001";
			
			st = connection.createStatement();
			
			resultSet = st.executeQuery("SELECT SEATSAVAILABLE FROM EVENT WHERE EVENTID = 1001");
			
			int testSeatsAvailableBefore=0;
			while(resultSet.next()){
			testSeatsAvailableBefore=resultSet.getInt("seatsavailable");
			}
			System.out.println("before"+testSeatsAvailableBefore);
			
			dao.updateEventNominations(1001);
			
			st = connection.createStatement();
			
			resultSet = st.executeQuery("SELECT SEATSAVAILABLE FROM EVENT WHERE EVENTID = 1001");
			
			int testSeatsAvailableAfter=0;
			while(resultSet.next()){
			testSeatsAvailableAfter=resultSet.getInt("seatsavailable");
			}
			System.out.println("after"+testSeatsAvailableAfter);
			
			assertTrue(testSeatsAvailableBefore<testSeatsAvailableAfter);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			fail(e.getMessage());
		}
				
		
	}

	/**
	 * Positive test case for method checkEventsofVisitor
	 */
	@Test
	public void testCheckEventsOfVisitor_Positive() {
		/**
		 * @TODO: Create visitor object by setting appropriate values
		 * Call checkEventsofVisitor method by passing this visitor object and
		 * valid eventId
		 * Assert the value of return type 
		 */	
		
		Visitor visitor=new Visitor();
		visitor.setVisitorId(1002);
		try {
			boolean value=dao.checkEventsofVisitor(visitor, 1001);
			
			assertTrue(value);
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			
			fail(e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			fail(e.getMessage());
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			
			fail(e.getMessage());
		}
	}

}
