import {BASE_URL} from "./config.js";

export function renderAddShowtime(main) {

    main.innerHTML = `
        <h2>Add Showtime</h2>

        <form id="showtime-form">
        
            <div class="form-group">
                <label>Movie ID</label>
                <input type="number" id="movieId" class="form-control" required>
            </div>

            <div class="form-group">
                <label>Theatre ID</label>
                <input type="number" id="theatreId" class="form-control" required>
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

        await fetch(`${BASE_URL}/showTimes`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(showtime)
        });

        alert("Showtime created!");
    });
}