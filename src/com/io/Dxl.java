// Сохранение списка фамилий в sotrudniki.xls

package com.io;


import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;


@WebServlet("/dxl")
public class Dxl extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {


        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // создание  excel файла в памяти
        HSSFWorkbook xls = new HSSFWorkbook();
        HSSFSheet sheet = xls.createSheet("Сотрудники");

        try (Connection connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dela", "user1", "123456");
             Statement stmt = connect.createStatement() ) {

            //Запрос списка фамилий из БД
            ResultSet rs = stmt.executeQuery( "SELECT * FROM pers_files.members order by fio ;" );


            //Заполнение заголовков xls файла
            Row str1 = sheet.createRow(0);
            str1.createCell(0).setCellValue("ID");
            Row str2 = sheet.createRow(1);
            str2.createCell(0).setCellValue("ФИО");
            int k = 0;

            //Заполнение xls файла
            while ( rs.next() ) {
                k++;
                str1.createCell(k).setCellValue(rs.getInt("num"));
                str2.createCell(k).setCellValue(rs.getString("fio"));
            }

        } catch (SQLException ex){
            ex.printStackTrace();
        }

        //Настройка записи
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=sotrudniki.xls");

        //Запись в файл xls

         xls.write(response.getOutputStream());

    }
}
