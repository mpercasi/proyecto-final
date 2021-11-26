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

        // Guarda el numero de cliente para cualquier transacción
        sessionStorage.setItem("numCliente", response[0].numCliente);

    }).fail(function (jqXHR, textStatus, errorThrown){
        console.log(jqXHR.status);
        console.log(textStatus);
    });
}

function imprimirContenido(response){

    if(response[0].cuentas.length == 0){
        document.getElementById("contenidoPrincipal").innerHTML = '\
        <div id="imgSinDatos" class="d-flex justify-content-center flex-column rounded">\
            <h3 class="w-100 text-center mt-5 mb-3">Todavía no tienes una cuenta</h3>\
            <div class="d-flex justify-content-center mb-2">\
                <a class="btn btn-primary" data-toggle="modal" data-target="#formularioAbrirCuenta">¡Clic aquí para aperturar tu primera cuenta!</a>\
            </div>\
            <img src="img/no-data.jpg">\
        </div>';
    } else{
        
    }
}

function validarDatosCuenta(){
    const contenedorError = document.getElementById("contenedor-error-solicitud-cuenta");
    const mensajeError = document.getElementById("mensaje-error-solicitud-cuenta");
    let tipoCuenta;
    let montoApertura = document.getElementById("monto-apertura").value;
    let tiempoAhorro = document.getElementById("tiempo-ahorro").value;
    let datos;

    // Oculta el contenedor de error en caso de haberlo usado
    contenedorError.classList.add("d-none");

    if(document.getElementById("radio-cuenta-ahorro").checked){
        tipoCuenta = "Ahorro"
    } else if(document.getElementById("radio-cuenta-corriente").checked){
        tipoCuenta = "Corriente"
    } else{
        contenedorError.classList.remove("d-none");
        mensajeError.innerHTML = "Por favor, seleccione un tipo de cuenta...";
        return false;
    }

    if(montoApertura == ""){
        contenedorError.classList.remove("d-none");
        mensajeError.innerHTML = "Por favor, ingrese el monto de apertura";
        return false;
    }

    if(document.getElementById("radio-cuenta-ahorro").checked){
        if(tiempoAhorro == "Selecciona una opción..."){
            contenedorError.classList.remove("d-none");
            mensajeError.innerHTML = "Por favor, seleccione un plazo de ahorro";
            return false;
        }
    }

    datos = {
        "estado": "Aprovado",
        "monto": montoApertura,
        "tipoMoneda": "MXN",
        "tipo": tipoCuenta,
        "montoSonreGiro": (tipoCuenta == "Corriente" ? 1000.00 : 0),
        "mesesAhorro": (tipoCuenta == "Ahorro" ? tiempoAhorro : 0),
        "numCliente": sessionStorage.numCliente
    }

    crearCuenta(datos);
}

function crearCuenta(datos){
    let settings = {
        "url": "http://" + servidor + ":" + puerto_api_cuentas + "/cuentas",
        "method": "POST",
        "timeout": 0,
        "headers": {
            "Content-Type": "application/json"
          },
        "data": JSON.stringify(datos),
    };
        
    $.ajax(settings).done(function (response) {
        console.log(response)
        if(typeof response == "string"){
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Cuenta ya existente. Solo puedes tener una cuenta de cada tipo',
            })
        } else{
            Swal.fire({
                icon: 'success',
                title: '¡Proceso exitoso!',
                text: 'Ahora tienes una cuenta asociada',
            })

            // Oculta el modal y recarga la página
            $('#formularioAbrirCuenta').modal('hide');
            window.location.reload();

        }

    }).fail(function (jqXHR, textStatus, errorThrown){
        console.log(jqXHR.status);
        console.log(textStatus);

        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Cuenta ya existente. Solo puedes tener una cuenta de cada tipo',
        })
    });
}

// Evento checkbox modal usuario
document.getElementById("radio-cuenta-ahorro").addEventListener('change', () => {
    if(document.getElementById("radio-cuenta-ahorro").checked){
        document.getElementById("monto-ahorro-item").classList.remove("d-none");
        document.getElementById("monto-ahorro-item").classList.add("d-flex");
    }
    // Oculta el contenedor de error en caso de haberlo usado
    document.getElementById("contenedor-error-solicitud-cuenta").classList.add("d-none");
});

document.getElementById("radio-cuenta-corriente").addEventListener('change', () => {
    if(document.getElementById("radio-cuenta-corriente").checked){
        document.getElementById("monto-ahorro-item").classList.remove("d-flex");
        document.getElementById("monto-ahorro-item").classList.add("d-none");
    }
    // Oculta el contenedor de error en caso de haberlo usado
    document.getElementById("contenedor-error-solicitud-cuenta").classList.add("d-none");
});

// Al iniciar haz esto
$(document).ready(function () {
    consultarInfoUsuario();
});