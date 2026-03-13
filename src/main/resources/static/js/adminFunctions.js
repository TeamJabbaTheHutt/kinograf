

import {BASE_URL} from "./config.js";
import {getTheatres, getMovies, getShowTimes} from "./app.js";
import {renderViewSchedule} from "./viewSchedule.js";
export async function renderAddTimeAndHall(movie) {

    const theatres = await getTheatres()


    const form = document.createElement("form");
    form.classList.add("formLayout");

    form.innerHTML = `
        <h4>Adding movie for ${movie.name}</h4>
        <label>Theatre:</label><br>
        <select name="theatreId"">
            ${theatres.map(t => `<option value="${t.theatreId}">${t.theatreName} (ID: ${t.theatreId})</option>`).join("")}
        </select><br>
    
        <label>Date:</label><br>
        <input type="date" name="timeOfDay""><br>
    
        <button class="addTimeAndHallBtn" type="submit">Save</button>
        <button class="cancelBtn" type="button" id="cancelForm">Cancel</button>
    `;


    const button = document.querySelector(`.addTimeAndHallBtn[data-id="${movie.movieId}"]`);
    button.insertAdjacentElement("afterend", form);


    form.querySelector("#cancelForm").addEventListener("click", () => form.remove());


    form.addEventListener("submit", async (e) => {
        e.preventDefault();

        const theatreId = parseInt(form.theatreId.value);
        const timeOfDay = form.timeOfDay.value;


        try {


            const res = await fetch(`${BASE_URL}/showTimes`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({
                    movieId: movie.movieId,
                    theatreId: theatreId,
                    timeOfDay: timeOfDay
                })
            });

            if (res.ok) {
                form.remove();

                renderViewSchedule(document.querySelector("main"));
            } else {
                console.error("Failed to add showtime:", res.status);
            }
        } catch (err) {
            console.error("Error adding showtime:", err);
        }
    });
}

// export async function renderAddNewMovie() {
//     console.log("Add a new movie");
//
//
//     const form = document.createElement("form");
//
//     form.classList.add("formLayout");
//
//     form.innerHTML = `
//         <h4>Add New Movie</h4>
//
//         <label for="movieName">Movie Name:</label><br>
//         <input type="text" name="name" "><br>
//
//         <label for="categories">Categories:</label><br>
//         <input type="text" name="categories" placeholder="Action, Comedy, ..." "><br>
//
//         <label for="ageLimit">Age Limit:</label><br>
//         <input type="number" name="ageLimit""><br>
//
//         <button class="addTimeAndHallBtn" type="submit">Save</button>
//         <button class="cancelBtn" type="button" id="cancelForm">Cancel</button>
//     `;
//
//
//     const button = document.getElementById("createNewMovieBtn");
//     button.insertAdjacentElement("afterend", form);
//
//
//     form.querySelector("#cancelForm").addEventListener("click", () => form.remove());
//
//
//     form.addEventListener("submit", async (e) => {
//         e.preventDefault();
//
//         const name = form.name.value;
//         const categories = form.categories.value;
//         const ageLimit = parseInt(form.ageLimit.value);
//
//         try {
//             const res = await fetch(`${BASE_URL}/movies`, {
//                 method: "POST",
//                 headers: { "Content-Type": "application/json" },
//                 body: JSON.stringify({
//                     name: name,
//                     categories: categories,
//                     ageLimit: ageLimit
//                 })
//             });
//
//             if (res.ok) {
//                 form.remove();
//                 await renderViewSchedule(document.querySelector("main"));
//             } else {
//                 const text = await res.text();
//                 console.error("Failed to add movie:", res.status, text);
//             }
//         } catch (err) {
//             console.error("Error adding movie:", err);
//         }
//     });
// }



export async function deleteShowtime(showtimeId) {
    console.log("id:", showtimeId);

    try {
        const response = await fetch(`${BASE_URL}/showTimes/${showtimeId}`, {
            method: "DELETE",
        });

        if (!response.ok) {
            console.error("Showtime deleted successfully!");
        }
    } catch (error) {
        console.error("Error deleting showtime:", error);
    }
}
