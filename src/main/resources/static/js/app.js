

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
    const res = await fetch(`${BASE_URL}/movies`);
    return await res.json();
}

export async function getTheatres() {
    const res = await fetch(`${BASE_URL}/home/theatres`);
    return await res.json();
}

export async function getShowTimes() {
    const res = await fetch(`${BASE_URL}/showTimes`);
    return await res.json();
}