/*
Выбирает из БД возраст и Фамилии, сортирует по алфавиту и передает в chart.jsp
 */

package com.io;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/chart")
public class Chart extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dela", "user1", "123456");
             Statement stmt = connect.createStatement() ) {

            List <String> fio = new ArrayList <>();
            List <String> ages = new ArrayList <>();

            //Запрос списка фамилий из БД
            ResultSet rs = stmt.executeQuery( "SELECT * FROM pers_files.members order by fio ;" );

            while (rs.next()){
                ages.add(rs.getString("age"));
                fio.add(rs.getString("fio"));
            }

            //Передача списков в chart.jsp
            request.setAttribute("fio", fio);
            request.setAttribute("ages", ages);
            request.setAttribute("dlina", fio.size());

        } catch (SQLException ex){
            ex.printStackTrace();
        }

        response.setContentType("text/html;charset=UTF-8");
        getServletContext().getRequestDispatcher("/chart.jsp").forward(request, response);
    }
}
