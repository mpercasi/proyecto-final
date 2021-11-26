const tarjeta = document.getElementById("tarjeta");
const botonGirar = document.getElementById("btn-girar");

botonGirar.addEventListener("click", () => {
    tarjeta.classList.toggle("active");
});