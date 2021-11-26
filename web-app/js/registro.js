function validarInfo(){
    const NOMBRE = document.getElementById("nombre-usuario").value;
    const APELLIDO = document.getElementById("apellido-usuario").value;
    const CORREO = document.getElementById("correo-usuario").value;
    const TELEFONO = document.getElementById("numero-celular-usuario").value;
    let edad = (document.getElementById("fecha-nacimiento-usuario").value != "" ? calcularEdad(document.getElementById("fecha-nacimiento-usuario").value) : "");
    const USUARIO = document.getElementById("username-usuario").value;
    const CONTRA = document.getElementById("contra-usuario").value;
    const CONFIRMA_CONTRA = document.getElementById("confirmar-contra-usuario").value;

    if(NOMBRE == ""){
        createToast('top-end', 'warning', 'Por favor, ingrese el su nombre');
        return false;
    }

    if(APELLIDO == ""){
        createToast('top-end', 'warning', 'Por favor, ingrese el su apellido');
        return false;
    }

    if(CORREO == ""){
        createToast('top-end', 'warning', 'Por favor, ingrese el su correo');
        return false;
    }

    if(TELEFONO == ""){
        createToast('top-end', 'warning', 'Por favor, ingrese el su teléfono');
        return false;
    } else if(TELEFONO.length < 10){
        createToast('top-end', 'warning', 'El número debe tener al menos 10 dígitos');
        return false;
    }

    if(edad == ""){
        createToast('top-end', 'warning', 'Por favor, ingrese el su fecha de nacimiento');
        return false;
    } else if(edad < 18){
        createToast('top-end', 'warning', 'Debe ser mayor de edad para poder solicitar su registro');
        return false;
    }

    if(USUARIO == ""){
        createToast('top-end', 'warning', 'Por favor, ingrese el su usuario');
        return false;
    } else if(USUARIO.length < 5){
        createToast('top-end', 'warning', 'El usuario debe tener al menos 5 caracteres');
        return false;
    }

    if(CONTRA == ""){
        createToast('top-end', 'warning', 'Por favor, ingrese el su contraseña');
        return false;
    } else if(!validarContra(CONTRA)){
        createToast('top-end', 'warning', 'La contraseña debe tener al menos un caracter alfa numerico y un número');
        return false;
    }

    if(CONTRA != CONFIRMA_CONTRA){
        createToast('top-end', 'warning', 'Las contraseñas son diferentes');
        return false;
    }

    let datosRegistro = {
        "nombre": NOMBRE,
        "apellido": APELLIDO,
        "edad": edad,
        "celular": TELEFONO,
        "correo": CORREO,
        "usuario": USUARIO,
        "contra": CONTRA,
        "status": "",
        "numCliente": generarNumeroCliente()
    }

    solicitarRegistro(datosRegistro);
}

function solicitarRegistro(datos){
    let settings = {
        "url": "http://" + servidor + ":" + puerto_api_usuarios + "/crearCliente",
        "method": "POST",
        "timeout": 0,
        "headers": {
            "Content-Type": "application/json"
          },
        "data": JSON.stringify(datos),
    };
        
        $.ajax(settings).done(function (response) {

            if(typeof response == "string"){
                createToast('top-end', 'warning', response);
            } else{
                Swal.fire({
                    title: '¡Proceso exitoso!',
                    icon: 'success',
                    text: 'Tu cuenta ha sido creada con exito, tu nuevo número de usuario es: ' + datos.numCliente,
                    confirmButtonText: 'Ir al login',
                }).then((result) => {
                    if (result.isConfirmed) {
                        window.location = "../login.html";    
                    } 
                })
            }

        }).fail(function (jqXHR, textStatus, errorThrown){
            console.log(jqXHR.status);
            console.log(textStatus);
        });
}

function calcularEdad(fecha){
    let hoy = new Date();
    let cumpleanos = new Date(fecha);
    let edad = hoy.getFullYear() - cumpleanos.getFullYear();
    let m = hoy.getMonth() - cumpleanos.getMonth();

    if (m < 0 || (m === 0 && hoy.getDate() < cumpleanos.getDate())) {
        edad--;
    }

    return edad;
}

function generarNumeroCliente(){
    return Math.floor(Math.random() * 90000) + 10000;
}

function validarContra(contra){
    let alfaNum = ['!','"','$','%','&','(',')','=','?','¿','¡','*','+','}','{','_','-',':','^','@', '.'];
    let nums = ['1','2','3','4','5','6','7','8','9','0'];
    let valAlfa = false;
    let valNum = false;

    //Valida alfa numericos
    for(let i = 0; i < alfaNum.length; i++){
        for(let j = 0; j < contra.length; j++){
            if(contra[j] == alfaNum[i]){
                valAlfa = true;
            }
        }
    }

    //Valida numeros
    for(let i = 0; i < nums.length; i++){
        for(let j = 0; j < contra.length; j++){
            if(contra[j] == nums[i]){
                valNum = true;
            }
        }
    }

    return valAlfa && valNum;
}