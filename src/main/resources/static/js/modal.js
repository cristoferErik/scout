function openModal(modalId) {
    //Qui se inserisce la stringa del modal
    let modal=document.getElementById(modalId);
    if(modal) modal.style.display="block";
}

function closeModal(modalId){
    let modal=document.getElementById(modalId);
    let inputs = modal.querySelectorAll('input, textarea, span');
    inputs.forEach(function(input) {
        input.value = '';  // pulire tutti gli inputs
        input.textContent='';
    });
     if(modal) modal.style.display="none";
}
