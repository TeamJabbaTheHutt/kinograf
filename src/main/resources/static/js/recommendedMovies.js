// replace with your OMDb API key
const apiKey = "b9c41db5";

document.addEventListener("DOMContentLoaded", () => {
    fillCard("Inception", 1);
    fillCard("The Dark Knight", 2);
    fillCard("Interstellar", 3);
});

const recommendedMovies = ["Inception", "The Dark Knight", "Interstellar"];


export async function fillCard(title, number) {
    const response = await fetch(`https://www.omdbapi.com/?apikey=${apiKey}&t=${encodeURIComponent(title)}`);
    const movie = await response.json();

    // Fill the card elements
    document.getElementById("moviePoster" + number).src = movie.Poster;
    document.getElementById("movieTitle" + number).textContent = movie.Title;
    document.getElementById("movieDuration" + number).textContent = movie.Runtime;
    document.getElementById("movieRating" + number).textContent = movie.imdbRating;
}

