
async function updateWebSite(id) {
    let form = document.getElementById('formWebSite');
    let inputs = form.querySelectorAll('[name]');

    // Llamada a getUtenteById envuelta en try-catch
    const data = await getWebSiteById(id);

    // Si no se obtiene la respuesta, se lanza un error
    if (!data) {
        closeModal("modal1");
        listWebSite();
        return;
    }

    // Llenar los campos del formulario con los datos del usuario
    inputs.forEach(function (input) {
        switch (input.name) {
            case 'webSiteId':
                input.value = data.id;
                break;
            case 'nome':
                input.value = data.nome;
                break;
            case 'url':
                input.value = data.url;
                break;
            case 'dataAggiornamento':
                input.value = data.dataAggiornamento;
                break;
            case 'dataBackup':
                input.value = data.dataBackup;
                break;
            case 'descrizione':
                input.value = data.descrizione;
                break;
            default:
                break;
        }
    });
}

async function checkWebSite(id) {
    let form = document.getElementById('formCheckWebSite');
    let inputs = form.querySelectorAll('[name]');

    // Llamada a getUtenteById envuelta en try-catch
    const data = await getWebSiteById(id);

    // Si no se obtiene la respuesta, se lanza un error
    if (!data) {
        closeModal("modal1");
        listWebSite();
        return;
    }
    // Llenar los campos del formulario con los datos del usuario
    inputs.forEach(function (input) {
        switch (input.name) {
            case 'webSiteId':
                input.value = data.id;
                break;
            case 'nome':
                input.value = data.nome;
                break;
            case 'url':
                input.value = data.url;
                break;
            case 'dataAggiornamento':
                input.value = data.dataAggiornamento;
                break;
            case 'dataBackup':
                input.value = data.dataBackup;
                break;
            case 'descrizione':
                input.value = data.descrizione;
                break;
            default:
                break;
        }
    });
}

function deleteWebSiteForm(id) {

    let form = document.getElementById("formDeleteWebSite");
    let inputs = form.querySelectorAll('[name]');

    inputs.forEach(function (input) {
        switch (input.name) {
            case 'id':
                input.value = id;
                break;
        }
    });
}

async function saveWebSiteForm(event) {
    event.preventDefault();
    try {
        let form = document.getElementById("formWebSite");
        let formData = new FormData(form);

        let webSite = {};
        formData.forEach((value, key) => {
            webSite[key] = value;
        });

        saveWebSite(webSite);
        closeModal("modal1");
    } catch (error) {
        console.error('Ce stato un errore al salvare il dato', error);
    }
}

//Questo permette aggiungere l'id del webSite dentro del input hidden
function anagraficaServizioForm(id) {
    let form = document.getElementById("formAnaServizio");
    let inputWebSiteId = form.querySelector('[name="webSiteId"]');
    inputWebSiteId.value = id;
}

