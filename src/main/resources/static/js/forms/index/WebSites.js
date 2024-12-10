document.addEventListener("DOMContentLoaded", function () {
    listWebSites();
});

async function listWebSites(url) {
    try {
        if (!url) {
            url = "/web_sites";
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
            <td>${dato.url}</td>
            <td>${dato.descrizione}</td>
            <td>${dato.dataAggiornamento}</td>
            <td>${dato.dataBackup}</td>
            
            <td>
                <button type="button" class="button light-blue"
                data-webSite='${JSON.stringify(dato)}'
                onclick="openModal('modal1'); messageForm(this);">Aggiornare</button>
            </td>
            <td>
                <button type="button" class="button gray-color"
                onclick="">-</button>
            </td>
        `;
        tableBody.appendChild(row);
    });
}

async function sendMessage(event){
    try{
        event.preventDefault();
        let form = document.getElementById("webSites");
        let formData= new FormData(form);
        
        let data={};
        formData.forEach((value,key)=> {
            data[key]=value;
        });

        const response = await fetch('/message',{
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'X-CSRF-TOKEN': document.querySelector('[name="_csrf"]').value,
            },
            body: JSON.stringify(data),
        });
        if(response.ok){
            const result = await response.json();
            const message=result.message;
            alert(message);
            closeModal("modal1");
        }
        listWebSites();
    }catch(error){
        console.error('Errore nella richiesta!',error);
    }
}
