document.addEventListener("DOMContentLoaded", function () {
    listUtente();
});
function listUtente(){
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
function createDataTableT1(data) {
    const tableBody = document.getElementById("tbody1");
    tableBody.innerHTML = "";

    data.forEach(dato => {
        const row = document.createElement('tr');
        row.innerHTML =
            `
            <td>${dato.id}</td>
            <td>${dato.nome}</td>
            <td>${dato.cognome}</td>
            <td>${dato.indirizzo}</td>
            <td>${dato.telefono}</td>
            <td>${dato.email}</td>
            <td>
                <button type="button" class="button light-blue"
                    onclick="openModal('modal1'); updateUtente(${dato.id});">update</button>
            </td>
            <td>
                <button type="button" class="button check-color"
                    onclick="openModal('modal2'); checkUtente(${dato.id});">check</button>
            </td>
            <td>
                <button type="button" class="button danger-color"
                    onclick="openModal('modal3'); deleteFormUtente(${dato.id});"
                    >remove</button>
            </td>
        `;
        tableBody.appendChild(row);
    });
}

async function getUtenteById(id){
    try{
        const response = await fetch(`/restUtente/utente/${id}`);
        const responseData= await response.json();
        if (!response.ok) {
            let error =  new Error();
            error.data= responseData;
            throw error;
        }else{
            return responseData.data;
        }

    }catch(error){
        let message = `Error: ${error.data.status} - ${error.data.message}`;
        alert(message);
    }
}

async function saveUtente(utente){
    try{
        const response = await fetch('/restUtente/save',{
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(utente),
        });

        if(response.ok){
            const result = await response.json();
            const message=result.message;
            alert(message);
            closeModal("modal1");
            listUtente();
        }
    }catch(error){
        console.error('Errore nella richiesta!',error);
    }
}

async function deleteUtente(){
    let form = document.getElementById("formDeleteUtente");
    let inputs = form.querySelectorAll('[name]');
    let utente={};
    inputs.forEach((input)=>{
        utente[input.name]=input.value;
    }); 
    try{
        const response = await fetch(`/restUtente/utente/${utente.id}`,{
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
            },
        });

        if(response.ok){
            const result = await response.json();
            const message=result.message;
            alert(message);
            listUtente();
            closeModal("modal3");
        }
    }catch(error){
        console.error('Errore nella richiesta!',error);
    }
}
