function messageForm(button){
    const dato = JSON.parse(button.getAttribute('data-webSite'));
    let form = document.getElementById('webSites');
    let inputs = form.querySelectorAll('[name]');
    inputs.forEach(input => {
        switch(input.name){
            case "webSiteId":
                input.value=dato.id;
                break;
            case "nome":
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