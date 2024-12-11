function messageForm(button){
    const dato = JSON.parse(button.getAttribute('data-webSite'));
    let form = document.getElementById('webSite');
    let inputs = form.querySelectorAll('[name]');
    inputs.forEach(input => {
        switch(input.name){
            case "webSiteId":
                input.value=dato.id;
                break;
            case "webSiteNome":
                input.value=dato.nome;
                break;
            case "date":
                date=new Date();
                //Questa funzione viene da FormatDate.js
                input.value=formatDate(date);
                break;
        }
    });
}
async function updateWebSite(id) {
    let form = document.getElementById('formWebSite');
    let inputs = form.querySelectorAll('[name]');

    // Llamada a getUtenteById envuelta en try-catch
    const data = await getWebSiteById(id);

    // Si no se obtiene la respuesta, se lanza un error
    if (!data) {
        closeModal("modal2");
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
