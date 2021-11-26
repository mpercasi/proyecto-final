function consultarInfoUsuario(){
    let settings = {
        "url": "http://" + servidor + ":" + puerto_api_usuarios + "/" + sessionStorage.usuario,
        "method": "GET",
        "timeout": 0,
    };

    $.ajax(settings).done(function (response) {
        // Coloca el nombre del usuario en el menú superior
        document.getElementById("nombreUsuario").innerHTML = response[0].nombre;

        imprimirContenido(response);

        llenarDatosPersonales(response);

    }).fail(function (jqXHR, textStatus, errorThrown){
        console.log(jqXHR.status);
        console.log(textStatus);
    });
}

function imprimirContenido(response){

    console.log(response)

    if(response[0].cuentas.length == 0){
        document.getElementById("cuentas").innerHTML = '\
        <div class="w-100 bg-white p-2 h-100 rounded">\
            <div id="imgSinDatos-perfil" class="d-flex justify-content-center flex-column">\
                <h3 class="w-100 text-center mt-5 mb-3">Todavía no tienes una cuenta</h3>\
                <div class="d-flex justify-content-center mb-2">\
                    <a class="btn btn-primary">¡Clic aquí para aperturar tu primera cuenta!</a>\
                </div>\
                <img src="../img/no-data.jpg">\
            </div>\
        </div>';
    } else if(response[0].cuentas.length == 1){
        document.getElementById("cuentas").innerHTML = '\
        <div class="w-100 bg-white p-5 rounded h-100 d-flex align-items-center flex-column">\
            <h3 class="text-dark mb-4 text-center">Detalles Cuenta</h3>\
            <div class="d-flex flex-column text-center justify-content-center w-50 m-auto">\
                <p>Saldo actual:</p>\
                <div class="w-100 d-flex justify-content-center">\
                    <div id="saldo-monto-cuenta" class="d-flex justify-content-center align-items-center">\
                        <h2 class="text-white">$' + response[0].cuentas[0].monto + '</h2>\
                    </div>\
                </div>\
            </div>\
            <div class="w-100 d-flex mt-4">\
                <div class="col-6 d-flex justify-content-center">\
                    <a class="btn btn-primary"><i class="fas fa-piggy-bank mr-2"></i>Depositar</a>\
                </div>\
                <div class="col-6 d-flex justify-content-center">\
                    <a class="btn btn-success"><i class="fas fa-hand-holding-usd mr-2"></i></i>Retirar</a>\
                </div>\
            </div>\
        </div>';
    } else{
        document.getElementById("cuentas").innerHTML = '\
        <div class="w-100 bg-white p-5 rounded h-100 d-flex align-items-center flex-column">\
            <h3 class="text-dark mb-4 text-center">Detalles Cuenta</h3>\
            <div class="d-flex flex-column text-center justify-content-center w-50 m-auto">\
                <div class="w-100 h-100 d-flex justify-content-center">\
                    <div class="d-flex flex-column mr-5">\
                        <p>Cuenta Corriente:</p>\
                        <div class="w-100 d-flex justify-content-center">\
                            <div id="saldo-monto-cuenta" class="d-flex justify-content-center align-items-center">\
                                <h2 class="text-white">$' + response[0].cuentas[0].monto + '</h2>\
                            </div>\
                        </div>\
                    </div>\
                    <div class="d-flex flex-column">\
                        <p>Cuenta de Ahorro:</p>\
                        <div class="w-100 d-flex justify-content-center">\
                            <div id="saldo-monto-cuenta-ahorro" class="d-flex justify-content-center align-items-center">\
                                <h2 class="text-white">$' + response[0].cuentas[1].monto + '</h2>\
                            </div>\
                        </div>\
                    </div>\
                </div>\
            </div>\
            <div class="w-100 d-flex mt-4">\
                <div class="col-6 d-flex justify-content-center">\
                    <a class="btn btn-warning"><i class="fas fa-piggy-bank mr-2"></i>Depositar</a>\
                </div>\
                <div class="col-6 d-flex justify-content-center">\
                    <a class="btn btn-success"><i class="fas fa-hand-holding-usd mr-2"></i></i>Retirar</a>\
                </div>\
            </div>\
        </div>';
    }
}

function llenarDatosPersonales(response){
    document.getElementById("nombre-usuario").value = response[0].nombre;
    document.getElementById("apellido-usuario").value = response[0].apellido;
    document.getElementById("correo-usuario").value = response[0].correo;
    document.getElementById("numero-celular-usuario").value = response[0].celular;
    document.getElementById("username-usuario").value = response[0].usuario;
    // document.getElementById("contra-usuario").value = response[0].nombre;
    // document.getElementById("confirmar-contra-usuario").value = response[0].nombre;
}

// Al iniciar haz esto
$(document).ready(function () {
    consultarInfoUsuario();
});