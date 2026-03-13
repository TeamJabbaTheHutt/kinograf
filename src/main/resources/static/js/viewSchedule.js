import { getTheatres, getMovies, getShowTimes } from "./app.js";
import { renderShowtimeSeats } from "./seatsForMovie.js";

const apiKey = "b9c41db5";

async function fetchMovieDetails(omdbID) {
    if (!omdbID) {
        return {
            poster: "",
            rating: "N/A",
            runtime: "N/A",
            ageRating: "N/A"
        };
    }

    try {
        const response = await fetch(
            `https://www.omdbapi.com/?apikey=${apiKey}&i=${omdbID}`
        );
        const movie = await response.json();

        return {
            poster: movie.Poster !== "N/A" ? movie.Poster : "",
            rating: movie.imdbRating || "N/A",
            runtime: movie.Runtime || "N/A",
            ageRating: movie.Rated || "N/A"
        };
    } catch (error) {
        console.error("Error fetching movie details:", error);
        return {
            poster: "",
            rating: "N/A",
            runtime: "N/A",
            ageRating: "N/A"
        };
    }
}

export async function renderViewSchedule(mainContainer) {
    mainContainer.className = "viewSchedule-body";

    const allMovies = await getMovies();
    const allTheatres = await getTheatres();
    const allShowTimes = await getShowTimes();

    const moviesWithOmdbData = await Promise.all(
        allMovies.map(async (movie) => {
            const omdbData = await fetchMovieDetails(movie.omdbID);
            return {
                ...movie,
                poster: omdbData.poster,
                rating: omdbData.rating,
                runtime: omdbData.runtime,
                ageRating: omdbData.ageRating
            };
        })
    );

    let htmlContent = `
        <div class="schedule-page">
            <h1 class="schedule-title">Now Showing</h1>
            <div class="schedule-list">
    `;

    for (const movie of moviesWithOmdbData) {
        const showTimesForMovie = allShowTimes.filter(
            st => st.movieId === movie.movieId
        );

        htmlContent += `
            <article class="schedule-card">
                <div class="schedule-poster-column">
                    <img 
                        class="schedule-poster" 
                        src="${movie.poster || "https://via.placeholder.com/300x450?text=No+Poster"}" 
                        alt="${movie.name} poster"
                    >
                </div>

                <div class="schedule-content-column">
                    <div class="schedule-card-header">
                        <div class="schedule-title-block">
                            <h2 class="schedule-movie-title">${movie.name}</h2>
                            <div class="schedule-meta">
                                <span class="meta-badge">IMDb ${movie.rating}</span>
                                <span class="meta-badge">${movie.runtime}</span>
                                <span class="meta-badge">${movie.ageRating}</span>
                                <span class="meta-badge">Price: 135 DKK</span>
                            </div>
                        </div>
                    </div>

                    <div class="showtime-list">
        `;

        if (showTimesForMovie.length === 0) {
            htmlContent += `
                <div class="empty-showtimes">
                    No showtimes available right now
                </div>
            `;
        } else {
            showTimesForMovie.forEach(showtime => {
                const theatre = allTheatres.find(
                    t => t.theatreId === showtime.theatreId
                );

                const formattedTime = showtime.timeOfDay
                    ? new Date(showtime.timeOfDay).toLocaleString("da-DK", {
                        dateStyle: "medium",
                        timeStyle: "short"
                    })
                    : "No time";

                htmlContent += `
                    <button 
                        class="showtime-main showtimeClick"
                        data-id="${showtime.showTimesId}">
                        <span class="showtime-theatre">
                            ${theatre ? theatre.theatreName : "Unknown Theatre"}
                        </span>
                        <span class="showtime-time">
                            ${formattedTime}
                        </span>
                        <span class="showtime-capacity">
                            ${theatre ? theatre.capacity : "?"} seats
                        </span>
                    </button>
                `;
            });
        }

        htmlContent += `
                    </div>
                </div>
            </article>
        `;
    }

    htmlContent += `
            </div>
        </div>
    `;

    mainContainer.innerHTML = htmlContent;

    document.querySelectorAll(".showtimeClick").forEach(button => {
        button.addEventListener("click", () => {
            const showTimeId = Number(button.dataset.id);
            const showtime = allShowTimes.find(
                st => st.showTimesId === showTimeId
            );

            renderShowtimeSeats(mainContainer, showtime);
        });
    });
}