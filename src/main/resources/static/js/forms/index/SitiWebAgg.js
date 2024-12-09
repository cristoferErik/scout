document.addEventListener("DOMContentLoaded", function () {
    listWebSiteByService();
});

async function listWebSiteByService(url) {
    try {
        if (!url) {
            url = "/restHome/webSiteToUpdate";
        }
        const response = await fetch(url);
        const responseData = await response.json();

        if (!response.ok) {
            let error = new Error();
            error.data = responseData.body;
            throw error;
        } else {
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
            <td>${dato.nomeWebSite}</td>
            <td>${dato.prossimoAgg}</td>
            <td>${dato.nomeServizio}</td>
            <td>
                <button type="button" class="button light-blue"
                data-aggSw='${JSON.stringify(dato)}'
                onclick="openModal('modal1'); fillAggSwForm(this);">Aggiornare</button>
            </td>
            <td>
                <button type="button" class="button gray-color"
                onclick="">-</button>
            </td>
        `;
        tableBody.appendChild(row);
    });
}

async function saveDescrizioneAgg(event){
    try{
        event.preventDefault();
        let form = document.getElementById("aggSwForm");
        let formData= new FormData(form);
        
        let data={};
        formData.forEach((value,key)=> {
            data[key]=value;
        });

        const response = await fetch('/restHome/webSiteUpdated',{
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        });
        if(response.ok){
            const result = await response.json();
            const message=result.message;
            alert(message);
            closeModal("modal1");
        }
        listWebSiteByService();
    }catch(error){
        console.error('Errore nella richiesta!',error);
    }
}
