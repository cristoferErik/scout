async function updateServiceForWebSite(id) {
    let form = document.getElementById('formServiceForWebSite');
    let inputs = form.querySelectorAll('[name]');

    // Llamada a getUtenteById envuelta en try-catch
    const data = await getServiceForWebSiteById(id);
    // Si no se obtiene la respuesta, se lanza un error
    if (!data) {
        closeModal("modal6");
        listWebSite();
        return;
    }

    // Llenar los campos del formulario con los datos del usuario
    inputs.forEach(function (input) {
        switch (input.name) {
            case 'id':
                input.value = data.id;
                break;
            case 'webSiteId':
                input.value = data.webSite.id;
                break;
            case 'servizioId':
                input.value = data.servizio.id;
                break;
            case 'dateIni':
                input.value = data.dateIni;
                break;
            case 'dateFine':
                input.value = data.dateFine;
                break;
            case 'periodo':
                input.value = data.periodo;
                break;
            case 'status':
                input.checked = data.status;
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

    let form = document.getElementById("deleteServiceForWebSite");
    let inputs = form.querySelectorAll('[name]');

    inputs.forEach(function (input) {
        switch (input.name) {
            case 'id':
                input.value = id;
                break;
        }
    });
}