package com.example.csc221_p4;
import java.sql.*;


public class StudentsDatabase implements TableInterface, StudentsDatabaseInterface {
    private String url;
    private String user;
    private String pass;
    static Connection connect;

    StudentsDatabase(String link, String username, String password) throws SQLException{
        this.url = link;
        this.user = username;
        this.pass = password;
        this.connect = DriverManager.getConnection(url,user,pass);
    }

    //Truncate Table
    void truncate_table(String table) throws SQLException {
        PreparedStatement st_table = connect.prepareStatement(StudentsDatabaseInterface.TruncateTable(table));
        st_table.executeUpdate();
    }
    class Schedule {
        String table;
        String insert;

        //Constructor
        Schedule(String table, String insert) throws SQLException {
            drop_table();
            //Create Schedule
            this.table = table;
            PreparedStatement st_table = connect.prepareStatement(table);
            st_table.executeUpdate();

            //Populate Schedule
            this.insert = insert;
            PreparedStatement st_insert = connect.prepareStatement(insert);
            st_insert.executeUpdate();
        }

        // Methods
        // updating instructor
        void update_instructor(String new_instructor, String condition) throws SQLException {
            PreparedStatement st_update =
                    connect.prepareStatement(StudentsDatabaseInterface.updateInstructor("Schedule",
                            new_instructor, condition));

            st_update.executeUpdate();
        }

        //adding Class

        void add_class(String id, String sectionNo, String title, int year, String semester,
                       String instructor, String department,String program) throws SQLException{
            PreparedStatement st_class =
                    connect.prepareStatement(StudentsDatabaseInterface.singleInsert("Schedule", id,
                            sectionNo, title, year, semester, instructor, department, program));
            st_class.executeUpdate();
        }

        //removing Class
        void delete_class(String condition) throws SQLException{
            PreparedStatement st_delete =
                    connect.prepareStatement(StudentsDatabaseInterface.deleteEntry("Schedule", condition));
            st_delete.executeUpdate();
        }

        //Deleting Schedule table
        void drop_table() throws SQLException {
            String sql = " DROP TABLE Schedule";
            PreparedStatement drop_table = connect.prepareStatement(sql); drop_table.executeUpdate();
        }

        //ResultSet Table
        static ResultSet pull() throws SQLException {
            String query = "SELECT * FROM Schedule";
            Statement statement = connect.createStatement();
            ResultSet rs = statement.executeQuery(query);
            return rs;
        }
    }

    class Students {
        int emplID;
        String firstName;
        String lastName;
        String email;
        char gender;

        //Constructor
        Students(Students temp) throws SQLException {
            make_table();
            this.emplID = temp.getId();
            this.firstName = temp.getfirstname();
            this.lastName = temp.getlastname();
            this.email = temp.getemail();
            this.gender = temp.getgender();
        }

        Students(int id, String firstName, String lastName, String email, char gender) throws SQLException {
            make_table();
            this.emplID = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.gender = gender;
        }

        //get Methods
        public int getId() {
            return emplID;
        }

        public String getfirstname() {
            return firstName;
        }

        public String getlastname() {
            return lastName;
        }

        public String getemail() {
            return email;
        }

        public char getgender() {
            return gender;
        }

        //set methods
        public void setId(int id) {
            this.emplID = id;
        }

        public void setfirstname(String name) {
            firstName = name;
        }

        public void setlastname(String name) {
            lastName = name;
        }

        public void setemail(String email) {
            email = email;
        }

        public void setgender(char g) {
            gender = g;
        }

        public void setALL(int id, String fname, String lname, String email, char g) {
            this.emplID = id;
            this.firstName = fname;
            this.lastName = lname;
            this.email = email;
            this.gender = g;
        }

        //Name Gen()
        public String gen(boolean type) {
            if (type) {
                return String.format("%s%s%s@gmail.com", (char) ('A' + (Math.random() * 100) % 26), (char) ('A' + (Math.random() * 100) % 26), (char) ('A' + (Math.random() * 100) % 26));
            }
            return String.format("%s%s%s", (char) ('A' + (Math.random() * 100) % 26), (char) ('A' + (Math.random() * 100) % 26), (char) ('A' + (Math.random() * 100) % 26));
        }

        //Pull Student Records from Emplid
        public Students getRecord(int emplID) throws SQLException {
            String pull = "SELECT * FROM Students WHERE empID = " + emplID;

            Statement statement = connect.createStatement();
            ResultSet rs = statement.executeQuery(pull);
            rs.next();
            Students temp = new
                    Students(rs.getInt("empID"), rs.getString("firstName"), rs.getString("lastName"),
                    rs.getString("email"), rs.getString("gender").charAt(0));
            return temp;
        }

        //Push new Student Record into the Student Table
        public void setRecord() throws SQLException {
            String push = String.format("INSERT INTO Students VALUES (%d, '%s', '%s', '%s', '%c')", emplID, firstName, lastName, email, gender);
            PreparedStatement statement = connect.prepareStatement(push);
            statement.executeUpdate();
        }

        //Create the Student table
        public void make_table() throws SQLException {
            drop_table();
            PreparedStatement statement = connect.prepareStatement(createStudent);
            statement.executeUpdate();
        }

        void drop_table() throws SQLException {
            String sql = "DROP TABLE Students";
            PreparedStatement drop_table = connect.prepareStatement(sql);
            drop_table.executeUpdate();
        }

        //toString
        public String toString() {
            return String.format("EmplID: %d Name: %s %s Email: %s Gender: %c", emplID, firstName, lastName, email, gender);
        }

    }

    class Courses {
        String table;
        String courseId;
        String title;
        String department;

        //Constructor
        Courses(String table) throws SQLException {
            drop_table();
            //Create Course
            this.table = table;
            PreparedStatement st_table = connect.prepareStatement(table);
            st_table.executeUpdate();

            //Populate Course
            PreparedStatement st_insert = connect.prepareStatement(insertCourses);
            st_insert.executeUpdate();
        }

        //Get Records From Course
        public void getRecord(int id) throws SQLException { //
            String pull = "SELECT * FROM Courses WHERE courseID = " + id;

            Statement statement = connect.createStatement();
            ResultSet rs = statement.executeQuery(pull);
            if (rs.next()) {
                this.courseId = rs.getString("courseID");
                this.title = rs.getString("courseTitle");
                this.department = rs.getString("department");
            } else {
                System.out.println("Course Record Does not exist!");
            }
        }
        //Drop table
        void drop_table() throws SQLException {
            String sql = "DROP TABLE Courses";
            PreparedStatement drop_table = connect.prepareStatement(sql);
            drop_table.executeUpdate();
        }
        public String toString() {
            return String.format("CourseID: %s, CourseTitle: %s, Department: %s",
                        courseId, title, department);
            }
        }

    class Classes {
        String table;

        //Constructor
        Classes(String table) throws SQLException {
            drop_table();
            //Create Course
            this.table = table;
            PreparedStatement st_table = connect.prepareStatement(table);
            st_table.executeUpdate();
        }

        //Populate Classes
        void populate(String CourseID, int id, String sectionNo, int year, String semester, char grade)
                throws SQLException {
            String Value = String.format("'%s', %d, '%s', %d, '%s', '%c'", CourseID, id,
                    sectionNo, year, semester, grade);
            String sql = "INSERT INTO Classes\n"
                    + "VALUES ("
                    + Value + ")";
            PreparedStatement st_insert = connect.prepareStatement(sql);
            st_insert.executeUpdate();
        }

        //Method Get
        String gettable() {

            return table;
        }

        //Remove Classes - Delete By Course ID
        void delete_CID(String CourseID) throws SQLException {
            String sql = String.format("DELETE FROM Classes WHERE CourseID = '%s'", CourseID);
            PreparedStatement st_insert = connect.prepareStatement(sql);
            st_insert.executeUpdate();
        }

        // Delete By Student ID
        void delete_SID(int id) throws SQLException {
            String sql = String.format("DELETE FROM Classes WHERE CourseID = '%s'", id);
            PreparedStatement st_insert = connect.prepareStatement(sql);
            st_insert.executeUpdate();
        }

        // Delete by SectionNo
        void delete_SNO(String sectionNo) throws SQLException {
            String sql = String.format("DELETE FROM Classes WHERE CourseID = '%s'", sectionNo);
            PreparedStatement st_insert = connect.prepareStatement(sql);
            st_insert.executeUpdate();
        }

        // Drop TABLE
        void drop_table() throws SQLException {
            String sql = "DROP TABLE Classes";
            PreparedStatement drop_table = connect.prepareStatement(sql);
            drop_table.executeUpdate();
        }
    }

    class AggregateGrades {
        String table;

        //Creating Table
        AggregateGrades(String table) throws SQLException { //
            drop_table();
            this.table = table;
            PreparedStatement st_table = connect.prepareStatement(table);
            st_table.executeUpdate();
        }

        //Populate table
        void populate(String id) throws SQLException {
            PreparedStatement st_table =
                    connect.prepareStatement(StudentsDatabaseInterface.filterAggregateTableByCourseID(id));
            st_table.executeUpdate();
        }

        //Drop Table
        void drop_table() throws SQLException {
            String sql = "DROP TABLE AggregateGrades";
            PreparedStatement drop_table = connect.prepareStatement(sql);
            drop_table.executeUpdate();
        }

        //Get Grades from AggregateGrades
        ResultSet pull() throws SQLException {
            String query = "SELECT * FROM AggregateGrades";
            Statement statement = connect.createStatement();
            ResultSet rs = statement.executeQuery(query);
            return rs;
        }
    }

}
