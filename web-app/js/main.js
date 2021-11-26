let servidor = "localhost";

//API USUARIOS
let puerto_api_usuarios = "8080";

//API CUENTAS
// let puerto = "8081";

function generarToken(){
    sessionStorage.setItem("token", true);
}

// Toast
function createToast(pos, status, title){
    const Toast = Swal.mixin({
        toast: true,
        position: pos,
        showConfirmButton: false,
        timer: 3000,
        timerProgressBar: true,
        didOpen: (toast) => {
          toast.addEventListener('mouseenter', Swal.stopTimer)
          toast.addEventListener('mouseleave', Swal.resumeTimer)
        }
      })
      
      Toast.fire({
        icon: status,
        title: title
    })
}

function cerrarSesion(){
    sessionStorage.removeItem("token");
    window.location.reload();
}