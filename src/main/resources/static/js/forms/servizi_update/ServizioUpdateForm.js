
async function updateServizio(id) {
    let form = document.getElementById('formServizio');
    let inputs = form.querySelectorAll('[name]');

    // Llamada a getUtenteById envuelta en try-catch
    const data = await getUtenteById(id);
        if (!data) {
            closeModal("modal1");
            listServizio();
            return;
        }
        inputs.forEach(function (input) {
            switch (input.name) {
                case 'id':
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