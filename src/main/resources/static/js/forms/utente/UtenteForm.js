async function updateUtente(id) {
    let form = document.getElementById('formUtente');
    let inputs = form.querySelectorAll('[name]');

    // Llamada a getUtenteById envuelta en try-catch
    const data = await getUtenteById(id);

    // Si no se obtiene la respuesta, se lanza un error
    if (!data) {
        closeModal("modal1");
        listUtente();
        return;
    }

    // Llenar los campos del formulario con los datos del usuario
    inputs.forEach(function (input) {
        switch (input.name) {
            case 'id':
                input.value = data.id;
                break;
            case 'nome':
                input.value = data.nome;
                break;
            case 'cognome':
                input.value = data.cognome;
                break;
            case 'indirizzo':
                input.value = data.indirizzo;
                break;
            case 'telefono':
                input.value = data.telefono;
                break;
            case 'email':
                input.value = data.email;
                break;
            default:
                break;
        }
    });
}

async function checkUtente(id) {
    let form = document.getElementById('formCheckUtente');
    let inputs = form.querySelectorAll('[name]');

    const data = await getUtenteById(id);

    inputs.forEach(function (input) {
        switch (input.name) {
            case 'id':
                input.value = data.id;
                break;
            case 'nome':
                input.value = data.nome;
                break;
            case 'cognome':
                input.value = data.cognome;
                break;
            case 'indirizzo':
                input.value = data.indirizzo;
                break;
            case 'telefono':
                input.value = data.telefono;
                break;
            case 'email':
                input.value = data.email;
                break;
            default:
                break;
        }
    });
}

function deleteFormUtente(button) {
    const utenteData = button.dataset.utente;
    const utente = JSON.parse(utenteData);

    let form = document.getElementById("formDeleteUtente");
    let inputs = form.querySelectorAll('[name]');
    inputs.forEach(function (input) {
        switch (input.name) {
            case 'id':
                input.value = utente.id;
                break;
            case 'nome':
                input.value = utente.nome;
                break;
            case 'cognome':
                input.value = utente.cognome;
                break;
            default:
                break;
        }
    });
}

async function saveFormUtente(event){
    event.preventDefault();
    try{
        let form = document.getElementById("formUtente");
        let formData= new FormData(form);

        
        let utente={};
        formData.forEach((value,key)=> {
            utente[key]=value;
        });
        saveUtente(utente);
        closeModal("modal1");
    }catch(error){
        console.error('Ce stato un errore al salvare il dato', error);
    }
}
