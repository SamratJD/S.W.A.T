package libraries;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import utilities.Constants;
import utilities.TestBase;

public class DataFromDB extends TestBase {

	/**
	 * @author Samrat
	 * @category Database function
	 * @param columnName
	 * @param query
	 * @return String
	 * @throws Throwable
	 */
	public static String executeDB(String columnName, String query) throws Throwable {
		try {
			Properties prop = new Properties();
			String queryResult;
			FileInputStream fis = new FileInputStream(Constants.CONFIG_FILE_PATH);
			prop.load(fis);
			if (prop.getProperty("dbType").equalsIgnoreCase("Oracle")) {
				log.info("Database is selected  as Oracle DB.");
				queryResult = datafromOracle(columnName, query);
			} else {
				log.info("Database is selected as MySQL DB.");
				queryResult = datafromMySQL(columnName, query);
			}
			return queryResult;
		} catch (Exception e) {
			log.error("Error occurred while trying to run database query " + e.getMessage());
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * @author Samrat
	 * @category Database function
	 * @param columnName
	 * @param query
	 * @return String
	 * @throws Throwable
	 */
	public static String datafromMySQL(String columnName, String query) throws Throwable {
		try {
			Properties prop = new Properties();
			FileInputStream fis = new FileInputStream(Constants.CONFIG_FILE_PATH);
			prop.load(fis);

			String userName = prop.getProperty("username");
			String password = prop.getProperty("password");
			String host = prop.getProperty("host");
			String portNo = prop.getProperty("port");
			String schemaName = prop.getProperty("databaseName");

			Connection con = DriverManager.getConnection(
					"jdbc:mysql://" + host + ":" + portNo + "/" + schemaName + "?autoReconnect=true&useSSL=false",
					userName, password);
			log.info("Connected to MySQL database.");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			String outputRecord = null;
			while (rs.next()) {
				outputRecord = rs.getString(columnName);
			}
			log.info("Executed the query.");
			return outputRecord;
		} catch (Exception e) {
			log.error("Error occurred while trying to run database query " + e.getMessage());
			e.printStackTrace();
			return "";
		}

	}

	/**
	 * @author Samrat
	 * @category Database function
	 * @param columnName
	 * @param query
	 * @return String
	 * @throws Throwable
	 */
	public static String datafromOracle(String columnName, String query) throws Throwable {
		try {
			Properties prop = new Properties();
			FileInputStream fis = new FileInputStream(Constants.CONFIG_FILE_PATH);
			prop.load(fis);
			String userName = prop.getProperty("OracleUsername");
			String password = prop.getProperty("OraclePassword");
			String portNo = prop.getProperty("OraclePort");
			String serverName = prop.getProperty("OracleServerName");

			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:" + portNo + ":" + serverName,
					userName, password);
			log.info("Connected to Oracl database.");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			String outputRecord = null;
			while (rs.next()) {
				outputRecord = rs.getString(columnName);
			}
			log.info("Executed the query.");
			return outputRecord;
		} catch (Exception e) {
			log.error("Error occurred while trying to run database query " + e.getMessage());
			e.printStackTrace();
			return "";
		}
	}
}
