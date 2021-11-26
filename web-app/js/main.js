let servidor = "localhost";

//API USUARIOS
let puerto_api_usuarios = "8080";

//API CUENTAS
let puerto_api_cuentas = "8082";

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

// Formateador de números
function separator(num) {
    let str = num.toString().split(".");
    str[0] = str[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    return str.join(".");
}

function cerrarSesion(){
    sessionStorage.removeItem("token");
    window.location.reload();
}