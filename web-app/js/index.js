function consultarInfoUsuario(){
    let settings = {
        "url": "http://" + servidor + ":" + puerto_api_usuarios + "/" + sessionStorage.usuario,
        "method": "GET",
        "timeout": 0,
    };

    $.ajax(settings).done(function (response) {
        // Coloca el nombre del usuario en el menú superior
        document.getElementById("nombreUsuario").innerHTML = response[0].nombre;

        if(response[0].cuentas,length == 0){
            document.getElementById("contenidoPrincipal").innerHTML = '\
            <div id="imgSinDatos" class="d-flex justify-content-center flex-column rounded">\
                <h3 class="w-100 text-center mt-5 mb-3">Todavía no tienes una cuenta</h3>\
                <div class="d-flex justify-content-center mb-2">\
                    <a class="btn btn-primary" data-toggle="modal" data-target="#formularioAbrirCuenta">¡Clic aquí para aperturar tu primera cuenta!</a>\
                </div>\
                <img src="img/no-data.jpg">\
            </div>';
        }

        // Guarda el numero de cliente para cualquier transacción
        sessionStorage.setItem("numCliente", response[0].numCliente);

    }).fail(function (jqXHR, textStatus, errorThrown){
        console.log(jqXHR.status);
        console.log(textStatus);
    });
}

// Al iniciar haz esto
$(document).ready(function () {
    consultarInfoUsuario();
});