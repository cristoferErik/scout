function createPagination(data,idPagination) {
    let input = document.getElementById(idPagination);
    let links = data.pageLinks;

    let paginationHTML = ``;
    if(links.first){
        paginationHTML += `<div class="page-link item-icon" data-link="${links.first}">
                                <img class="icon" src="/images/pagination/angulo-doble-izquierda.svg" alt="">
                            </div>`;
    }else{
        paginationHTML += `<div class="item-icon opaque">
                                <img class="icon" src="/images/pagination/angulo-doble-izquierda.svg" alt="">
                            </div>`;
    }

    if (links.prev) {
        paginationHTML += `<div class="page-link item-icon" data-link="${links.prev}">
                                <img class="icon" src="/images/pagination/angulo-izquierdo.svg" alt="">
                            </div>`;
    }else{
        paginationHTML += `<div class="item-icon opaque">
                                <img class="icon" src="/images/pagination/angulo-izquierdo.svg" alt="">
                            </div>`;
    }

    // Agregar los números de página
    links.numbers.forEach(link => {
        if (link.actual) {
            // Si es la página actual, agregar el enlace con clase 'active'
            paginationHTML += `<div class="item-icon actual">${link.page}</div>`;
        } else {
            // Si no es la página actual, agregar el enlace normal
            paginationHTML += `<div class="page-link item-icon" data-link="${link.link}">${link.page}</div>`;
        }
    });

    if (links.next) {
        paginationHTML += `<div class="page-link item-icon" data-link="${links.next}">
                                <img class="icon" src="/images/pagination/angulo-derecho.svg" alt="">
                            </div>`;
    }else{
        paginationHTML += `<div class="item-icon opaque">
                                <img class="icon" src="/images/pagination/angulo-derecho.svg" alt="">
                            </div>`;
    }
    if(links.last){
        paginationHTML += `<div class="page-link item-icon" data-link="${links.last}">
                                <img class="icon" src="/images/pagination/angulo-doble-derecha.svg" alt="">
                            </div>`;
    }else{
        paginationHTML += `<div class="item-icon opaque">
                                <img class="icon" src="/images/pagination/angulo-doble-derecha.svg" alt="">
                            </div>`;
    }

    input.innerHTML = paginationHTML;
    addEventListenerToPages(input);
}