import { renderAddMovie } from "./addMovie.js";
import {renderRemoveMoviePage} from "./removeMovie.js";
import { renderAddShowtime } from "./addShowTime.js";
import {renderReservations} from "./editReservation.js";
import { renderViewAddTheatreHall } from "./addTheatreHall.js";
document.addEventListener("DOMContentLoaded", initApp);

async function initApp() {
    const main = document.getElementById("admin-main");

    const navLinkHome = document.getElementById("navlink-Home")
    const navLinkAddMovie = document.getElementById("nav-link-add-movie");
   // const navLinkEditReservation = document.getElementById()
    const navLinkAddShowtime = document.getElementById("nav-link-add-showtime");
    const navLinkReservations = document.getElementById("nav-link-reservations");
    const navLinkAddTheatreHall = document.getElementById("nav-link-add-hall");

    const btnAddShowtime = document.getElementById("btn-add-showtime");
    const btnRemoveMovie = document.getElementById("btn-remove-movie");
    const btnAddMovie = document.getElementById("btn-add-movie");
    const btnCreateTheatre = document.getElementById("btn-btn-add-hall");
    const btnReservations = document.getElementById("btn-reservations");

    btnRemoveMovie.addEventListener("click", () => renderRemoveMoviePage());
    btnAddShowtime.addEventListener("click", () => renderAddShowtime(main));
    btnCreateTheatre.addEventListener("click", () => renderViewAddTheatreHall(main));
    btnAddMovie.addEventListener("click", () => renderAddMovie(main));
    btnReservations.addEventListener("click", () => renderReservations(main));

    navLinkAddShowtime.addEventListener("click", () => renderAddShowtime(main));

    navLinkHome.addEventListener("click", () =>  window.location.reload());
    navLinkAddMovie.addEventListener("click", () => renderAddMovie(main));
    navLinkReservations.addEventListener("click", () => renderReservations(main));
    navLinkAddTheatreHall.addEventListener("click", () => renderViewAddTheatreHall(main))


}