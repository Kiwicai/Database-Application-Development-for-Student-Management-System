import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Scanner;

public class db3 {
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/project3-nudb";
	private static final String driver="com.mysql.jdbc.Driver";
	private Connection conn;
	private int studentId;
	private String password;
	
	public db3() {
		conn = getConn("root", "", "");
		studentId = 0;
		password = null;
	}
	
	public db3(int id, String pw) {
		conn = getConn("root", "", "");
		studentId = id;
		password = pw;
	}
	
	public int studentid() {
		return this.studentId;
	}
	
	public String password() {
		return this.password;
	}
	
    public Connection getConn(String username,String password,String DBname){
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(URL,username,password);
            
        } catch (ClassNotFoundException e) {            
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
   public int loginCheck(int id, String pw) throws SQLException {
	   CallableStatement cs = null;
	   String sql = "{call login(?, ?, ?)}"; 
	   cs = conn.prepareCall(sql);  
	   cs.setInt(1, id);
	   cs.setString(2, password);
	   cs.registerOutParameter(3, Types.INTEGER);
	   cs.execute();  
	   int result = cs.getInt(3);
	   return result;
   }
   
   public void StudentMenu(int id, String password) throws SQLException {
	   CallableStatement cs = null;
	   String sql = "{call currentCourses(?, ?)}"; 
	   cs = conn.prepareCall(sql);  
	   cs.setInt(1, id);
	   cs.setString(2, password);
	   boolean hadResults = cs.execute();  
//	   int result = cs.getInt(3);
//	   if (result == -1) {
//		   // inputs do not match with the database;
//		   System.out.println("Wrong studentID or password.");
//	   }
	   int i = 0; 
//	   System.out.println("successfully logged in!");  
	   while(hadResults) {  
//		   System.out.println("current courses: ");
	       ResultSet rs = cs.getResultSet();  
	       while (rs != null && rs.next()) {  
	          String id1 = rs.getString(1);  
	          String semester = rs.getString(2);  
	          String year = rs.getString(3);
	          
	          System.out.print("courseId " + (++i) + " : "+ id1);  
	          System.out.print(" semester : " + semester + " year : "+ year); 
	          System.out.println();
	       }  
	       hadResults = cs.getMoreResults(); //检查是否存在更多结果集  
	   }
	   this.studentId = id;
	   this.password = password;
	   
   }
   public void courseCouldWithdraw(int id, String password) throws SQLException {
	   CallableStatement cs = null;
	   String sql = "{call CourseCouldWithdraw(?, ?)}"; 
	   cs = conn.prepareCall(sql);  
	   cs.setInt(1, id);
	   cs.setString(2, password);
	   boolean hadResults = cs.execute();  
//	   int result = cs.getInt(3);
//	   if (result == -1) {
//		   // inputs do not match with the database;
//		   System.out.println("Wrong studentID or password.");
//	   }
	   int i = 0; 
//	   System.out.println("successfully logged in!");  
	   while(hadResults) {  
//		   System.out.println("current courses: ");
	       ResultSet rs = cs.getResultSet();  
	       while (rs != null && rs.next()) {  
	          String id1 = rs.getString(1);  
	          String semester = rs.getString(2);  
	          String year = rs.getString(3);
	          
	          System.out.print("courseId " + (++i) + " : "+ id1);  
	          System.out.print(" semester : " + semester + " year : "+ year); 
	          System.out.println();
	       }  
	       hadResults = cs.getMoreResults(); //检查是否存在更多结果集  
	   }
	   this.studentId = id;
	   this.password = password;
	   
   }
   public void showTranscript() throws SQLException {
	   CallableStatement cs = null;
	   Statement stmt = null;
	   String sql = "{call FullTranscript(?, ?)}"; 
	   cs = conn.prepareCall(sql);  
	   cs.setInt(1, studentId);
	   cs.setString(2, password);
	   boolean hadResults = cs.execute();     
	   int i = 0;  
	   while(hadResults) {   
	       ResultSet rs = cs.getResultSet();
	       while (rs != null && rs.next()) {  
	          String id1 = rs.getString(1);  
	          String grade = rs.getString(2);
	          String semester = rs.getString(3);
	          int year = rs.getInt(4);
	          
	          System.out.printf("%s %s %d %s %n", id1, semester, year, grade);
	       }  
	       hadResults = cs.getMoreResults(); //检查是否存在更多结果集  
	   }
   }
   
   public void showCourseDetail(String courseNum) throws SQLException {
	   CallableStatement cs = null;
	   String sql = "{call CourseDetail(?, ?, ?)}"; 
	   
	   cs = conn.prepareCall(sql);  
	   cs.setInt(1, studentId);
	   cs.setString(2, password);
	   cs.setString(3, courseNum);
	   boolean hadResults = cs.execute();      
	   while(hadResults) {   
	       ResultSet rs = cs.getResultSet();  
	       while (rs != null && rs.next()) {  
	          String id1 = rs.getString(1);  
	          String title = rs.getString(2);
	          String year = rs.getString(3);
	          String quarter = rs.getString(4);
	          int enrollNum = rs.getInt(5);
	          int maxEnroll = rs.getInt(6);
	          String lec = rs.getString(7);
	          String grade = rs.getString(8);
	          System.out.println("courseNo.: " + id1);
	          System.out.println("title : " + title);
	          System.out.println("year : " + year);	
	          System.out.println("quarter : " + quarter);
	          System.out.println("enrollNum : " + enrollNum);
	          System.out.println("maxEnroll : " + maxEnroll);
	          System.out.println("lec :" + lec);
	          System.out.println("grade :" + grade);
	          //System.out.printf("%s %s %s %s %d %d %n", id1, title, year,quarter,enrollNum,maxEnroll);
	       }  
	       hadResults = cs.getMoreResults(); //检查是否存在更多结果集  
	   }
   }
   
   public int courseCouleEnroll() throws SQLException {
	   CallableStatement cs = null;
	   String sql = "{call CourseCouldEnroll(?, ?)}"; 
	   cs = conn.prepareCall(sql); 
	   cs.setInt(1, studentId);
	   cs.registerOutParameter(2, Types.INTEGER);
	   boolean hadResults = cs.execute();  
	   int result = cs.getInt(2);
	   if (result == 1) {
		   // have courses that could enroll
		   System.out.println("Courses offering at this time : ");
		   
		   while(hadResults) {   
		       ResultSet rs = cs.getResultSet();  
		       while (rs != null && rs.next()) {  
//		          String prereqCourseID = rs.getString(1);  
		          String courseID = rs.getString(1);
		          String semeter = rs.getString(2);
		          String year = rs.getString(3);
//		          System.out.print("courseID : " + courseID);
//		          System.out.print(" semeter : " + semeter);
//		          System.out.print(" year : " + year);	
//		          System.out.println();
		          System.out.printf("%s %s %s %n", courseID, semeter, year);
		       }  
		       hadResults = cs.getMoreResults(); //检查是否存在更多结果集  
		   }
	   }
	   return result;
   }
//   
//   public int enroll2(int id, String cid, String sem, int y) throws SQLException {
//	   CallableStatement cs = null;
//	   Statement stmt = null;
//	   String sql = "{call enroll(?, ?, ?, ?, ?)}";
//	   cs = conn.prepareCall(sql);  
//	   cs.setInt(1, id);
//	   cs.setString(2, cid);
//	   cs.setString(3, sem);
//	   cs.setInt(4, y);
//	   cs.registerOutParameter(5, Types.INTEGER);
//	   
//	   boolean hadResults = cs.execute();  
//	   int result = cs.getInt(5);
//	   if (result == 1) 
//		   System.out.println("permit to enroll");
//	   else 
//		   System.out.println("cannot enroll");
//	   return result; // 1 could enroll -1 otherwise
//   }
//   
//   public void processEnroll(int id, String cid, String sem, int y) throws SQLException {
//	   CallableStatement cs = null;
//	   Statement stmt = null;
//	   String sql = "{call processenroll(?, ?, ?, ?)}"; 
//	   cs.setInt(1, id);
//	   cs.setString(2, cid);
//	   cs.setString(3, sem);
//	   cs.setInt(4, y);
////	   cs.registerOutParameter(5, Types.INTEGER);
//	   cs = conn.prepareCall(sql);  
//	   boolean hadResults = cs.execute();  
//	   if (!hadResults) {
//		  int  temp = 1; 
//	   }
//	   System.out.println("");
//   }
   
   
   
   
   public int enroll(int id, String cid, String sem, int y) throws SQLException {
	   CallableStatement cs = null;
	   Statement stmt = null;
	   String sql = "{call enroll(?, ?, ?, ?, ?)}";
	   cs = conn.prepareCall(sql);  
	   cs.setInt(1, id);
	   cs.setString(2, cid);
	   cs.setString(3, sem);
	   cs.setInt(4, y);
	   cs.registerOutParameter(5, Types.INTEGER);
	   
	   boolean hadResults = cs.execute();  
	   int result = cs.getInt(5);
	   if (result == 1) 
		   processEnroll(id, cid, sem, y);
	   if (result == 0){
		   System.out.println("Can'not enroll! Prerequisites shown below are not satisfied!");
		   
		   while(hadResults) {   
		       ResultSet rs = cs.getResultSet();  
		       while (rs != null && rs.next()) {    
		          String prereqCourseID = rs.getString(1);
		          System.out.print("PrereqCourseID : " + prereqCourseID);
		          System.out.println();
		       }  
		       hadResults = cs.getMoreResults(); //检查是否存在更多结果集  
		   }
	   }		   
	   else if (result == -1) 
		   System.out.println("Can'not enroll! Maximum enrollment number has been reached!");
	   return result; 
   }
   
   public int processEnroll(int id, String cid, String sem, int y) throws SQLException {
	   CallableStatement cs = null;
	   Statement stmt = null;
	   String sql = "{call processenroll(?, ?, ?, ?)}";
	   cs = conn.prepareCall(sql); 
	   cs.setInt(1, id);
	   cs.setString(2, cid);
	   cs.setString(3, sem);
	   cs.setInt(4, y);  
	   boolean hadResults = cs.execute(); //update the database(transcript+enrollment)
//	   System.out.println("You have enrolled successfully!");
	   if (hadResults == true) {
		   return 1;
	   }
	   return 0;
   }

   public void showPersonInfo() throws SQLException {
	   CallableStatement cs = null;
	   String sql = "{call showPersonInfo(?, ?)}"; 
	   
	   cs = conn.prepareCall(sql);  
	   cs.setInt(1, studentId);
	   cs.setString(2, password);
	   boolean hadResults = cs.execute();      
	   while(hadResults) {   
	       ResultSet rs = cs.getResultSet();  
	       while (rs != null && rs.next()) {  
	          String id = rs.getString(1);  
	          String name = rs.getString(2);
	          String password = rs.getString(3);
	          String address = rs.getString(4);
	          System.out.printf("%s %s %s %s %n", id, name, password, address);
	       }  
	       hadResults = cs.getMoreResults(); //检查是否存在更多结果集  
	   }
   }
   
   public void changePersonInfo_password(String newPW) throws SQLException {
	   CallableStatement cs = null;   
	   String sql = "{call changePW(?, ?, ?)}"; 
	   cs = conn.prepareCall(sql);  
	   cs.setInt(1, studentId);
	   cs.setString(2, password);
	   cs.setString(3, newPW);
	   boolean haveUpdate = cs.execute();
	   
	   if (!haveUpdate) 
		   if (cs.getUpdateCount() != -1)
			   System.out.println("change password successfully!");
	   else
		   System.out.println("do not change the password!");
   }
   
   public void changePersonInfo_address(String newAD) throws SQLException {
	   CallableStatement cs = null;
	   
	   String sql = "{call changeAddress(?, ?, ?)}"; 
	   cs = conn.prepareCall(sql);  
	   cs.setInt(1, studentId);
	   cs.setString(2, password);
	   cs.setString(3, newAD);
	   boolean haveUpdate = cs.execute();
	   
	   if (!haveUpdate) 
		   if (cs.getUpdateCount() != -1)
			   System.out.println("change Address successfully!");
	   else
		   System.out.println("do not change the address!");
	   
   }
   
   public int withdraw(int id, String pw, String cid, int year, String semester) throws SQLException {
	   CallableStatement cs = null;
	   String sql = "{call withdraw(?, ?, ?, ?, ?)}"; 
	   cs = conn.prepareCall(sql);  
	   cs.setInt(1, studentId);
	   cs.setString(2, cid);
	   cs.setInt(3, year);
	   cs.setString(4, semester);
	   cs.registerOutParameter(5, Types.INTEGER);
	   cs.execute();  
	   int result = cs.getInt(5);
	   return result;
   }
   
   public int withdrawTrigger() throws SQLException {
	   CallableStatement cs = null;
	   String sql = "{call TriggerInsert(?)}"; 
	   cs = conn.prepareCall(sql);  
	   cs.registerOutParameter(1, Types.INTEGER);
	   cs.execute();  
	   int result = cs.getInt(1);
	   return result;
   }
  
   public static void main(String[] args) throws SQLException {
//	   int id = 3213;
//	   String password = "dinner";
//	   db3 test = new db3(id, password);
//	   System.out.println("please enter the student ID and password: ");
//	   int id = 3213;
//	   String password = "lunch";
//	   System.out.println("Please wait, entering into database...");
//	 int id = 3213;
//	 String password = "DINNER";
//	 System.out.println(studentId);
//       test.courseCouleEnroll();
//	   System.out.println("show transcript: ");
//	   System.out.println(test.withdraw(id, password, "info3315"));
//	   test.StudentMenu(id, password);
//	   test.courseCouleEnroll();
	   
//	   test.enroll(id, cid, sem, y);
//	   System.out.println("show detail : ");
//	   test.showCourseDetail("COMP2129");
//	   System.out.println("show personal information : ");
//	   test.showPersonInfo();
//	   System.out.println("change password: ");
//	   test.changePersonInfo_password();
//	   test.changePersonInfo_address();
//	   test.enroll1();
//	   System.out.println("enter cid, sem. year:");
//
//	   String cid = sc.nextLine();
//
//	   String sem = sc.nextLine();
//
//	   int y = Integer.parseInt(sc.nextLine());
//	   test.enroll2(id, cid, sem, y);
   }
}
