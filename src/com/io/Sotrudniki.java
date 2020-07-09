// Подсоединение к созданной БД
// Передача списка фамилий и уникального номера на страницу index.jsp

package com.io;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebServlet("/sotrudniki")
public class Sotrudniki extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException{

            //Объявление драйвера БД Postgresql
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

            //Создание соединения с БД dela
        try (Connection connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dela", "user1", "123456");
             Statement stmt = connect.createStatement() ) {

            List <String> fio = new ArrayList <>();
            List <String> num = new ArrayList <>();

            //Запрос списка фамилий из БД
            ResultSet rs = stmt.executeQuery( "SELECT * FROM pers_files.members order by fio;" );

            //Формирование списка фамилий
            while ( rs.next() ) {

                 num.add(rs.getString("num"));
                 fio.add(rs.getString("fio"));

            }

                 //Передача списка фамилий и уникального номера на страницу index.jsp
                 request.setAttribute("fio", fio);
                 request.setAttribute("num", num);

                 //Формирование гиперссылки на странице index.jsp  для скачивания файла в XLS
                 request.setAttribute("dxl", "<a href=\"/dxl?filtr=0\"> Нажмите для скачивания списка в формате XLS </a>");
                 //Формирование гиперссылки на странице index.jsp  для вывода графика
                 request.setAttribute("chart", "<a target=\"_blank\" href=\"/chart\"> Нажмите для построения графика </a>");


        } catch (SQLException ex){
            ex.printStackTrace();
        }

        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
}