<%--
Выводит график по событию onload
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>График</title>
</head>

<body onload="addData()" >

<%-- Получает данные из Chart.java и передает в chart.js
 Понять как нормально передать object в object (json?) ...
 --%>
<script type="text/javascript" src="external.js"></script>
<script type="text/javascript">

    var ages = '${ages}';
    var fio =   '${fio}';
    var dlina = '${dlina}';

</script>


        <div class="chart">
            <canvas id="chart_ages" width="10" height="2" ></canvas>
        </div>


<%-- Chart.js --%>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
<script src="chart.js"></script>


</body>
</html>
