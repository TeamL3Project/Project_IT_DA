package util;

import javax.naming.*;
import javax.sql.DataSource;

 public  class dbService {
	public static DataSource ds = null;
	public static void dbConntect() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
