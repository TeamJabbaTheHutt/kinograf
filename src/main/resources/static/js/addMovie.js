

export async function renderAddMovie(main) {
    main.innerHTML = "";

    main.innerHTML = "<label for=\"movieSearch\"></label>\n" +
        "    <input id=\"movieSearch\" type=\"text\" placeholder=\"Search movie\">\n" +
        "    <div id=\"searchResults\"></div>\n" +
        "\n" +
        "    <div id=\"movieDetails\" style=\"border:1px solid #ccc; padding:15px; max-width:500px;\">\n" +
        "        <img id=\"moviePoster\" src=\"\" alt=\"Movie Poster\" style=\"width:100%; max-width:300px;\">\n" +
        "        <h2 id=\"movieTitle\"></h2>\n" +
        "        <p><strong>Description:</strong> <span id=\"movieDescription\"></span></p>\n" +
        "        <p><strong>Director:</strong> <span id=\"movieDirector\"></span></p>\n" +
        "        <p><strong>Age rating:</strong> <span id=\"movieAge\"></span></p>\n" +
        "        <p><strong>IMDB score:</strong> <span id=\"movieRating\"></span></p>\n" +
        "    </div>"

    let timer;

    document.getElementById("movieSearch").addEventListener("input", () => {
        clearTimeout(timer);
        timer = setTimeout(searchMovies, 300);
    });
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

