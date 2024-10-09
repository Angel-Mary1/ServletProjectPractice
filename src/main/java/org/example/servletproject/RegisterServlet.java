package org.example.servletproject;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private static final  String query="INSERT INTO BOOKDATA(BOOKNAME,BOOKEDITION,BOOKPRICE) VALUES(?,?,?)";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        doGet(req,res);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        //getThe writer
        PrintWriter writer = res.getWriter();
        res.setContentType("text/html");

        String bookName= req.getParameter("bookName");
        String bookPrice= req.getParameter("bookPrice");
        String bookEdition= req.getParameter("bookEdition");
        try{

            Class.forName("org.postgresql.Driver");

            Connection connection= DriverManager.getConnection("jdbc:postgresql://localhost:5432/ems","postgres", "postgres");

            PreparedStatement statement=connection.prepareStatement(query);
            statement.setString(1,bookName);
            statement.setString(2,bookPrice);
            statement.setString(3,bookEdition);

            int count =statement.executeUpdate();

        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        writer.println("<a href='home.html'>Home</a>");
        writer.println("<br>");
        writer.println("<a href='bookList'>Book List</a>");

    }

}