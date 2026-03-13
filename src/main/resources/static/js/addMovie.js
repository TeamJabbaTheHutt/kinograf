let selectedMovie = null;

export async function renderAddMovie(main) {
    main.innerHTML = "";

    main.innerHTML = `
        <section class="add-movie-page">
            <div class="add-movie-search">
                <input id="movieSearch" type="text" placeholder="Search movie..." />
                <div id="searchResults" class="search-results"></div>
            </div>

            <div id="movieSection" class="movie-section disabled-section">
                <div id="movieDetails" class="movie-details-card">
                    <div class="movie-poster-wrapper">
                        <img id="moviePoster" src="" alt="Movie Poster" />
                    </div>

                    <div class="movie-info">
                        <h2 id="movieTitle"></h2>

                        <div class="movie-meta">
                            <p><strong>Description:</strong></p>
                            <p id="movieDescription" class="movie-description"></p>

                            <p><strong>Director:</strong> <span id="movieDirector"></span></p>
                            <p><strong>Age rating:</strong> <span id="movieAge"></span></p>
                            <p><strong>IMDb score:</strong> <span id="movieRating"></span></p>
                        </div>

                        <button id="create-button" class="createBtn">Create Movie</button>
                    </div>
                </div>
            </div>
        </section>
    `;

    let timer;

    document.getElementById("movieSearch").addEventListener("input", () => {
        clearTimeout(timer);
        timer = setTimeout(searchMovies, 300);
    });

    document.getElementById("create-button").addEventListener("click", createMovie);
}

async function searchMovies(){
    const apiKey = "b9c41db5";
    const query = document.getElementById("movieSearch").value;

    if (query.length < 3) return;

    const response = await fetch(
    `https://www.omdbapi.com/?apikey=${apiKey}&s=${encodeURIComponent(query)}`
    );

    const data = await response.json();

    if (data.Search) showResults(data.Search);
    else document.getElementById("searchResults").innerHTML = "No results found.";
}

function showResults(movies) {
    const results = document.getElementById("searchResults");
    results.innerHTML = "";

    movies.forEach(movie => {
        const item = document.createElement("div");
        item.className = "search-result-item";
        item.textContent = `${movie.Title} (${movie.Year})`;
        item.onclick = () => selectMovie(movie.imdbID);
        results.appendChild(item);
    });
}

async function selectMovie(id) {

    const apiKey = "b9c41db5";
    const response = await fetch(
        `https://www.omdbapi.com/?apikey=${apiKey}&i=${id}&plot=full`
    );

    const movie = await response.json();

    fillForm(movie);
    document.getElementById("searchResults").innerHTML = "";
}

function fillForm(movie) {

    selectedMovie = {
        name: movie.Title,
        omdbID: movie.imdbID
    };

    document.getElementById("moviePoster").src = movie.Poster;
    document.getElementById("movieTitle").textContent = movie.Title;
    document.getElementById("movieDescription").textContent = movie.Plot;
    document.getElementById("movieDirector").textContent = movie.Director;
    document.getElementById("movieAge").textContent = movie.Rated;
    document.getElementById("movieRating").textContent = movie.imdbRating;

    document.getElementById("movieSection").classList.remove("disabled-section");
}

async function createMovie() {

    if (!selectedMovie) {
        alert("Please select a movie first.");
        return;
    }

    const response = await fetch("/movies", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(selectedMovie)
    });

    if (response.ok) {
        alert("Movie created!");
    } else {
        alert("Error creating movie.");
    }
}

