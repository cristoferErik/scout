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
            //data=paginate(data);
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
            <input type="hidden" name="descrizzione" th:value="${dato.descrizzione}">
                                <td>${dato.id}</td>
                                <td>${dato.nome}</td>
                                <td>${dato.costo}</td>
                                <td>${dato.descrizzione}</td>
                                <td>${dato.dataCreazione}</td>
                                <td>${dato.dataAggiornamento}</td>
                                <td>
                                    <button type="button" class="button light-blue"
                                        onclick="openModal('modal1'); updateServizio(${dato.id}');">update</button>
                                </td>
                                <td>
                                    <button type="button" class="button check-color"
                                        onclick="openModal('modal2'); getDataServizio(this,'modal2','add');">check</button>
                                </td>
                                <td>
                                    <button type="button" class="button danger-color"
                                        onclick="openModal('modal3'); getDataServizio(this,'modal3','add');">remove</button>
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
        listUtente();
        

    }catch(error){
        console.error('Errore nella richiesta!',error);
    }
}
