async function saveFormHosting(event){
    event.preventDefault();
    try{
        let form = document.getElementById("formHosting");
        let formData= new FormData(form);
        
        let hosting={};
        formData.forEach((value,key)=> {
            hosting[key]=value;
        });
        saveHosting(hosting);
        closeModal("modal1");
    }catch(error){
        console.error('Ce stato un errore al salvare il dato', error);
    }
}

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
                case 'nomeHosting':
                    input.value = nome;
                    input.textContent=nome;
                    break;
                case 'urlHosting':
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
         let inputs = m.querySelectorAll('input, textarea');
         inputs.forEach(function (input) {
             input.value = '';  // pulire tutti gli inputs
             input.textContent = '';
         });
    }
    
}