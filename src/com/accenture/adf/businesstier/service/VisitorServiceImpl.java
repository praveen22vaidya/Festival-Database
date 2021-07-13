package com.accenture.adf.businesstier.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.accenture.adf.businesstier.dao.EventDAO;
import com.accenture.adf.businesstier.dao.VisitorDAO;
import com.accenture.adf.businesstier.entity.Event;
import com.accenture.adf.businesstier.entity.Visitor;
import com.accenture.adf.exceptions.FERSGenericException;

/**
 * <br/>
 * CLASS DESCRIPTION: <br/>
 * 
 * A service class that implements the VisitorFacade. Makes visitor-related data
 * requests to the VisitorDAO class <br/>
 * 
 */

public class VisitorServiceImpl implements VisitorFacade {

	//LOGGER for logging all exceptions of VISITOR DAO
	private static Logger log = Logger.getLogger(VisitorServiceImpl.class);

	/**
	 * <br/>
	 *  METHOD DESCRIPTION:<br/>
	 *	SERVICE CLASS for inserting new visitor<br/>
	 *  <br/>
	 *  
	 *  PSEUDOCODE: <br/>
	 *  Call insertData () in a new DAO object and store the returned Boolean value<br/>
	 *  Catch all possible exceptions and log to the error file the provided exception message.<br/>
	 *  If no exceptions occur, return the Boolean value<br/>
	 *  <br/>
	 *   
	 * 	@param visitor (type Visitor)
	 * 
	 * 	@return boolean
	 * 
	 * 	@throws ClassNotFoundException
	 * 	@throws SQLException
	 *	@throws Exception
	 *	  
	 */
	
	public boolean createVisitor(Visitor visitor) {

		boolean insertStatus = false;
		
		
		// TODO:  Add code here.....
		// TODO:  Pseudo-code are in the block comments above this method
		// TODO:  For more comprehensive pseudo-code with details, refer to the Component/Class Detailed Design Document
	
		VisitorDAO visitorDAO = new VisitorDAO();
		try {
			insertStatus= visitorDAO.insertData(visitor);
			System.out.println(insertStatus);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			
			log.info("CNFE :" + e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.info("SQL Expec :" + e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info("Exc :" + e.getMessage());
		}
		
		return insertStatus;
	}

	/**
	 * <br/>
	 *  METHOD DESCRIPTION:<br/>
	 *	SERVICE CLASS for searching visitor details<br/>
	 *  <br/>
	 *  
	 *  PSEUDOCODE: <br/>
	 *  Call searchUser () in a new DAO object and reference the returned Visitor object<br/>
	 *  Catch all possible exceptions and log to the error file the provided exception message.<br/>
	 *  If no exceptions occur, return the Visitor Object reference<br/>
	 *  <br/>
	 *   
	 * 	@param username (type String)
	 * 	@param password (type String)
	 * 
	 * 	@return Visitor
	 * 
	 * 	@throws ClassNotFoundException
	 * 	@throws SQLException
	 *	@throws Exception
	 *	  
	 */
	
	public Visitor searchVisitor(String username, String password) {

		// TODO:  Add code here.....
		// TODO:  Pseudo-code are in the block comments above this method
		// TODO:  For more comprehensive pseudo-code with details, refer to the Component/Class Detailed Design Document
		
		VisitorDAO visitorDAO = new VisitorDAO();
		Visitor visitor=new Visitor();
		try {
			
			visitor = visitorDAO.searchUser(username,password);
		} catch (ClassNotFoundException exception) {
			log.info("CNFEException is :" + exception.getMessage());
		} catch (SQLException exception) {
			log.info("SQLException is :" + exception.getMessage());
		}catch(Exception exception){
			log.info("Eexception"+exception.getMessage());
		}
	
		
		return visitor; // Replace null with "visitor" object, as required after completing the code in the TODOs above 
	}

	/**
	 * <br/>
	 *  METHOD DESCRIPTION:<br/>
	 *	SERVICE CLASS for registering user to event<br/>
	 *  <br/>
	 *  
	 *  PSEUDOCODE: <br/>
	 * 	Call registerVisitorToEvent () in a new DAO object.<br/>
	 * 	Call updateEventNominations() in a new DAO object.<br/>
	 * 	Catch all possible exceptions and log to the error file the provided exception message.<br/> 
	 *  <br/>
	 *   
	 * 	@param visitor (type Visitor)
	 * 	@param eventid (type int)
	 * 
	 *	@throws ClassNotFoundException
	 * 	@throws SQLException
	 *	@throws Exception
	 *	  
	 */
	
	public void RegisterVisitor(Visitor visitor, int eventid) {
		VisitorDAO visitorDAO = new VisitorDAO();
		EventDAO eventDAO=new EventDAO();
		try {
			visitorDAO.registerVisitorToEvent(visitor, eventid);
			eventDAO.updateEventNominations(eventid);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			log.info("CNFEException is :" + e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.info("SQLException is :" + e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info("EException is :" +e.getMessage());
		}
		
		

		// TODO:  Add code here.....
		// TODO:  Pseudo-code are in the block comments above this method
		// TODO:  For more comprehensive pseudo-code with details, refer to the Component/Class Detailed Design Document
					
	}

	/**
	 * <br/>
	 *  METHOD DESCRIPTION:<br/>
	 *	SERVICE CLASS for displaying all registered events<br/>
	 *  <br/>
	 *  
	 *  PSEUDOCODE: <br/>
	 * 	Call registeredEvents () in a new DAO object and reference the return ArrayList of Event objects<br/>
	 * 	Catch all possible exceptions and log to the error file the provided exception message.<br/>
	 * 	If no exceptions occur, return the reference to the list of event objects<br/>
	 *  <br/>
	 *   
	 * 	@param visitor (type Visitor)
	 * 
	 *	@throws ClassNotFoundException
	 * 	@throws SQLException
	 *	@throws Exception
	 *
	 *	@return List of Event Object Array 
	 *	  
	 */
	public ArrayList<Event> showRegisteredEvents(Visitor visitor) {
		VisitorDAO visitorDAO = new VisitorDAO();
		ArrayList<Event> list=new ArrayList<Event>();
		
		try {
			list=visitorDAO.registeredEvents(visitor);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			log.info("CNFEException is :" + e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.info("SQLException is :" + e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info("EException is :" + e.getMessage());
		}
		
		
		
		// TODO:  Add code here.....
		// TODO:  Pseudo-code are in the block comments above this method
		// TODO:  For more comprehensive pseudo-code with details, refer to the Component/Class Detailed Design Document
				
		return list; // TODO Replace "null" with "ArrayList<Event>" collection, based on updates to code in the TODO section.
	}

	/**
	 * SERVICE CLASS for updating visitor details
	 */
	public int updateVisitorDetails(Visitor visitor) {

		VisitorDAO visitorDAO = new VisitorDAO();
		int status = 0;
		try {
			status = visitorDAO.updateVisitor(visitor);
		} catch (ClassNotFoundException exception) {
			log.info("Exception is :" + exception.getMessage());
		} catch (SQLException exception) {
			log.info("Exception is :" + exception.getMessage());
		}
		return status;
	}

	/**
	 * SERVICE CLASS for removing event registered by visitor
	 */
	public void unregisterEvent(Visitor visitor, int eventid) {

		VisitorDAO visitorDAO = new VisitorDAO();
		try {
			visitorDAO.unregisterEvent(visitor, eventid);
		} catch (ClassNotFoundException exception) {
			log.info("Exception is :" + exception.getMessage());
		} catch (SQLException exception) {
			log.info("Exception is :" + exception.getMessage());
		} catch (Exception exception) {
			log.info("Exception is :" + exception.getMessage());
		}
	}
	public int changePassword(Visitor visitor) throws FERSGenericException {

		//TODO: Create a new instance of VisitorDAO object
		//TODO: Within a return statement invoke changePassword() method on VisitorDAO with the new instance of the Visitor object created in previous TODO as password

		//TODO: Surround step 3 in a try..catch block for ClassNotFoundException and SQLException
		//TODO: In catch block log an error to log file with the error message from the exception object and rethrow the exception as FERSGenericException attaching original exception object
		VisitorDAO visitorDAO = new VisitorDAO();
		int i=0;
		try{
			
			i=visitorDAO.changePassword(visitor);
			
		return i;
		}
		 catch (ClassNotFoundException exception) {
				log.info("Exception is :" + exception.getMessage());
			} catch (SQLException exception) {
				log.info("Exception is :" + exception.getMessage());
			} catch (Exception exception) {
				log.info("Exception is :" + exception.getMessage());
			}
	return i;

}
}


