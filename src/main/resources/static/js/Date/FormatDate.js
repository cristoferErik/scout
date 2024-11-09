function formatDate(date){
    var year = date.getFullYear();
    var month = date.getMonth() + 1; // Los meses son base 0, por lo que sumamos 1
    var day = date.getDate();

    // Aseguramos que el mes y el día tengan dos dígitos (por ejemplo, 09 en vez de 9)
    if (month < 10) {
        month = '0' + month;
    }
    if (day < 10) {
        day = '0' + day;
    }

    // Devuelve la fecha formateada como yyyy-MM-dd
    return year + '-' + month + '-' + day;
}