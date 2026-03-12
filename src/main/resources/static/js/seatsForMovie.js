import { BASE_URL } from "./config.js";
import { getMovies, getTheatres, getShowTimes, getSeats, getReservations, getTickets } from "./app.js";
import {renderViewSchedule} from "./viewSchedule.js";

export async function renderShowtimeSeats(main, item) {
    const tickets = await getTickets();
    const showtime = item;
    const theatres = await getTheatres();
    const seats = await getSeats();

    const ticketsForShowtime = tickets.filter(
        ticket => ticket.showingId === showtime.showTimesId
    );

    const theatre = theatres.find(
        t => t.theatreId === showtime.theatreId
    );

    const reservedSeatIds = ticketsForShowtime.map(ticket => ticket.seatId);

    const reservedSeats = seats.filter(seat =>
        reservedSeatIds.includes(seat.seatId)
    );

    const selectedSeats = [];

    const grid = [];

    for (let row = 1; row <= theatre.rowsInTheatre; row++) {
        const rowSeats = [];

        for (let seatNum = 1; seatNum <= theatre.seatsInTheatre; seatNum++) {

            const seat = seats.find(
                s => s.seatRow === row && s.seatNumber === seatNum
            );

            const isReserved = reservedSeats.some(
                rs => rs.seatId === seat?.seatId
            );

            rowSeats.push({
                row,
                seatNumber: seatNum,
                reserved: isReserved
            });
        }

        grid.push(rowSeats);
    }

    main.innerHTML = "";

    const bookingContainer = document.createElement("div");
    bookingContainer.classList.add("booking-container");

    grid.forEach(row => {
        const rowDiv = document.createElement("div");
        rowDiv.classList.add("seat-row");

        row.forEach(seat => {
            const seatBtn = document.createElement("button");
            seatBtn.classList.add("seat");

            if (seat.reserved) {
                seatBtn.classList.add("reserved");
                seatBtn.disabled = true;
            } else {

                seatBtn.addEventListener("click", () => {

                    const seatLabel = `${seat.row}-${seat.seatNumber}`;

                    if (selectedSeats.includes(seatLabel)) {

                        // remove seat
                        const index = selectedSeats.indexOf(seatLabel);
                        selectedSeats.splice(index, 1);
                        seatBtn.classList.remove("selected");

                    } else {

                        // add seat
                        selectedSeats.push(seatLabel);
                        seatBtn.classList.add("selected");

                    }

                    renderBookButton(bookingContainer, selectedSeats, item);
                });

            }

            rowDiv.appendChild(seatBtn);
        });

        main.appendChild(rowDiv);
    });

    main.appendChild(bookingContainer);
}


function renderBookButton(container, selectedSeats, item) {

    container.innerHTML = "";

    if (selectedSeats.length === 0) return;

    const btn = document.createElement("button");
    btn.textContent = `Book ${selectedSeats.length} Seat(s)`;
    btn.classList.add("addTimeAndHallBtn");
    btn.addEventListener("click", () => {
        renderBookSeatForm(container, selectedSeats, item);
    });

    container.appendChild(btn);
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

async function bookSeats(container, selectedSeats, item, name, phonenumber) {

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

        for (const seatLabel of selectedSeats) {
            const [row, seatNumber] = seatLabel.split("-").map(Number);

            // 2a️⃣ Create the seat
            const createSeatRes = await fetch(`${BASE_URL}/seats`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({
                    theatreId: item.theatreId,
                    rowNumber: row,
                    seatNumber: seatNumber
                })
            });

            if (!createSeatRes.ok) {
                console.error("Failed to create seat:", row, seatNumber);
                continue;
            }

            const seat = await createSeatRes.json();

            const ticketRes = await fetch(`${BASE_URL}/ticket/ticket`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({
                    showingId: Number(item.showTimesId),
                    seatId: Number(seat.seatId),
                    reservationId: Number(reservation.reservationId),
                    price: 100
                })
            });

            if (!ticketRes.ok) {
                console.error("Failed to create ticket for seat:", row, seatNumber);
            }
        }

        container.innerHTML = "<h2 style='color:white;'>Seats successfully booked!</h2>";

    } catch (err) {
        console.error("Error making reservation:", err);
    }
}