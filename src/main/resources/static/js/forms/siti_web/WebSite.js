document.addEventListener("DOMContentLoaded", function () {
    listWebSite();
});

async function listWebSite(url) {
    try {
        if (!url) {
            url = "/restWebSite/webSitesByhosting";
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
            <td>
                <button type="button" class="button light-blue"
                    onclick="openModal('modal1'); updateWebSite(${dato.id});">update</button>
            </td>
            <td>
                <button type="button" class="button check-color"
                    onclick="openModal('modal2'); checkWebSite(${dato.id});">check</button>
            </td>
            <td>
                <button type="button" class="button danger-color"
                    onclick="openModal('modal3'); deleteWebSiteForm(${dato.id});">remove</button>
            </td>
        `;
        tableBody.appendChild(row);
    });
}

async function getWebSiteById(id) {
    try {
        const response = await fetch(`/restWebSite/webSite/${id}`);
        if (!response.ok) {
            let error = new Error();
            error.data = responseData;
            throw error;
        } else {
            const responseData = await response.json();
            return responseData.body;
        }

    } catch (error) {
        let message = `Error: ${error.data.status} - ${error.data.message}`;
        alert(message);
    }
}

async function saveWebSite(sitoWeb) {
    console.log(JSON.stringify(sitoWeb));
    try {
        const response = await fetch('/restWebSite/saveWebSite', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'X-CSRF-TOKEN': document.querySelector('[name="_csrf"]').value,
            },
            body: JSON.stringify(sitoWeb),
        });

        if (response.ok) {
            const result = await response.json();
            const message = result.message;
            alert(message);
            closeModal("modal1");
            listWebSite();
        }
    } catch (error) {
        console.error('Errore nella richiesta!', error);
    }
}

async function deleteWebSite() {
    let form = document.getElementById("formDeleteWebSite");
    let inputs = form.querySelectorAll('[name]');

    let sitoWeb = {};
    inputs.forEach((input) => {
        sitoWeb[input.name] = input.value;
    });

    try {
        const response = await fetch(`/restWebSite/webSite/${sitoWeb.id}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                'X-CSRF-TOKEN': document.querySelector('[name="_csrf"]').value,
            },
        });

        if (response.ok) {
            const result = await response.json();
            const message = result.message;
            alert(message);
            listWebSite();
            closeModal("modal3");
        }
    } catch (error) {
        console.error('Errore nella richiesta!', error);
    }
}

async function saveServizioForWebSite() {
    try {
        let form = document.getElementById("formAnaServizio");
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
                closeModal("modal4");
            }
            alert(message);
            listWebSite();
        }
    } catch (error) {
        console.error("Error fetching data:", error);
    }

}
async function saveAnaServizio() {
    try {
        let form = document.getElementById("formAnaServizio");
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
            listWebSite();
        }
    } catch (error) {
        console.error("Error fetching data:", error);
    }

}