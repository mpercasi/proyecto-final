function consultarInfoUsuario(){
    let settings = {
        "url": "http://" + servidor + ":" + puerto_api_usuarios + "/" + sessionStorage.usuario,
        "method": "GET",
        "timeout": 0,
    };

    $.ajax(settings).done(function (response) {
        // Coloca el nombre del usuario en el menú superior
        document.getElementById("nombreUsuario").innerHTML = response[0].nombre;

    }).fail(function (jqXHR, textStatus, errorThrown){
        console.log(jqXHR.status);
        console.log(textStatus);
    });
}

// Al iniciar haz esto
$(document).ready(function () {
    consultarInfoUsuario();
});