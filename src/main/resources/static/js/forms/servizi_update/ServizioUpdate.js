document.addEventListener("DOMContentLoaded", function () {
    listServizio();
});
function listServizio(){
    fetch('/restServizio/servizi')
        .then(response => {
            if (!response.ok) {
                throw new Error("errore nella rete");
            }
            return response.json();
        })
        .then(data => {
            let tableElement =document.getElementById('table1');
            if (tableElement.DataTable) {
                let tableInstance= tableElement.DataTable;
                tableInstance.clear();  // Limpiar las filas actuales
                tableInstance.destroy();  // Destruir la instancia de DataTable
            }
            createDataTableT1(data);
            //$('#table1').DataTable(); // Inicializa DataTables
            let table = new DataTable('#table1',{});
        })
        .catch(error => {
            // Manejo de errores en caso de fallar la solicitud
            console.error('Hubo un problema con la solicitud AJAX:', error);
        });
}
function createDataTableT1(data) {
    const tableBody = document.getElementById("tbody1");
    tableBody.innerHTML="";

    data.forEach(dato => {
        const row = document.createElement('tr');
        row.innerHTML =
            `
            <input type="hidden" name="descrizzione" th:value="${dato.descrizione}">
                                <td>${dato.id}</td>
                                <td>${dato.nome}</td>
                                <td>${dato.costo}</td>
                                <td>${dato.descrizione}</td>
                                <td>${dato.dataCreazione}</td>
                                <td>${dato.dataAggiornamento}</td>
                                <td>
                                    <button type="button" class="button light-blue"
                                        onclick="openModal('modal1'); updateServizio(${dato.id});">update</button>
                                </td>
                                <td>
                                    <button type="button" class="button check-color"
                                        onclick="openModal('modal2'); checkFormServizio(${dato.id});">check</button>
                                </td>
                                <td>
                                    <button type="button" class="button danger-color"
                                        onclick="openModal('modal3'); deleteFormServizio(this);"
                                        data-servizio='${JSON.stringify(dato)}'>remove</button>
                                </td>
        `;
        tableBody.appendChild(row);
    });
}

async function saveServizio(servizio){
    try{
        const response = await fetch('/restServizio/saveServizio',{
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(servizio),
        });

        const result = await response.json();
        const message=result.message;
        alert(message);
        closeModal("modal1");
        listServizio();


    }catch(error){
        console.error('Errore nella richiesta!',error);
    }
}

async function getServizioById(id){
    try{
        const response = await fetch(`/restServizio/servizio/${id}`);
        const responseData= await response.json();
        if (responseData.status !== "success") {
            let error =  new Error();
            error.data = responseData;
            throw error;
        } else {
            // Retornar los datos si la respuesta es exitosa
            return responseData.body; // Aquí asumo que los datos de interés están bajo la clave 'body'
        }
    }catch(error){
        let message = `Error: ${error.data.status} - ${error.data.message}`;
        alert(message);
    }
}

async function deleteServizio(){
    let form = document.getElementById("formDeleteServizio");
    let formData= new FormData(form);
        
    let servizio={};
    formData.forEach((value,key)=> {
        servizio[key]=value;
    });
    try{
        const response = await fetch(`/restServizio/servizio/${servizio.id}`,{
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
            },
        });
        const responseData= await response.json();
        if (responseData.status !== "success") {
            let error =  new Error();
            error.data = responseData;
            throw error;
        } else {
            // Retornar los datos si la respuesta es exitosa
            alert(responseData.message); // Aquí asumo que los datos de interés están bajo la clave 'body'
            closeModal("modal3");
            listServizio();
        }

    }catch(error){
        let message = `Error: ${error.data.status} - ${error.data.message}`;
        alert(message);
    }
}