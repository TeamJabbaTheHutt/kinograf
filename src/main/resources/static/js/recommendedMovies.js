const apiKey = "b9c41db5";
const recommendedMovies = ["Inception", "The Dark Knight", "Interstellar"];

document.addEventListener("DOMContentLoaded", () => {
    recommendedMovies.forEach((title, index) => {
        fillCard(title, index + 1);
    });
});

export async function fillCard(title, number) {
    const response = await fetch(
        `https://www.omdbapi.com/?apikey=${apiKey}&t=${encodeURIComponent(title)}`
    );
    const movie = await response.json();

    document.getElementById("moviePoster" + number).src = movie.Poster;
    document.getElementById("movieTitle" + number).textContent = movie.Title;
    document.getElementById("movieDuration" + number).textContent = movie.Runtime;
    document.getElementById("movieRating" + number).textContent = movie.imdbRating;
}
