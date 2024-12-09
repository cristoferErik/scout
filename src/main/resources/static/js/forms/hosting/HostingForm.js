
async function updateHosting(id) {
    let form = document.getElementById('formHosting');
    let inputs = form.querySelectorAll('[name]');

    // Llamada a getUtenteById envuelta en try-catch
    const hosting = await getHostingById(id);
    if (!hosting) {
        return;
    }
    inputs.forEach(function (input) {
        switch (input.name) {
            case 'id':
                input.value = hosting.id;
                break;
            case 'nome':
                input.value = hosting.nome;
                break;
            case 'netsonUrl':
                input.value = hosting.netsonUrl;
                break;
            case 'url':
                input.value = hosting.url;
                break;
            case 'hUsername':
                input.value = hosting.hUsername;
                break;
            case 'hPassword':
                input.value = hosting.hPassword;
                break;
            default:
                break;
        }
    });
}

async function saveFormHosting(event) {
    event.preventDefault();
    try {
        let form = document.getElementById("formHosting");
        let formData = new FormData(form);

        let hosting = {};
        formData.forEach((value, key) => {
            hosting[key] = value;
        });
        saveHosting(hosting);
        closeModal("modal1");
    } catch (error) {
        console.error('Ce stato un errore al salvare il dato', error);
    }
}

function deleteFormHosting(id) {

    let form = document.getElementById("formDeleteHosting");
    let inputs = form.querySelectorAll('[name]');

    inputs.forEach(function (input) {
        switch (input.name) {
            case 'id':
                input.value = id;
                break;
        }
    });
}

async function checkFormHosting(id) {
    let form = document.getElementById('checkHosting');
    let inputs = form.querySelectorAll('[name]');

    // Llamada a getUtenteById envuelta en try-catch
    const hosting = await getHostingById(id);
    if (!hosting) {
        return;
    }
    inputs.forEach(function (input) {
        switch (input.name) {
            case 'id':
                input.value = hosting.id;
                break;
            case 'nome':
                input.value = hosting.nome;
                break;
            case 'netsonUrl':
                input.value = hosting.netsonUrl;
                break;
            case 'url':
                input.value = hosting.url;
                break;
            case 'hUsername':
                input.value = hosting.hUsername;
                break;
            case 'hPassword':
                input.value = hosting.hPassword;
                break;
            default:
                break;
        }
    });
}
