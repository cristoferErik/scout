function getDataServizio(button, modal, mode) {
    let m = document.getElementById(modal);
    let ids = m.querySelectorAll('[id]');
    if (mode === "add" && button != null) {
        // Obtener el <tr> que es el padre del botón
        var tr = button.closest('tr');
        // Hacer algo con el tr, por ejemplo, mostrar los datos de la fila
        var tds = tr.querySelectorAll('td');

        let idServizio = `${tds[0].textContent.trim()}`;
        let nome = `${tds[1].textContent.trim()}`;
        let costo = `${tds[2].textContent.trim()}`;
        let descrizione = `${tds[3].textContent.trim()}`;
        let dataCreazione =  `${tds[4].textContent.trim()}`;
        let dataAggiornamento =  `${tds[5].textContent.trim()}`;
        ids.forEach(function (input) {
            let name = input.getAttribute('id');
            switch (name) {
                case 'idServizio':
                    input.value = idServizio;
                    input.textContent = idServizio;
                    break;
                case 'nome':
                    input.value = nome;
                    input.textContent = nome;
                    break;
                case 'costo':
                    input.value = costo;
                    input.textContent = costo;
                    break;
                case 'descrizione':
                    input.value = descrizione;
                    input.textContent = descrizione;
                    break;
                case 'dataCreazione':
                    input.value = dataCreazione;
                    input.textContent = dataCreazione;
                    break;
                case 'dataAggiornamento':
                    input.value = dataAggiornamento;
                    input.textContent = dataAggiornamento;
                default:
                    break;
            }
        });

    } else {
        //cui si fa la pullizia in caso sia da registrare
        let inputs = m.querySelectorAll('input, textarea');
        inputs.forEach(function (input) {
            input.value = '';  // pulire tutti gli inputs
            input.textContent = '';
        });
    }
}