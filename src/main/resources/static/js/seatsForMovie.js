import { BASE_URL } from "./config.js";
import { getMovies, getTheatres, getShowTimes, getSeats, getReservations, getTickets } from "./app.js";
import {renderViewSchedule} from "./viewSchedule.js";

export async function renderShowtimeSeats(main, item) {
    console.log("Clicked showtime item:", item);

    const movies = await getMovies();
    const theatres = await getTheatres();
    const tickets = await getTickets();

    main.innerHTML = "";

    const theatre = theatres.find(t => t.theatreId === item.theatreId);
    const movie = movies.find(m => m.movieId === item.movieId);

    const selectedSeats = [];

    const movieTitle = document.createElement("h2");
    movieTitle.textContent = movie.name;
    movieTitle.style.color = "white";
    movieTitle.style.textAlign = "center";
    main.appendChild(movieTitle);

    const seatsContainer = document.createElement("div");
    seatsContainer.classList.add("seatsContainer");
    main.appendChild(seatsContainer);



    const formContainer = document.createElement("div");
    formContainer.classList.add("formContainer");
    main.appendChild(formContainer);


    for (let i = 1; i <= theatre.capacity; i++) {
        const seatDiv = document.createElement("div");
        seatDiv.classList.add("seatDiv");
        seatDiv.dataset.seatId = i;
        const seatBottom = document.createElement("div");
        seatBottom.classList.add("seatBottom");

        seatDiv.appendChild(seatBottom);
        const seatTaken = tickets.some(t => t.showingId === item.showTimesId && t.seatId === i);

        if (seatTaken) {
            seatDiv.style.backgroundColor = "red";
        } else {

            seatDiv.addEventListener("click", () => {

                const seatId = i;

                if (selectedSeats.includes(seatId)) {

                    const index = selectedSeats.indexOf(seatId);
                    selectedSeats.splice(index, 1);
                    seatDiv.style.backgroundColor = "green";

                } else {

                    selectedSeats.push(seatId);
                    seatDiv.style.backgroundColor = "#5ce1e6";


                }
                renderBookSeatForm(formContainer, selectedSeats, item);

                console.log("Selected seats:", selectedSeats);
            });

        }

        seatsContainer.appendChild(seatDiv);
    }
}
function renderBookSeatForm(container, selectedSeats, item) {

    container.innerHTML = "";

    container.innerHTML = `
        <div class="bookSeatFormDiv">
            <form class="formLayout">
                <h3 style="color:white;">Book Seats</h3>

                <p style="color:white;">
                    Selected seats: ${selectedSeats.join(", ")}
                </p>

                <input type="text" name="name" placeholder="Name" required>
                <br><br>

                <input type="number" name="phone" placeholder="Phonenumber" required>
                <br><br>

                <button class="addTimeAndHallBtn" type="submit">Reserve Seats</button>
            </form>
        </div>
    `;

    const form = container.querySelector(".formLayout");

    form.addEventListener("submit", async (e) => {
        e.preventDefault();

        const name = form.name.value;
        const phonenumber = form.phone.value;

        console.log("Selected seats:", selectedSeats);
        console.log("Showtime item:", item);
        console.log("Name:", name);
        console.log("Phone:", phonenumber);

        await bookSeats(container, selectedSeats, item, name, phonenumber);
    });
}
export async function bookSeats(mainContainer, selectedSeats, item, name, phonenumber, seatsFromDB) {
    try {

        const res = await fetch(`${BASE_URL}/reservations`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ customerName: name, phonenumber: phonenumber })
        });

        if (!res.ok) {
            console.error("Failed to create reservation");
            return;
        }

        const reservation = await res.json();


        for (const seatNumber of selectedSeats) {

            const seat = seatsFromDB.find(s => s.theatreId === item.theatreId && s.seatNumber === seatNumber);
            if (!seat) {
                console.warn("Seat not found in DB:", seatNumber);
                continue;
            }

            await fetch(`${BASE_URL}/ticket/ticket`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({
                    showingId: item.showTimesId,
                    reservationId: reservation.reservationId,
                    seatId: seat.seatId,
                    price: 0
                })
            });
        }

        console.log("Seats booked successfully");


        selectedSeats.length = 0;


        await renderShowtimeSeats(mainContainer, item);

    } catch (err) {
        console.error("Error booking seats:", err);
    }
}