
document.addEventListener("DOMContentLoaded", function () {
    fetch('/restHome/webSiteToUpdate')
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
});

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
