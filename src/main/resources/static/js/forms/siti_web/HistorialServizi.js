function addListWebSiteByService(){
    listWebSiteByService();
}

async function listWebSiteByService(url){
    try {
        let form = document.getElementById('formCheckWebSite');
        let webSiteId = form.querySelector('[name=webSiteId]').value;

        if(!url){
            url=`restWebSite/historial?webSiteId=${webSiteId}`;
        }else{
            url+=`&webSiteId=${webSiteId}`;
        }
        
        const response = await fetch(url);
        if (!response.ok) {
            throw new Error('Errore nella risposta del server');
        }
        const responseData = await response.json();
        if(responseData.status==="success"){
            createPagination(responseData,"pagination1");
            createDataTableT2(responseData.body);
        }else{
            let error = new Error();
            error.data = responseData;
            throw error;
        }
    } catch (error) {
        let message = "Error desconocido";
        if (error.data) {
            message = `Error: ${error.data.status} - ${error.data.message}`;
        } else if (error.message) {
            message = `Error: ${error.message}`;
        }
        alert(message);
    }
}
function createDataTableT2(data) {
    const tableBody = document.getElementById("tbody2");
    tableBody.innerHTML="";
    data.forEach(dato => {
        const row = document.createElement('tr');
        row.innerHTML =
            `
            <td>${dato.id}</td>
            <td>${dato.nomeServizio}</td>
            <td>${dato.dateIni}</td>
            <td>${dato.dateFine}</td>
            <td>${dato.periodo}</td>
            <td>${dato.status}</td>
            <td>${dato.descrizione}</td>
            <td>
                <button type="button" class="button light-blue"
                    onclick="openModal('modal6'); updateServiceForWebSite(${dato.id});">update</button>
            </td>
            <td>
                <button type="button" class="button check-color"
                    onclick="openModal('modal2'); checkStorico(${dato.id});">check</button>
            </td>
            <td>
                <button type="button" class="button danger-color"
                    onclick="openModal('modal7'); deleteWebSiteForm(${dato.id});"
                    >remove</button>
            </td>
        `;
        tableBody.appendChild(row);
    });
}

async function getServiceForWebSiteById(id) {
    try {
        const response = await fetch(`/restWebSite/historial/${id}`);
        const responseData = await response.json();
        if (!response.ok) {
            let error = new Error();
            error.data = responseData;
            throw error;
        } else {
            return responseData.body;
        }

    } catch (error) {
        let message = `Error: ${error.data.status} - ${error.data.message}`;
        alert(message);
    }
}

async function deleteServiceForWebSite() {
    let form = document.getElementById("deleteServiceForWebSite");
    let inputs = form.querySelectorAll('[name]');

    let detail = {};
    inputs.forEach((input) => {
        detail[input.name] = input.value;
    });

    try {
        const response = await fetch(`/restWebSite/historial/${detail.id}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
            },
        });

        if (response.ok) {
            const result = await response.json();
            const message = result.message;
            alert(message);
            listWebSiteByService();
            closeModal("modal7");
        }
    } catch (error) {
        console.error('Errore nella richiesta!', error);
    }
}

async function saveServizioForWebSite() {
    try {
        let form = document.getElementById("formServiceForWebSite");
        let inputs = form.querySelectorAll('[name]');

        let servizio = {};
        inputs.forEach((input) => {
            servizio[input.name] = input.value;
        });

        const response = await fetch('/restServizio/serviziForWebSite', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(servizio),
        });

        if (response.ok) {
            const result = await response.json();
            const message = result.message;
            if(result.status==="success"){
                closeModal("modal6");
            }
            alert(message);
            listWebSiteByService();
        }
    } catch (error) {
        console.error("Error fetching data:", error);
    }

}