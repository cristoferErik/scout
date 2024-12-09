function fillAggSwForm(button){
    const dato = JSON.parse(button.getAttribute('data-aggSw'));
    let form = document.getElementById('aggSwForm');
    let inputs = form.querySelectorAll('[name]');
    inputs.forEach(input => {
        switch(input.name){
            case "sitoWebId":
                input.value=dato.id;
                break;
            case "nomeWebSite":
                input.value=dato.nomeWebSite;
                break;
            case "nomeServizio":
                input.value=dato.nomeServizio;
                break;
            case "date":
                date=new Date();
                //Questa funzione viene da FormatDate.js
                input.value=formatDate(date);
                break;
        }
    });
}