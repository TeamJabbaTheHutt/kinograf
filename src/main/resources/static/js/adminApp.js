import { renderAddMovie } from "./addMovie.js";
// import { renderEditReservation} from "./editReservation";
import {ADMIN_BASE_URL} from "./config.js";
import { renderAddShowtime } from "./addShowTime.js";
document.addEventListener("DOMContentLoaded", initApp);

async function initApp() {
    const main = document.getElementById("admin-main");

    const navLinkHome = document.getElementById("navlink-Home")
    const navLinkAddMovie = document.getElementById("nav-link-add-movie");
   // const navLinkEditReservation = document.getElementById()
    const navLinkAddShowtime = document.getElementById("nav-link-add-showtime");
    const btnAddShowtime = document.getElementById("btn-add-showtime");

    btnAddShowtime.addEventListener("click", () => renderAddShowtime(main));
    navLinkAddShowtime.addEventListener("click", () => renderAddShowtime(main));

    navLinkHome.addEventListener("click", () =>  window.location.reload());
    navLinkAddMovie.addEventListener("click", () => renderAddMovie(main));


}