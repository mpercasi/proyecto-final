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
    let c = 0;
    let d = 0;

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
                        <h2 class="text-white">$' + separator(response[0].cuentas[0].monto) + '</h2>\
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
                                <h2 class="text-white">$' + separator(response[0].cuentas[0].monto) + '</h2>\
                            </div>\
                        </div>\
                    </div>\
                    <div class="d-flex flex-column">\
                        <p>Cuenta de Ahorro:</p>\
                        <div class="w-100 d-flex justify-content-center">\
                            <div id="saldo-monto-cuenta-ahorro" class="d-flex justify-content-center align-items-center">\
                                <h2 class="text-white">$' + separator(response[0].cuentas[1].monto) + '</h2>\
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

    // Table - prestamos
    if(response[0].cuentas.length != 0){
        for(let i = 0; i <= response[0].cuentas.length; i++){
            if(response[0].cuentas[i].tipo == "Corriente"){
                document.getElementById("table-div").innerHTML = '\
                    <table id="table" class="display">\
                        <thead>\
                            <tr>\
                                <th>N.Préstamo</th>\
                                <th>N.Cliente</th>\
                                <th>Plazo</th>\
                                <th>Monto</th>\
                                <th>Cuota</th>\
                                <th>Interés</th>\
                                <th>Estatus</th>\
                                <th>Fecha límite de pago</th>\
                            </tr>\
                        </thead>\
                        <tbody>\
                            <tr>\
                                <td>' + response[0].cuentas[i].prestamos[0].numPrestamo + '</td>\
                                <td>' + response[0].numCliente + '</td>\
                                <td>' + response[0].cuentas[i].prestamos[0].plazoMeses + ' Meses</td>\
                                <td>$' + separator(response[0].cuentas[i].prestamos[0].monto) + '</td>\
                                <td>$' + separator(response[0].cuentas[i].prestamos[0].cuota) + '</td>\
                                <td>' + "10%" + '</td>\
                                <td>' + "Adeudado" + '</td>\
                                <td>' + "26/12/2021" + '</td>\
                            </tr>\
                        </tbody>\
                    </table>';
                $('#table').DataTable();
                c = 0;
            } else{
                c++;
            }
        }
    }

    if(c != 0){
        document.getElementById("table-div").innerHTML = '\
        <div class="w-100 text-center bg-warning rounded">\
            <h1 class="text-dark py-2">Sin préstamos vigentes</h1>\
        </div>';
    }

    console.log(c);

    // Tabla inversiones
    // if(response[0].cuentas.length != 0){
    //     for(let i = 0; i <= response[0].cuentas.length; i++){
    //         console.log(response[0].cuentas.length);
    //         if(response[0].cuentas[i].tipo == "Corriente"){
                
    //             for(let j = 0; j < response[0].cuentas[i].inversiones.length; j++){
    //                 document.getElementById("table-div-inversiones").innerHTML = '\
    //                 <table id="table-inversiones" class="display">\
    //                     <thead>\
    //                         <tr>\
    //                             <th>Fecha de vencimiento</th>\
    //                             <th>N.Cliente</th>\
    //                             <th>Monto</th>\
    //                             <th>Monto total</th>\
    //                             <th>Interés</th>\
    //                         </tr>\
    //                     </thead>\
    //                     <tbody>\
    //                         <tr>\
    //                             <td>' + response[0].cuentas[i].inversiones[j].fechaVencimiento + '</td>\
    //                             <td>' + response[0].numCliente + '</td>\
    //                             <td>$' + separator(response[0].cuentas[i].inversiones[j].plazo.monto) + '</td>\
    //                             <td>$' + separator(response[0].cuentas[i].inversiones[j].plazo.montoTotal) + '</td>\
    //                             <td>' + response[0].cuentas[i].inversiones[j].plazo.tasaInteres.tasa + '</td>\
    //                         </tr>\
    //                     </tbody>\
    //                 </table>';
    //             $('#table-inversiones').DataTable();
    //                 d = 0;
    //             }
    //         } else{
    //             d++;
    //         }
    //     }
    // }

    // if(d != 0){
    //     document.getElementById("table-div-inversiones").innerHTML = '\
    //     <div class="w-100 text-center bg-primary rounded">\
    //         <h1 class="text-dark py-2">Sin inversiones</h1>\
    //     </div>';
    // }

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

            setTimeout(() => {
                window.location.reload();
            }, 5000);

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