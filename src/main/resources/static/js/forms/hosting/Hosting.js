document.addEventListener("DOMContentLoaded", function () {
    listHosting();
});

function createDataTableT1(data) {
    const tableBody = document.getElementById("tbody1");
    tableBody.innerHTML = "";

    data.forEach(dato => {
        const row = document.createElement('tr');
        row.innerHTML =
            `
            <td>${dato.id}</td>
            <td>${dato.nome}</td>
            <td>${dato.url}</td>
            <td>${dato.hUsername}</td>
            <td>${dato.hPassword}</td>
            <td>
                <button type="button" class="button light-blue"
                    onclick="openModal('modal1'); updateHosting(${dato.id});">update</button>
            </td>
            <td>
                <button type="button" class="button check-color"
                    onclick="openModal('modal2'); checkFormHosting(${dato.id});">check</button>
            </td>
            <td>
                <button type="button" class="button danger-color"
                    onclick="openModal('modal3'); deleteFormHosting(${dato.id});">remove</button>
            </td>
        `;
        tableBody.appendChild(row);
    });
}

function listHosting(){
    let listHosting = document.getElementById('listHosting');
    let id = listHosting.querySelector('[name="utenteId"]').value;

    fetch(`/restHosting/hosting?id=${id}`)
        .then(response => {
            if (!response.ok) {
                throw new Error("errore nella rete");
            }
            return response.json();
        })
        .then(data => {
            //data=paginate(data);
            createDataTableT1(data);
            //$('#table1').DataTable(); // Inicializa DataTables
            let table = new DataTable('#table1', {});
        })
        .catch(error => {
            // Manejo de errores en caso de fallar la solicitud
            console.error('Hubo un problema con la solicitud AJAX:', error);
        });
}

async function saveHosting(hosting){
    let listHostingInputs = document.getElementById('listHosting');
    let utenteId = listHostingInputs.querySelector('[name="utenteId"]').value;
    if(utenteId){
        hosting["utenteId"]=utenteId
    }else{
        throw Error("Non c'è l'ID del utente");
    }

    try{
        const response = await fetch('/restHosting/saveHosting',{
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(hosting),
        });

        const result = await response.json();
        const message=result.message;
        alert(message);
        closeModal("modal1");
        listHosting();
    }catch(error){
        console.error('Errore nella richiesta!',error);
    }
}

async function getHostingById(id){
    try{
        const response = await fetch(`/restHosting/hosting/${id}`);
        const responseData= await response.json();
        if (responseData.status !== "success") {
            let error =  new Error();
            error.data = responseData;
            throw error;
        } else {
            // Retornar los datos si la respuesta es exitosa
            return responseData.body; // Restituiamo il corpo de la richiesta!
        }
    }catch(error){
        let message = `Error: ${error.data.status} - ${error.data.message}`;
        alert(message);
    }  
}

async function deleteHosting(){
    let form = document.getElementById("formDeleteHosting");
    let inputs = form.querySelectorAll('[name]');
  
    let hosting={};
    inputs.forEach((input)=> {
        hosting[input.name]=input.value;
    });
    try{
        const response = await fetch(`/restHosting/hosting/${hosting.id}`,{
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
            listHosting();
        }

    }catch(error){
        let message = `Error: ${error.data.status} - ${error.data.message}`;
        alert(message);
    }
}


