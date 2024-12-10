document.addEventListener("DOMContentLoaded", function () {
    listUtente();
});
async function listUtente(url) {
    try {
        if (!url) {
            url = "/restUtente/utenti";
        }
        const response = await fetch(url);
        

        if (!response.ok) {
            let error = new Error();
            error.data = responseData.body;
            throw error;
        } else {
            const responseData = await response.json();
            createPagination(responseData, "pagination1");
            createDataTableT1(responseData.body);
        }

    } catch (error) {
        let message = `Error: ${error.data.status} - ${error.data.message}`;
        alert(message);
    }
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
                'X-CSRF-TOKEN': document.querySelector('[name="_csrf"]').value,
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
