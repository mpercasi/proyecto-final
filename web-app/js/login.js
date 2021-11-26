function iniciarSesion(){
    let usuario = document.getElementById("usuario-cliente").value;
    let contrasena = document.getElementById("contra-cliente").value;

    if(usuario == ""){
        createToast('top-end', 'warning', 'Por favor, ingrese el su usuario');
        return false;
    }

    if(contrasena == ""){
        createToast('top-end', 'warning', 'Por favor, ingrese el su contraseña');
        return false;
    }

    let settings = {
        "url": "http://" + servidor + ":" + puerto_api_usuarios + "/cliente/" + usuario + "/" + contrasena,
        "method": "GET",
        "timeout": 0,
    };
      
    $.ajax(settings).done(function (response) {
        if(response == "Usuario correcto"){
            generarToken();
            sessionStorage.setItem("usuario", usuario);
            window.location = "index.html";
        }
    }).fail(function (jqXHR, textStatus, errorThrown){
        console.log(jqXHR.status);
        console.log(textStatus);
    });
}

// Al iniciar haz esto
// $(document).ready(function () {
//   iniciarSesion();
// });