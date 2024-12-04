document.addEventListener("DOMContentLoaded", function () {
    listWebSiteByService();
});

async function listWebSiteByService(){
    try {
        const response = await fetch(`/restHome/webSiteToUpdate`);
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

function createDataTableT1(data) {
    const tableBody = document.getElementById("tbody1");
    tableBody.innerHTML="";

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
                onclick="openModal('modal1'); getData(this);">Aggiornare</button>
            </td>
        `;
        tableBody.appendChild(row);
    });
}
