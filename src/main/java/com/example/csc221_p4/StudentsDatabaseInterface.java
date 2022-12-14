package com.example.csc221_p4;

public interface StudentsDatabaseInterface {

    String createSchedule = "CREATE TABLE Schedule("
            + "courseID VARCHAR(10) NOT NULL UNIQUE, "
            + "sectionNo VARCHAR(5) NOT NULL UNIQUE, "
            + "title VARCHAR(50), "
            + "year INT, "
            + "semester VARCHAR(6), "
            + "instructor VARCHAR(50), "
            + "department VARCHAR(50), "
            + "program VARCHAR(20), "
            + "PRIMARY KEY(courseID, sectionNo))";

    String createStudent = "CREATE TABLE Students(" + "empID INT NOT NULL PRIMARY KEY , "
            + "firstName VARCHAR(50), "
            + "lastName VARCHAR(50), "
            + "email VARCHAR(50), "
            + "gender CHAR CHECK(gender = 'M' OR gender = 'F' OR gender = 'U'))";

    String createClasses = "CREATE TABLE Classes(" + "courseID VARCHAR(10) NOT NULL, "
            + "studentID INT NOT NULL, "
            + "sectionNo VARCHAR(5) NOT NULL, "
            + "year INT, "
            + "semester VARCHAR(6), "
            + "grade CHAR CHECK(grade = 'A' OR grade = 'B' OR grade = 'C' OR grade = 'D' " +
            "OR grade = 'F' OR grade = 'W')"
            + "PRIMARY KEY(courseID, studentID, sectionNo))";

    String createCourses = "CREATE TABLE Courses("
            + "courseID VARCHAR(10) NOT NULL PRIMARY KEY, "
            + "courseTitle VARCHAR(50), "
            + "department VARCHAR(50))";

    String createAggregateGrades = "CREATE TABLE AggregateGrades(" + "grades CHAR PRIMARY KEY, "
            + "studentcount INT)";

    String insertAggregateGrades = "INSERT INTO AggregateGrades " + "SELECT grade, count(grade) "
            + "FROM Classes "
            + "GROUP BY grade";

    String selectAggregateGrades = "SELECT * FROM AggregateGrades";

    String insertCourses = "INSERT INTO Courses(courseID, courseTitle, department) " +
            "SELECT courseID, title, department FROM Schedule";

    //Static Method
    //Delete record from Schedule
    public static String deleteEntry(String tablename, String condition) {
        return String.format("DELETE FROM %s WHERE courseID = '%s'", tablename,
                condition);
    }

    //Update Instructor
    public static String updateInstructor(String tablename, String instructor, String condition) {
        return String.format("UPDATE %s SET instructor = '%s' WHERE courseID= '%s'", tablename,
                instructor, condition);
    }

    //Single Schedule Insert
    public static String singleInsert(String tablename, String id, String sectionNo, String title, int year,
                                      String semester, String instructor, String department, String program) {
        return String.format("INSERT INTO %s VALUES ('%s', '%s', '%s', %d,'%s', '%s', '%s', '%s')",
                tablename, id, sectionNo, title, year, semester, instructor, department, program);
    }

    //Drop Table
    public static String drop_table(String tablename) {
        return String.format("DROP TABLE %s",
                tablename, tablename);
    }

    //Truncate Table
    public static String TruncateTable(String tablename) {
        return "TRUNCATE TABLE " + tablename;
    }

    //BULK INSERT
    public static String InsertTable(String file, String tablename) {
        return "LOAD DATA INFILE  " + file + "INTO TABLE " + tablename + " WITH FIELDS TERMINATED BY '\t' " +
                "LINES TERMINATED BY '\n'";
    }

    //Display Table
    public static String DisplayTable(String tablename) {
        return "SELECT * FROM " + tablename + "GO";
    }

    //Return Filter Grades
    public static String filterAggregateTableByCourseID(String id) {
        return "INSERT INTO AggregateGrades "
                + "SELECT grade,count(grade) "
                + "FROM Classes WHERE courseID "
                + "LIKE '" + id + "%' "
                + "GROUP BY grade";
    }
}
