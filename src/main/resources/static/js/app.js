

import { renderViewSchedule } from "./viewSchedule.js";
import {BASE_URL} from "./config.js";

document.addEventListener("DOMContentLoaded", initApp);

async function initApp() {
    const main = document.getElementById("main");

    const navLinkViewSchedule = document.getElementById("navLink-ViewSchedule");
    const navLinkHome = document.getElementById("navlink-Home")

    navLinkHome.addEventListener("click", () =>  window.location.reload());
    navLinkViewSchedule.addEventListener("click", () => renderViewSchedule(main));


}

export async function getMovies() {
    const res = await fetch(`/movies`);
    return await res.json();
}

export async function getTheatres() {
    const res = await fetch(`/home/theatres`);
    return await res.json();
}

export async function getShowTimes() {
    const res = await fetch(`/showTimes`);
    return await res.json();
}

export async function getSeats() {
    const res = await fetch(`/seats`)
    return await res.json();
}
export async function getReservations() {
    const res = await fetch(`/reservations`)
    return await res.json();
}
export async function getTickets() {
    const res = await fetch(`/ticket/tickets`)
    return await res.json();
}