let timer;

document.getElementById("movieSearch").addEventListener("input", () => {
    clearTimeout(timer);
    timer = setTimeout(searchMovies, 300);
});
async function searchMovies(){
    const apiKey = "b9c41db5"; // replace with your OMDb API key
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

        item.textContent = movie.Title;
        item.style.cursor = "pointer";
        item.onclick = () => selectMovie(movie.imdbID);

        results.appendChild(item);
    })
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
    document.getElementById("moviePoster").src = movie.Poster;
    document.getElementById("movieTitle").textContent = movie.Title;
    document.getElementById("movieDescription").textContent = movie.Plot;
    document.getElementById("movieDirector").textContent = movie.Director;
    document.getElementById("movieAge").textContent = movie.Rated;
    document.getElementById("movieRating").textContent = movie.imdbRating;
}

