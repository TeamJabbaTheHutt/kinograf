import { renderAddMovie } from "./addMovie.js";
// import { renderEditReservation} from "./editReservation";
import {ADMIN_BASE_URL} from "./config.js";

document.addEventListener("DOMContentLoaded", initApp);

async function initApp() {
    const main = document.getElementById("admin-main");

    const navLinkHome = document.getElementById("navlink-Home")
    const navLinkAddMovie = document.getElementById("nav-link-add-movie");
   // const navLinkEditReservation = document.getElementById()

    navLinkHome.addEventListener("click", () =>  window.location.reload());
    navLinkAddMovie.addEventListener("click", () => renderAddMovie(main));


}