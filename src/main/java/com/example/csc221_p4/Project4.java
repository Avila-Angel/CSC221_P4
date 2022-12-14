package com.example.csc221_p4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Project4 extends Application {
    static int x = 800;
    static int y = 800;
    @Override
    public void start(Stage stage) throws SQLException {
        stage.setTitle("Project 4"); // title
        Canvas cv = new Canvas(x, y);
        GraphicsContext gc = cv.getGraphicsContext2D();
        HBox root = new HBox(cv);
        Label label = new Label("Enter CourseID:   ");
        label.setFont(Font.font(18));
        TextField input = new TextField();
        input.setMaxWidth(100);
        Button button = new Button("Enter");

        root.getChildren().addAll(label, input, button);
        stage.setScene(new Scene(root)); // creates Scene from the group root
        stage.show();


        String url = "jdbc:mysql://localhost:3306/StudentsDatabase";
        String user = "root";
        String pass = "Zalak31591";

        //Connection connection = DriverManager.getConnection(url, user, pass);

        String table = "Schedule";
        String file = "/Users/Angel/Documents/Java /Java Projects/CSC221_P4/src/main/java/ScheduleSpring22.txt"; // File Location

        char[] grades = {'A', 'B', 'C', 'D', 'F', 'W'};
        char[] gender = {'M', 'F', 'U'};


        try {

            StudentsDatabase db = new StudentsDatabase(url, user, pass);
            StudentsDatabase.Schedule Schedule = db.new
                    Schedule(db.createSchedule, StudentsDatabaseInterface.InsertTable(file, table));
            StudentsDatabase.Students Student = db.new Students(12345671, "Angel", "A",
                    "aa@gmail.com", 'M');
            StudentsDatabase.Courses Courses = db.new Courses(db.createCourses);
            StudentsDatabase.Classes Classes = db.new Classes(db.createClasses);
            ResultSet result = StudentsDatabase.Schedule.pull();
            int id = 1;

            while (result.next()) {
                for (int i = 0; i < 25; i++) {
                    Classes.populate(result.getString("CourseID"),
                            id, result.getString("sectionNo"), result.getInt("year"), result.getString("semester"),
                            grades[(int) Math.floor(Math.random() * grades.length)]);
                    Student.setALL(id, Student.gen(false),
                            Student.gen(false), Student.gen(true), gender[(int) Math.floor(Math.random() * 3)]);
                    Student.setRecord();
                    id += 1;
                }
            }
            StudentsDatabase.AggregateGrades AGrades = db.new AggregateGrades(db.createAggregateGrades);

            button.setOnAction(e -> {
                try {
                    db.truncate_table("AggregateGrades");
                    AGrades.populate(input.getText());

                    ResultSet rs_AGrades = AGrades.pull();

                    String GradeList = "";
                    while (rs_AGrades.next()) {
                        for (int i = 0; i < rs_AGrades.getInt("studentcount"); i++)
                            GradeList += rs_AGrades.getString("grades");
                    }
                    gc.clearRect(0, 0, x, y);
                    HistogramAlphaBet chart = new HistogramAlphaBet(GradeList);
                    HistogramAlphaBet.MyPieChart pie =
                            chart.new MyPieChart(grades.length, 100, new MyPoint(x / 2, y / 2));
                    pie.draw(gc);
                } catch (SQLException temp) {
                    System.out.println("Failed to Connect to SQL SERVER");

                    temp.printStackTrace();
                }
            });
        } catch (SQLException e) {
            System.out.println("Failed to Connect to SQL SERVER");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
