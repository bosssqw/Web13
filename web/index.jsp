<%--
Начальная страница.
Вывод списка сотрудников в виде таблицы после нажатия на гиперссылку ООО "Ромашка"
данные берет из сервлета Sotrudniki.java
При нажатии на фамилию сотрудника передает его ID как параметр в сервлет Options.java
Гиперссылка на скачивание отсортированного по алфавиту списка фамилий и ID в XLS
Гиперссылка на график Фамилиии, Возраст

--%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Список сотрудников</title>
</head>

<body>
Текущая дата: <%= new java.util.Date()%>  <br>
<p> <a href="/sotrudniki"><h1> ООО "Ромашка"  </h1></a> </p>



  <table border="2" cellspacing="5" cellpadding="8" >
        <thead>
            <tr>
                 <th> Сотрудников список </th>
            </tr>
        </thead>

        <tbody>
          <c:forEach var="user"  items="${fio}" >

             <tr>
                     <%-- При нажатии на фамилию сотрудника передает его ID как параметр в сервлет Options.java --%>
                 <td>  <a target="_blank" href="/options?num=${i=i+1;num.get(i-1)}" > ${user}</a>  </td>

            </tr>

          </c:forEach>

        </tbody>
  </table>

<%-- Гиперссылка на скачивание отсортированного по алфавиту списка фамилий и ID в XLS --%>
<br> <p> ${dxl} </p>
<%-- Гиперссылка на график Фамилиии, Возраст --%>
<br> <p> ${chart} </p>


</body>

</html>
