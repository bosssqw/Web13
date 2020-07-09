// Получение id как параметра.
// Подсоединение к созданной БД Dela
// Получение остальных данных для этого сторудника
// Передача всех данных сотрудника в Options.jsp

package com.io;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;


@WebServlet("/options")
public class Options extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
           
            request.setCharacterEncoding("UTF8");

        //Создание соединения с БД
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dela", "user1", "123456");) {

            int id = 0;

            String sql_all = "select * from pers_files.home_adres, pers_files.work_time , pers_files.members" +
                    " where members.num = ? and work_time.num = ? and home_adres.num = ?";

                String num = request.getParameter("num");
                id = Integer.parseInt(num);

            //Запрос на поиск данных на сотрудника по его уникальному id в таблицах members, work_time, home_adres
            try (PreparedStatement statement = conn.prepareStatement(sql_all)) {

                statement.setInt(1, id);
                statement.setInt(2, id);
                statement.setInt(3, id);

                ResultSet rs = statement.executeQuery();

                while (rs.next()) {

                    String name = rs.getString("fio");
                    int age = rs.getInt("age");
                    String rayon = rs.getString("rayon");
                    String okrug = rs.getString("okrug");
                    String adres = rs.getString("adres");
                    Time start = rs.getTime("start");
                    Time finish = rs.getTime("finish");

                    //Передача данных о сотруднике в options.jsp
                    request.setAttribute("name", name);
                    request.setAttribute("age", age);
                    request.setAttribute("rayon", rayon);
                    request.setAttribute("okrug", okrug);
                    request.setAttribute("adres", adres);
                    request.setAttribute("start", start);
                    request.setAttribute("finish", finish);
                    request.setAttribute("id", id);

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException ex){
            ex.printStackTrace();
        }

        response.setContentType("text/html;charset=UTF-8");
        getServletContext().getRequestDispatcher("/options.jsp").forward(request, response);
    }
}