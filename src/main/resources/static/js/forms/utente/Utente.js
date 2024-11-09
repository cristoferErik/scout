function getDataUtente(button){

    // Obtener el <tr> que es el padre del botón
    var tr = button.closest('tr');
    // Hacer algo con el tr, por ejemplo, mostrar los datos de la fila
    var tds = tr.querySelectorAll('td');
    
    let id=`${tds[0].textContent.trim()}`;
    let nome=`${tds[1].textContent.trim()}`;
    let cognome=`${tds[2].textContent.trim()}`;
    let indirizzo=`${tds[3].textContent.trim()}`;
    let telefono=`${tds[4].textContent.trim()}`;
    let email=`${tds[5].textContent.trim()}`;
    
    document.getElementsByName('idSitoWeb')[0].value=idSitoWeb;
    document.getElementsByName('nomeWebSite')[0].value=nomeSitoWeb;
    document.getElementsByName('nomeWebSite')[1].value=nomeSitoWeb;
    document.getElementsByName('date')[0].value=formatDate(data);
    document.getElementsByName('date')[1].value=formatDate(data);
}