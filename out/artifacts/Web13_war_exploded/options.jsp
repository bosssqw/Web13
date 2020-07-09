<%--
Выводит данные одного сотрудника, выбранного на странице index.jsp
данные берет из сервлета Options.java
--%>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>${name}</title>
</head>

<body>
Текущая дата: <%= new java.util.Date()%>  <br>
<p> <a href="/sotrudniki"><h1> ООО "Ромашка"  </h1></a> </p>


  <table border="2" cellspacing="5" cellpadding="8" >
    <thead>
       <tr>
           <th> Данные сотрудника </th>
           <td> Номер ID ${id} </td>
       </tr>

    </thead>

    <tbody>
      <tr>
          <th> ФИО </th>
          <td>  ${name}  </td>
      </tr>
      <tr>
          <th> Возраст </th>
          <td>  ${age}  </td>
      </tr>
      <tr>
          <th> Адрес </th>
          <td>  ${rayon}, ${okrug}, ${adres}  </td>
      </tr>
      <tr>
          <th> Режим работы </th>
          <td>  С ${start} до ${finish}  </td>
      </tr>

    </tbody>

  </table>

</body>

</html>
