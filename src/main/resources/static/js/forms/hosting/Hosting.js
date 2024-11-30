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
                    onclick="openModal('modal1'); getDataHosting(this,'modal1','add');">update</button>
            </td>
            <td>
                <button type="button" class="button check-color"
                    onclick="openModal('modal2'); getDataHosting(this,'modal2','add');">check</button>
            </td>
            <td>
                <button type="button" class="button danger-color"
                    onclick="openModal('modal3'); getDataHosting(this,'modal3','add');">remove</button>
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




