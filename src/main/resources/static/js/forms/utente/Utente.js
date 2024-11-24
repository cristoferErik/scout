function getDataUtente(button,modal,mode){
    let m=document.getElementById(modal);
    let ids=m.querySelectorAll('[id]');
    if(mode==="add" && button!=null ){
        // Obtener el <tr> que es el padre del botón
        var tr = button.closest('tr');
        // Hacer algo con el tr, por ejemplo, mostrar los datos de la fila
        var tds = tr.querySelectorAll('td');
        
        let id=`${tds[0].textContent.trim()}`;
        let nome=`${tds[1].textContent.trim()}`;
        let cognome=`${tds[2].textContent.trim()}`;
        let indirizzo=`${tds[3].textContent.trim()}`;
        let telefono=`${tds[4].textContent.trim()}`;
        let email=`${tds[5].textContent.trim()}`;
        ids.forEach(function(input){
            let name = input.getAttribute('id');
            switch (name) {
                case 'idUtente':
                    input.value = id;
                    input.textContent=id;
                    break;
                case 'nomeUtente':
                    input.value = nome;
                    input.textContent=nome;
                    break;
                case 'cognome':
                    input.value = cognome;
                    input.textContent=cognome;
                    break;
                case 'indirizzo':
                    input.value = indirizzo;
                    input.textContent=indirizzo;
                    break;
                case 'telefono':
                    input.value = telefono;
                    input.textContent=telefono;
                    break;
                case 'email':
                    input.value = email;
                    input.textContent=email;
                    break;
                default:
                    break;
            }
        });
        
    }else{
        //cui si fa la pullizia in caso sia da registrare
        let inputs = m.querySelectorAll('input, textarea');
        inputs.forEach(function (input) {
            input.value = '';  // pulire tutti gli inputs
            input.textContent = '';
        });
    }
    
}