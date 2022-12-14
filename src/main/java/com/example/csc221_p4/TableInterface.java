package com.example.csc221_p4;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.example.csc221_p4.StudentsDatabase.connect;

public interface TableInterface {

    static void update_instructor(String new_instructor, String condition) throws SQLException {
        PreparedStatement st_update =
                connect.prepareStatement(StudentsDatabaseInterface.updateInstructor("Schedule",
                        new_instructor, condition));

        st_update.executeUpdate();
    }

    static void add_class(String id, String sectionNo, String title, int year, String semester,
                           String instructor, String department, String program) throws SQLException{
        PreparedStatement st_class =
                connect.prepareStatement(StudentsDatabaseInterface.singleInsert("Schedule", id,
                        sectionNo, title, year, semester, instructor, department, program));
        st_class.executeUpdate();
    }

    //removing Class
    static void delete_class(String condition) throws SQLException{
        PreparedStatement st_delete =
                connect.prepareStatement(StudentsDatabaseInterface.deleteEntry("Schedule", condition));
        st_delete.executeUpdate();
    }

    //Deleting Schedule table
    static void drop_table() throws SQLException {
        String sql = " DROP TABLE Schedule";
        PreparedStatement drop_table = connect.prepareStatement(sql); drop_table.executeUpdate();
    }

    static ResultSet pull() throws SQLException {
        String query = "SELECT * FROM Schedule";
        Statement statement = connect.createStatement();
        ResultSet rs = statement.executeQuery(query);
        return rs;
    }
}
