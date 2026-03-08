

import {renderAddTimeAndHall, renderAddNewMovie, deleteShowtime} from "./adminFunctions.js";
import {getTheatres, getMovies, getShowTimes} from "./app.js";
import {renderShowtimeSeats} from "./seatsForMovie.js";

export async function renderViewSchedule(mainContainer) {


    mainContainer.className = "";
    mainContainer.classList.add("viewSchedule-body");


    const isAdmin = true;


    const allMovies = await getMovies();
    const allTheatres = await getTheatres();
    const allShowTimes = await getShowTimes();


    let htmlContent = `<div class="movies-container">`;

    if (isAdmin) {
        htmlContent += `<div style="text-align:center;"><h1>Administration</h1></div>`;
    }


    for (let movie of allMovies) {
        htmlContent += `<div class="movie-card"><h2>${movie.name}</h2></div>`;

        const showTimesForMovie = allShowTimes.filter(st => st.movieId === movie.movieId);

        if (showTimesForMovie.length === 0) {
            htmlContent += `<p style="margin-left:20px; color:white;">No showtimes yet</p>`;
        } else {
            showTimesForMovie.forEach(showtime => {
                const theatre = allTheatres.find(t => t.theatreId === showtime.theatreId);

                htmlContent += `<p class="showtimeClick" data-id="${showtime.showTimesId}" style="margin-left:20px; color:white; cursor:pointer;">
                Theatre: ${theatre ? theatre.theatreName : "No Theatre name"},
                Time: ${showtime.timeOfDay ?? "No Time"},
                Capacity: ${theatre ? theatre.capacity : "No Capacity"}
                ${isAdmin ? `<span class="deleteShowtime" data-id="${showtime.showTimesId}" style="color:red; cursor:pointer; margin-left:10px;">X</span>` : ""}
            </p>`;
            });
        }



        if (isAdmin) {
            htmlContent += `<button class="addTimeAndHallBtn" data-id="${movie.movieId}" >Add time and hall</button>`;
        }
    }

    htmlContent += `</div>`;


    htmlContent += `
        <div style="text-align:center; margin-top:20px;">
            <button id="createNewMovieBtn" class="createBtn">Add new Movie</button>
        </div>
    `;


    mainContainer.innerHTML = htmlContent;


    document.querySelectorAll(".addTimeAndHallBtn").forEach(button => {
        button.addEventListener("click", () => {
            const movieId = button.dataset.id;
            const movie = allMovies.find(m => m.movieId == movieId);
            renderAddTimeAndHall(movie);
        });
    });

    document.querySelectorAll(".deleteShowtime").forEach(span => {
        span.addEventListener("click", async () => {
            const showtimeId = span.dataset.id;

            await deleteShowtime(showtimeId);
            await renderViewSchedule(document.querySelector("main"));
        });
    });

    document.querySelectorAll(".showtimeClick").forEach(p => {
        p.addEventListener("click", () => {
            const showTimeId = Number(p.dataset.id);
            const showtime = allShowTimes.find(st => st.showTimesId === showTimeId);
            renderShowtimeSeats(mainContainer, showtime);
        });
    });

    document.getElementById("createNewMovieBtn").addEventListener("click", () => {
        renderAddNewMovie();
    });
}

