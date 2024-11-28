
async function updateServizio(id) {
    let form = document.getElementById('formServizio');
    let inputs = form.querySelectorAll('[name]');

    // Llamada a getUtenteById envuelta en try-catch
    const servizio = await getServizioById(id);
        if (!servizio) {
            return;
        }
        inputs.forEach(function (input) {
            switch (input.name) {
                case 'id':
                    input.value = servizio.id;
                    break;
                case 'nome':
                    input.value = servizio.nome;
                    break;
                case 'costo':
                    input.value = servizio.costo;
                    break;
                case 'descrizione':
                    input.value = servizio.descrizione;
                    break;
                default:
                    break;
            }
        });
}

async function saveFormServizio(event){
    event.preventDefault();
    try{
        let form = document.getElementById("formServizio");
        let formData= new FormData(form);
        
        let servizio={};
        formData.forEach((value,key)=> {
            servizio[key]=value;
        });
        saveServizio(servizio);
        closeModal("modal1");
    }catch(error){
        console.error('Ce stato un errore al salvare il dato', error);
    }
}

function deleteFormServizio(button){
    const servizioData = button.dataset.servizio;
    const servizioJson = JSON.parse(servizioData);

    let form = document.getElementById("formDeleteServizio");
    let inputs = form.querySelectorAll('[name]');

    inputs.forEach(function (input) {
        switch (input.name) {
            case 'id':
                input.value = servizioJson.id;
                break;
            case 'nome':
                input.value = servizioJson.nome;
                break;
            default:
                break;
        }
    });
}

async function checkFormServizio(id) {
    let form = document.getElementById('checkServizio');
    let inputs = form.querySelectorAll('[name]');

    // Llamada a getUtenteById envuelta en try-catch
    const servizio = await getServizioById(id);
        if (!servizio) {
            return;
        }
        inputs.forEach(function (input) {
            switch (input.name) {
                case 'id':
                    input.value = servizio.id;
                    break;
                case 'nome':
                    input.value = servizio.nome;
                    break;
                case 'costo':
                    input.value = servizio.costo;
                    break;
                case 'descrizione':
                    input.value = servizio.descrizione;
                    break;
                case 'dataCreazione':
                    input.value = servizio.dataCreazione;
                    break;
                case 'dataAggiornamento':
                    input.value = servizio.dataAggiornamento;
                    break;
                default:
                    break;
            }
        });
}