// Создание базы данных Postgres, имя = Dela,  и заполнение её


package com.io;

import java.sql.*;

public class MakeDB {

    public static void main(String[] args) {

        // Создание БД Dela

        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "admin");
             Statement stmn = conn.createStatement() ) {

            stmn.execute("CREATE USER user1  PASSWORD '123456';");
            stmn.execute ("create database dela with owner user1;");

        } catch (SQLException ex){
            ex.printStackTrace();
        }

        // Подключение к БД Dela пользователем user1 и заполнение её данными
        /*
           Не нашел как можно создать схему в новой базе только одним соединением :(
           пишут:
           .. при создании схемы это может быть только база данных, к которой вы подключены.
           .. в отличие от баз данных, схемы не ограничивают доступ к данным:
         */

        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dela", "user1", "123456");
             Statement stmn = conn.createStatement() ) {

            stmn.addBatch("create schema pers_files ;");

            stmn.addBatch("create table pers_files.members\n" +
                    "(\n" +
                    "    num int,\n" +
                    "    fio varchar ,\n" +
                    "    age int\n" +
                    ");");
            stmn.addBatch("insert into pers_files.members (num, fio, age) VALUES\n" +
                    "        (1, 'Иванов В. П.', 33),\n" +
                    "        (2, 'Сидоров Н. Н.', 37),\n" +
                    "        (3, 'Петров В. В.', 22),\n" +
                    "        (4, 'Амарян А. К.', 43),\n" +
                    "        (5, 'Петров В. В.', 38);");

            stmn.addBatch("create table pers_files.home_adres\n" +
                    "(\n" +
                    "    num int,\n" +
                    "    adres varchar,\n" +
                    "    rayon varchar,\n" +
                    "    okrug varchar \n" +
                    ");");
            stmn.addBatch("insert into pers_files.home_adres (num ,adres , rayon, okrug) VALUES\n" +
                    "     (1,'Проектируемый проезд, дом 1', 'Москва', 'СВАО'),\n" +
                    "     (2,'Проектируемый проезд, дом 13', 'Москва', 'САО'),\n" +
                    "     (3,'Проектируемое шоссе, дом 77', 'Московский округ', 'ЮАО'),\n" +
                    "     (4,'Проектируемый проезд, дом 21', 'Москва', 'САО'),\n" +
                    "     (5,'Проектируемое шоссе, дом 77', 'Московский округ', 'ЮАО');");

            stmn.addBatch("create table pers_files.work_time\n" +
                    "(\n" +
                    "    num int,\n" +
                    "    start time,\n" +
                    "    finish time\n" +
                    ");");
            stmn.addBatch("insert into pers_files.work_time (num , start , finish) VALUES\n" +
                    "        (1, '08:00:00', '17:00:00'),\n" +
                    "        (2, '09:00:00', '18:00:00'),\n" +
                    "        (3, '09:00:00', '18:00:00'),\n" +
                    "        (4, '10:00:00', '20:00:00'),\n" +
                    "        (5, '09:00:00', '18:00:00');");

            stmn.executeBatch();
            stmn.clearBatch();
            System.out.println("Создана БД Dela");

        } catch (SQLException ex){
            ex.printStackTrace();
        }


    }
}
