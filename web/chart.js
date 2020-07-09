

//Формирует пустую оболочку для графика
var ctx = document.getElementById('chart_ages').getContext('2d');
var chart = new Chart(ctx, {
    // The type of chart we want to create
    type: 'bar',

    data: {
        labels: [],

        datasets: [{
            label: 'Возраст',
            backgroundColor: 'rgb(0, 99, 132)',
            borderColor: 'rgb(0, 99, 0)',
            data: []
        }]
    },
    // Configuration options go here
    options: {
    }
});

function addData() {
    //Отрезает пекрвый и последний элемент строки - [скобки]
    //Разбивает одну длинную строку на элементы массива, разделитель : ","
    fio_m = fio.slice(1,-1).split(',');
    ages_m = ages.slice(1,-1).split(',');

    //Добавляет в график значения фамилий и возраст
    for (i=0; i<dlina; i++) {

        chart.data.labels.push(fio_m[i]);
        chart.data.datasets[0].data.push(ages_m[i]);
    }
    chart.update();
}