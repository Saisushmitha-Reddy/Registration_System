package edu.ufl.ancha.registrationdblib;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegDBlib {
    static private Connection myconn;


    public RegDBlib() {
        try {
            myconn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/studentrecords",
                    "root",
                    "root"
            );
            System.out.println("Database connected successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> listEnrolled(String stuid, String semid) {
        List<String> resultList = new ArrayList<>();

        try {
            PreparedStatement ps = myconn.prepareStatement(
                    "SELECT e.crsid, e.semid, e.secid, c.classtimes " +
                            "FROM enrollment e JOIN class c ON e.crsid = c.crsid AND e.secid = c.secid AND e.semid = c.semid " +
                            "WHERE e.stuid=? AND e.semid=?");

            ps.setString(1, stuid);
            ps.setString(2, semid);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                resultList.add(
                        rs.getString("crsid") + ", " +
                                rs.getString("semid") + ", " +
                                rs.getString("secid") + ", " +
                                rs.getString("classtimes"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    public List<String> listAvailable(String crsid, String semid) {
        List<String> resultList = new ArrayList<String>();

        try {
            PreparedStatement ps = myconn.prepareStatement(
                    "SELECT secid, classtimes, (capacity-enrolled) as seats " +
                            "FROM class WHERE crsid=? AND semid=? AND capacity > enrolled");

            ps.setString(1, crsid);
            ps.setString(2, semid);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                resultList.add(rs.getString("secid") + ", " +
                        rs.getString("classtimes") + ", " +
                        rs.getInt("seats"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    public String enroll(String stuid, String crsid, String secid, String semid) {
        try {
            PreparedStatement ps = myconn.prepareStatement(
                    "INSERT INTO enrollment(stuid, crsid, secid, semid, edate) VALUES (?, ?, ?, ?, CURDATE())");

            ps.setString(1, stuid);
            ps.setString(2, crsid);
            ps.setString(3, secid);
            ps.setString(4, semid);
            ps.executeUpdate();

            return "Student " + stuid + " added to section " + secid + " of " + crsid + " for semester " + semid;
        } catch (SQLException e) {
            e.printStackTrace();
            return "Registration failed. Student " + stuid + " is not added to the classroll of " + crsid + " for section " + secid;
        }
    }


}

//    public static void main(String[] args) {
//        RegDBlib regDB = new RegDBlib();
//
//        String dummyStuId = "1";
//        String dummySemId = "S25";
//
//        List<String> enrolledCourses = regDB.listEnrolled(dummyStuId, dummySemId);
//
//        if (enrolledCourses.isEmpty()) {
//            System.out.println("No courses found for student ID " + dummyStuId + " in semester " + dummySemId);
//        } else {
//            System.out.println("Courses enrolled by student " + dummyStuId + " in semester " + dummySemId + ":");
//            for (String course : enrolledCourses) {
//                System.out.println(course);
//            }
//        }
//    }


