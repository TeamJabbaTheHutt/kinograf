// Finder main containeren fra jeres eksisterende HTML
const adminMain = document.getElementById("admin-main");
const removeMovieNav = document.getElementById("nav-link-remove-movie");

// Når man klikker på "Remove Movie" i navbaren
removeMovieNav.addEventListener("click", (e) => {
    e.preventDefault();
    renderRemoveMoviePage();
});

function renderRemoveMoviePage() {
    adminMain.innerHTML = `
        <h2 class="text-center mt-4">Remove Movies</h2>
        <div id="movie-list" class="container mt-4"></div>
    `;

    loadMovies();
}

async function loadMovies() {
    const movieListContainer = document.getElementById("movie-list");

    try {
        const response = await fetch("/movies");
        const movies = await response.json();

        movieListContainer.innerHTML = "";

        if (movies.length === 0) {
            movieListContainer.innerHTML = "<p>No movies found.</p>";
            return;
        }

        const listGroup = document.createElement("ul");
        listGroup.className = "list-group";

        movies.forEach(movie => {
            const listItem = document.createElement("li");
            listItem.className =
                "list-group-item d-flex justify-content-between align-items-center";

            listItem.innerHTML = `
                <span>${movie.name}</span>
                <button class="btn btn-sm btn-danger">
                    Delete
                </button>
            `;

            listItem.querySelector("button").addEventListener("click", () => {
                deleteMovie(movie.movieId);
            });

            listGroup.appendChild(listItem);
        });

        movieListContainer.appendChild(listGroup);

    } catch (error) {
        console.error("Error loading movies:", error);
        movieListContainer.innerHTML = "<p>Error loading movies.</p>";
    }
}

async function deleteMovie(id) {
    const confirmDelete = confirm("Are you sure you want to delete this movie?");
    if (!confirmDelete) return;

    try {
        await fetch(`/movies/${id}`, {
            method: "DELETE"
        });

        // Reload filmene efter sletning
        loadMovies();

    } catch (error) {
        console.error("Error deleting movie:", error);
        alert("Could not delete movie.");
    }
}