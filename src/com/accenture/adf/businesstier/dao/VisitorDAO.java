package com.accenture.adf.businesstier.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext; // Spring related APIS
import org.springframework.context.support.ClassPathXmlApplicationContext; // Spring related APIS
import com.accenture.adf.businesstier.dao.EventDAO;
import com.accenture.adf.businesstier.entity.Event; // CC
import com.accenture.adf.businesstier.entity.Visitor;
import com.accenture.adf.exceptions.FERSGenericException;
import com.accenture.adf.helper.FERSDataConnection; // to get DB conne
import com.accenture.adf.helper.FERSDbQuery; // To Inililize all the querries
import com.mysql.jdbc.util.ResultSetUtil;

/**
 * 
 * <br/>
 * CLASS DESCRIPTION:<br/>
 * A Data Access Object (DAO) class for handling and managing visitor related
 * data requested, used, and processed in the application and maintained in the
 * database. The interface between the application and visitor data persisting
 * in the database.
 * 
 */

public class VisitorDAO {

	// LOGGER for handling all transaction messages in VISITORDAO
	private static Logger log = Logger.getLogger(VisitorDAO.class);

	// JDBC API classes for data persistence
	private Connection connection = null;
	private PreparedStatement statement = null;
	private ResultSet resultSet = null;
	private FERSDbQuery query;
	EventDAO dao = new EventDAO();

	// Default constructor for injecting Spring dependencies for SQL queries
	public VisitorDAO() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		query = (FERSDbQuery) context.getBean("SqlBean");
		// FERSDbQuery d=new FERSDbQuery(); // Dont create... // DI or IOC
	}

	/**
	 * <br/>
	 * METHOD DESCRIPTION:<br/>
	 * DAO method to loading visitor details into VISITOR table in database<br/>
	 * and validating about existing visitor details before inserting a visitor <br/>
	 * 
	 * <br/>
	 * PSEUDOCODE: <br/>
	 * Create a connection to database<br/>
	 * Prepare a statement object using the connection that uses a query that
	 * inserts visitor information <br/>
	 * into the visitor table <br/>
	 * Execute a statement object selects all the usernames from the visitor
	 * table<br/>
	 * if the username is not in the visitor table <br/>
	 * 
	 * @param visitor
	 *            (type Visitor)
	 * 
	 * @return boolean
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws Exception
	 * 
	 */

	public boolean insertData(Visitor visitor) throws ClassNotFoundException,
			SQLException, Exception {

		boolean flag = false;
		// TODO: Add code here.....
		// TODO: Pseudo-code are in the block comments above this method
		// TODO: For more comprehensive pseudo-code with details, refer to the
		// Component/Class Detailed Design Document
		connection = FERSDataConnection.createConnection();

		statement = connection.prepareStatement(query.getsearchbyuname());
		statement.setString(1, visitor.getUserName());
		System.out.println("before resultset");

		ResultSet st = statement.executeQuery();
		System.out.println("after resultset");
		if (st.next() == false) {

			connection = FERSDataConnection.createConnection();
			statement = connection.prepareStatement(query.getInsertQuery());
			System.out.println("******************");
			statement.setString(1, visitor.getUserName());
			statement.setString(2, visitor.getPassword());
			statement.setString(3, visitor.getFirstName());
			statement.setString(4, visitor.getLastName());
			statement.setString(5, visitor.getEmail());
			statement.setString(6, visitor.getPhoneNumber());
			statement.setString(7, visitor.getAddress());
			statement.executeUpdate();
			System.out.println("******************");
			flag = true;
		}
		return flag;
	}

	/**
	 * <br/>
	 * METHOD DESCRIPTION:<br/>
	 * DAO method for searching for visitor details using USERNAME and PASSWORD <br/>
	 * 
	 * <br/>
	 * PSEUDOCODE: <br/>
	 * Create a connection to database<br/>
	 * Prepare a statement object using the connection<br/>
	 * that uses a query that retrieves all the data from the visitor table
	 * based on the username and password provided. Execute the query and <br/>
	 * Using a WHILE LOOP, store the results in the result set record in the
	 * visitor object.<br/>
	 * 
	 * @param username
	 *            (type String)
	 * @param password
	 *            (type String)
	 * 
	 * @return Visitor
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * 
	 * 
	 */
	public Visitor searchUser(String username, String password)
			throws ClassNotFoundException, SQLException {

		Visitor visitor = null;
		connection = FERSDataConnection.createConnection();
		statement = connection.prepareStatement(query.getSearchQuery());
		// StringBuffer sb = new StringBuffer(query.getSearchQuery());
		// String sb1 = sb.substring(0,sb.indexOf("?"));
		// sb1=sb1.concat(username);
		// System.out.println(sb1);
		statement.setString(1, username);
		statement.setString(2, password);
		System.out.println(username + password);
		resultSet = statement.executeQuery(); // Once

		while (resultSet.next()) {
			visitor = new Visitor();
			System.out.println("%%%%%%%%%%%%%%%%%%%%%");
			visitor.setVisitorId(resultSet.getInt("visitorId"));
			visitor.setUserName(resultSet.getString("username"));
			visitor.setPassword(resultSet.getString("password"));
			visitor.setFirstName(resultSet.getString("firstname"));
			visitor.setLastName(resultSet.getString("lastname"));
			visitor.setEmail(resultSet.getString("email"));
			visitor.setPhoneNumber(resultSet.getString("phoneNumber"));
			visitor.setAddress(resultSet.getString("address"));
			return visitor;
		}

		// TODO: Add code here.....
		// TODO: Pseudo-code are in the block comments above this method
		// TODO: For more comprehensive pseudo-code with details, refer to the
		// Component/Class Detailed Design Document

		// System.out.println(visitor);
		return visitor;
	}

	/**
	 * <br/>
	 * METHOD DESCRIPTION: <br/>
	 * DAO method to register visitor to specific event and checking about
	 * status of visitor to particular event. <br/>
	 * 
	 * PSEUDO-CODE: <br/>
	 * Create a connection to the database <br/>
	 * Prepare a statement object using the connection: that inserts the visitor
	 * and event IDs into the EVENTSESSIONSIGNUP table <br/>
	 * Execute the query to perform the update <br/>
	 * 
	 * 
	 * @param visitor
	 * @param eventid
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws Exception
	 * 
	 */

	public void registerVisitorToEvent(Visitor visitor, int eventid)
			throws ClassNotFoundException, SQLException, Exception {

		// TODO: Add code here.....
		// TODO: Pseudo-code are in the block comments above this method
		// TODO: For more comprehensive pseudo-code with details, refer to the
		// Component/Class Detailed Design Document

		connection = FERSDataConnection.createConnection();

		statement = connection.prepareStatement(query.getCheckDuplicate());
		statement.setInt(1, eventid);
		statement.setInt(2, visitor.getVisitorId());
		ResultSet st = statement.executeQuery();

		if (st.next() == false) {
			statement = connection.prepareStatement(query.getRegisterQuery());
			statement.setInt(1, eventid);
			statement.setInt(2, visitor.getVisitorId());

			statement.executeUpdate();
			// dao.updateEventDeletions(eventid);

		} else {
			System.out
					.println("visitor has alraedy registered for the same event");
		}
	}

	/**
	 * <br/>
	 * METHOD DESCRIPTION:<br/>
	 * DAO method to display all the events registered by particular visitor <br/>
	 * 
	 * PSEUDO-CODE: <br/>
	 * Create a connection to the database <br/>
	 * Prepare a statement object using the connection: that returns the event
	 * information for all the events that are registered to a visitor<br/>
	 * Execute the query to retrieve the results into a result set<br/>
	 * Place each event record‘s information in an event list. <br/>
	 * 
	 * @param visitor
	 *            (type Visitor)
	 * 
	 * @return Collection of Event Arrays (type Event)
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * 
	 */
	public ArrayList<Event> registeredEvents(Visitor visitor)
			throws ClassNotFoundException, SQLException {
		ArrayList<Event> list = new ArrayList<Event>();
		connection = FERSDataConnection.createConnection();
		statement = connection.prepareStatement(query.getStatusQuery());
		statement.setInt(1, visitor.getVisitorId());
		resultSet = statement.executeQuery();
		while (resultSet.next()) {
			Event event = new Event();
			event.setEventid(resultSet.getInt("eventid"));
			event.setName(resultSet.getString("name"));
			event.setDescription(resultSet.getString("description"));
			event.setPlace(resultSet.getString("places"));
			event.setDuration(resultSet.getString("duration"));
			event.setEventtype(resultSet.getString("eventtype"));
			event.setSignupid(resultSet.getInt("signupid"));
			list.add(event);

		}

		// TODO: Add code here.....
		// TODO: Pseudo-code are in the block comments above this method
		// TODO: For more comprehensive pseudo-code with details, refer to the
		// Component/Class Detailed Design Document
		System.out.println(list.size());
		FERSDataConnection.closeConnection();
		return list;
	}

	/**
	 * <br/>
	 * METHOD DESCRIPTION:<br/>
	 * DAO method to update visitor with additional information <br/>
	 * <br/>
	 * 
	 * @param visitor
	 *            (type Visitor)
	 * 
	 * @return int
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * 
	 * 
	 */
	public int updateVisitor(Visitor visitor) throws ClassNotFoundException,
			SQLException {
		connection = FERSDataConnection.createConnection();
		statement = connection.prepareStatement(query.getUpdateQuery());
		statement.setString(1, visitor.getFirstName());
		statement.setString(2, visitor.getLastName());
		statement.setString(3, visitor.getUserName());
		statement.setString(4, visitor.getPassword());
		statement.setString(5, visitor.getEmail());
		statement.setString(6, visitor.getPhoneNumber());
		statement.setString(7, visitor.getAddress());
		statement.setInt(8, visitor.getVisitorId());

		int status = statement.executeUpdate();
		log.info("Updating visitor details in Database for Visitor ID :"
				+ visitor.getVisitorId());
		FERSDataConnection.closeConnection();
		return status;
	}

	/**
	 * <br/>
	 * METHOD DESCRIPTION: <br/>
	 * DAO method to unregister from events <br/>
	 * 
	 * 
	 * @param visitor
	 *            (type Visitor)
	 * @param eventid
	 *            (type int)
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws Exception
	 * 
	 */

	public void unregisterEvent(Visitor visitor, int eventid)
			throws ClassNotFoundException, SQLException, Exception {
		connection = FERSDataConnection.createConnection();
		statement = connection.prepareStatement(query.getDeleteEventQuery());
		statement.setInt(1, eventid);
		statement.setInt(2, visitor.getVisitorId());
		int status = statement.executeUpdate();
		if (status <= 0)
			throw new FERSGenericException("Records not updated properly",
					new Exception());
		log.info("unregistering event in Database for the visitor :"
				+ visitor.getFirstName());
		FERSDataConnection.closeConnection();
	}

	@SuppressWarnings("finally")
	public int changePassword(Visitor visitor) throws ClassNotFoundException,
			SQLException {
		// TODO: Declare a variable with name status of type int and initialize
		// with -1

		// TODO: Start a try block

		// TODO: Get a database connection from FERSDataConnection class

		int status = -1;
		try {
			connection = FERSDataConnection.createConnection();
			if (connection != null) {
				// TODO: Add an if statement to check if Visitor object is null

				if (visitor != null) {
					System.out.println("7878787" + matchWithOldPwd(visitor));
					if (matchWithOldPwd(visitor)) {
						status = -10;
					} else {
						// TODO: create a prepared statement with password
						// change query
						// TODO: Set the values of visitor and password to the
						// query paramet
						// TODO: Execute the password change query
						// TODO: Print a log message ‘Visitor password
						// successfully modified’ along with visitor ID

						statement = connection.prepareStatement(query
								.getChangePWDQuery());
						statement.setString(1, visitor.getPassword());
						statement.setInt(2, visitor.getVisitorId());
						int st = statement.executeUpdate();
						if (st <= 0) {
							log.info("Visitor password not modified");
						} else {
							log.info("Visitor password successfully modified"
									+ visitor.getVisitorId());

						}
						status = st;

					}
				} else {

					// TODO: log an error message ‘Visitor details are invalid’
					// to log file
					log.info("Visitor details are invalid");

				}
			} else {

				throw new SQLException(
						"Connection Error, could not establish connection with database");

			}
		} finally {

			// TODO: Add a finally block to close prepared statement and
			// connection objects

			// TODO: Return status value

			FERSDataConnection.closeConnection();
			// System.out.println("ststus of dao"+status);

			return status;
		}

	}

	@SuppressWarnings("finally")
	private boolean matchWithOldPwd(Visitor visitor) throws SQLException {

		String currentPWD = "";

		try { // TODO: Create a prepared statement with with verify password
				// query
			// TODO: Set the visitor ID as parameter to the query
			// TODO: Execute the query and retrieve the current password if
			// resultset has values

			statement = connection.prepareStatement(query.getVerifyPWDQuery());
			statement.setInt(1, visitor.getVisitorId());
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				currentPWD = resultSet.getString("password");
				System.out.println("oldpass" + currentPWD);
			}
			System.out.println("current=" + visitor.getPassword());
			if (currentPWD.equalsIgnoreCase(visitor.getPassword())) {
				System.out.println("same pass");

				log.info("New password must be different from previous password, please choose a different password");

				return true;

			}

		} finally {
			// TODO: Add a finally block to clean up prepared statement
			// TODO: Return false after finally block

			statement = null;

		}
		return false;

	}
}
