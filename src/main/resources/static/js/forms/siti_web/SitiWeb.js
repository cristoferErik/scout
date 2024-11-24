function getDataSitiWeb(button,modal,mode){
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
        let descrizione=`${tds[3].textContent.trim()}`;
        ids.forEach(function(input){
            let name = input.getAttribute('id');
            switch (name) {
                case 'idSW':
                    input.value = id;
                    input.textContent=id;
                    break;
                case 'nomeSW':
                    input.value = nome;
                    input.textContent=nome;
                    break;
                case 'urlSW':
                    input.value = url;
                    input.textContent=url;
                    break;
                case 'descrizioneSW':
                    input.value = descrizione;
                    input.textContent=descrizione;
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