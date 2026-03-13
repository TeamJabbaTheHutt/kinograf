// import {BASE_URL} from "./config.js";

export async function renderAddShowtime(main) {

    const movies = await fetchAllMovies();

    let movieOptions = "";

    movies.forEach(movie => {
        movieOptions += `<option value="${movie.movieId}">${movie.name}</option>`;
    });

    const theatreHalls = await fetchAllTheatreHalls();

    let theatreHallOptions = "";

    theatreHalls.forEach(theatre => {
        theatreHallOptions += `<option value="${theatre.theatreId}">${theatre.theatreName}</option>`;
    });

    main.innerHTML = `
        <h2>Add Showtime</h2>

        <form id="showtime-form">
        
            <div class="form-group">
                <label>Choose Movie</label>
                <select id="movieId" class="form-control">
                    ${movieOptions}
                </select>
            </div>

            <div class="form-group">
                <label>Choose Theatre</label>
                <select id="theatreId" class="form-control">
                    ${theatreHallOptions}
                </select>
            </div>

            <div class="form-group">
                <label>Date and Time</label>
                <input type="datetime-local" id="timeOfDay" class="form-control" required>
            </div>

            <button class="btn btn-dark mt-3">Create Showtime</button>

        </form>
    `;

    const form = document.getElementById("showtime-form");

    form.addEventListener("submit", async (e) => {
        e.preventDefault();

        const timeValue = document.getElementById("timeOfDay").value;

        const showtime = {
            movieId: Number(document.getElementById("movieId").value),
            theatreId: Number(document.getElementById("theatreId").value),
            timeOfDay: timeValue + ":00"
        };

        await fetch("/showTimes", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(showtime)
        });

        alert("Showtime created!");
    });
}

export async function fetchAllMovies() {
    const response = await fetch("/movies");
    return await response.json();
}

export async function fetchAllTheatreHalls() {
    const response = await fetch("/home/theatres");
    return await response.json();
}