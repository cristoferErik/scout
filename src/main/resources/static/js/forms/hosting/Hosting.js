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
            <td>${dato.netsonUrl}</td>
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

async function listHosting(url) {
    try {
        if (!url) {
            url = "/restHosting/hostingByUtente";
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
async function saveHosting(hosting){
    try{
        const response = await fetch('/restHosting/saveHosting',{
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'X-CSRF-TOKEN': document.querySelector('[name="_csrf"]').value,
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
                'X-CSRF-TOKEN': document.querySelector('[name="_csrf"]').value,
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


