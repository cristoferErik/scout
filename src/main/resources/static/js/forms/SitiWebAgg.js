function getData(button){

    // Obtener el <tr> que es el padre del botón
    var tr = button.closest('tr');
    // Hacer algo con el tr, por ejemplo, mostrar los datos de la fila
    var tds = tr.querySelectorAll('td');
    
    let idSitoWeb=`${tds[0].textContent.trim()}`;
    let nomeSitoWeb=`${tds[1].textContent.trim()}`;
    let data=new Date();
    
    document.getElementsByName('idSitoWeb')[0].value=idSitoWeb;
    document.getElementsByName('nomeWebSite')[0].value=nomeSitoWeb;
    document.getElementsByName('nomeWebSite')[1].value=nomeSitoWeb;
    document.getElementsByName('date')[0].value=formatDate(data);
    document.getElementsByName('date')[1].value=formatDate(data);
}