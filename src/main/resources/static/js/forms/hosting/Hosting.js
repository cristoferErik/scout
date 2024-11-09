function getDataHosting(button,modal,mode){
    let m=document.getElementById(modal);
    let ids=m.querySelectorAll('[id]');
    if(mode==="add" && button!=null ){
        // Ottenere il <tr> che è il padre del bottone
        var tr = button.closest('tr');
        // Ottenere tutti i td, per potere accedere ai suoi valori
        var tds = tr.querySelectorAll('td');
        
        let id=`${tds[0].textContent.trim()}`;
        let nome=`${tds[1].textContent.trim()}`;
        let url=`${tds[2].textContent.trim()}`;
        let hUsername=`${tds[3].textContent.trim()}`;
        let hPassword=`${tds[4].textContent.trim()}`;
        ids.forEach(function(input){
            let name = input.getAttribute('id');
            switch (name) {
                case 'idHosting':
                    input.value = id;
                    input.textContent=id;
                    break;
                case 'nome':
                    input.value = nome;
                    input.textContent=nome;
                    break;
                case 'url':
                    input.value = url;
                    input.textContent=url;
                    break;
                case 'hUsername':
                    input.value = hUsername;
                    input.textContent=hUsername;
                    break;
                case 'hPassword':
                    input.value = hPassword;
                    input.textContent=hPassword;
                    break;
                default:
                    break;
            }
        });
        
    }else{
        //cui si fa la pullizia in caso sia da registrare
        ids.forEach(function(input){
            let name = input.getAttribute('id');
            switch (name) {
                case 'idUtente':
                    input.value = "";
                    break;
                case 'nomeUtente':
                    input.value = "";
                    break;
                case 'cognome':
                    input.value = "";
                    break;
                case 'indirizzo':
                    input.value = "";
                    break;
                case 'telefono':
                    input.value = "";
                    break;
                case 'email':
                    input.value = "";
                    break;
                default:
                    break;
            }
        });
    }
    
}