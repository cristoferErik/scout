document.addEventListener("DOMContentLoaded", function () {
    listUtente();
});
function listWebSite(){
    fetch('/restUtente/utenti')
    .then(response => {
        if (!response.ok) {
            throw new Error("errore nella rete");
        }
        return response.json();
    })
    .then(data => {
        //data=paginate(data);
        //console.log(data);
        
        let tableElement =document.getElementById('table1');
        if($.fn.DataTable.isDataTable(tableElement)){
            let tableInstance = $(tableElement).DataTable(); // Usar jQuery para acceder a DataTable
            tableInstance.clear();  // Limpiar las filas actuales
            tableInstance.destroy();  // Destruir la instancia de DataTable
        }
            createDataTableT1(data);
            let newTable = new DataTable(tableElement, {});
    })
    .catch(error => {
        // Manejo de errores en caso de fallar la solicitud
        console.error('Hubo un problema con la solicitud AJAX:', error);
    });
}

function getDataSitiWeb(button,modal,mode){
    let m=document.getElementById(modal);
    let ids=m.querySelectorAll('[id]');
    if(mode==="add" && button!=null ){
        // Ottenere il <tr> che è il padre del bottone
        var tr = button.closest('tr');
        // Ottenere tutti i td, per potere accedere ai suoi valori
        var tds = tr.querySelectorAll('td');
        
        let id=`${tds[0].textContent.trim()}`;
        let nome=`${tds[1].textContent.trim()}`;
        let url=`${tds[2].textContent.trim()}`;
        let descrizione=`${tds[3].textContent.trim()}`;
        ids.forEach(function(input){
            let name = input.getAttribute('id');
            switch (name) {
                case 'idSW':
                    input.value = id;
                    input.textContent=id;
                    break;
                case 'nomeSW':
                    input.value = nome;
                    input.textContent=nome;
                    break;
                case 'urlSW':
                    input.value = url;
                    input.textContent=url;
                    break;
                case 'descrizioneSW':
                    input.value = descrizione;
                    input.textContent=descrizione;
                    break;
                default:
                    break;
            }
        });
        
    }else{
         //cui si fa la pullizia in caso sia da registrare
         let inputs = m.querySelectorAll('input, textarea');
         inputs.forEach(function (input) {
             input.value = '';  // pulire tutti gli inputs
             input.textContent = '';
         });
    }
    
}