const TICKET_PRICE = 135;

export async function fetchAllReservations() {
    const response = await fetch("/reservations");
    return await response.json();
}

export async function fetchAllTickets() {
    const response = await fetch("/ticket/tickets");
    return await response.json();
}

export async function fetchAllShowing() {
    const response = await fetch("/showTimes");
    return await response.json();
}

export async function fetchMovieById(id) {
    const response = await fetch("/movies/" + id);
    return await response.json();
}

export async function fetchTheatreById(id) {
    const response = await fetch("/home/theatres/" + id);
    return await response.json();
}

export async function fetchSeatsById(id) {
    const response = await fetch("/seats/" + id);
    return await response.json();
}

function formatDateTime(timeString) {
    if (!timeString) return "Unknown";

    const date = new Date(timeString);

    const datePart = date.toLocaleDateString([], {
        month: "short",
        day: "numeric"
    });

    const timePart = date.toLocaleTimeString([], {
        hour: "2-digit",
        minute: "2-digit"
    });

    return `${datePart} • ${timePart}`;
}

export async function renderReservations(main) {
    const reservations = await fetchAllReservations();
    const tickets = await fetchAllTickets();
    const showings = await fetchAllShowing();

    main.innerHTML = `
        <div class="reservations-wrapper">
            <div class="reservation-search-container">
                <input
                    type="text"
                    id="reservation-search"
                    placeholder="Search reservation ID..."
                    class="reservation-search-input"
                >
            </div>

            <div class="reservations-list"></div>
        </div>
    `;

    const wrapper = main.querySelector(".reservations-list");
    const searchInput = main.querySelector("#reservation-search");

    async function drawReservations(filteredReservations) {
        wrapper.innerHTML = "";

        if (filteredReservations.length === 0) {
            wrapper.innerHTML = `<p class="no-reservations-found">No reservations found.</p>`;
            return;
        }

        for (const reservation of filteredReservations) {
            const reservationTickets = tickets.filter(
                ticket => ticket.reservationId === reservation.reservationId
            );

            const totalPrice = reservationTickets.length * TICKET_PRICE;

            let showingInfo = "No showing";
            if (reservationTickets.length > 0) {
                const showing = showings.find(
                    s => s.showTimesId === reservationTickets[0].showingId
                );

                if (showing) {
                    const movie = await fetchMovieById(showing.movieId);
                    const theatre = await fetchTheatreById(showing.theatreId);

                    showingInfo = `
                        <span class="reservation-meta-pill">${movie.name}</span>
                        <span class="reservation-meta-pill">${theatre.theatreName}</span>
                        <span class="reservation-meta-pill">${formatDateTime(showing.timeOfDay)}</span>
                    `;
                }
            }

            const div = document.createElement("div");
            div.className = "reservation-card";

            div.innerHTML = `
                <div class="reservation-header">
                    <div>
                        <h2 class="reservation-title">Reservation ID - #${reservation.reservationId}</h2>
                        <p class="reservation-subtitle">${reservation.customerName}</p>
                    </div>
                    <div class="reservation-price">${totalPrice} kr</div>
                </div>

                <div class="reservation-info-grid">
                    <div class="reservation-info-box">
                        <span class="reservation-label">Phone</span>
                        <span class="reservation-value">${reservation.phoneNumber}</span>
                    </div>

                    <div class="reservation-info-box">
                        <span class="reservation-label">Tickets</span>
                        <span class="reservation-value">${reservationTickets.length}</span>
                    </div>
                </div>

                <div class="reservation-showing">
                    <span class="reservation-label">Event info</span>
                    <div class="reservation-meta-row">
                        ${showingInfo}
                    </div>
                </div>

                <div class="reservation-seats-section">
                    <span class="reservation-label">Seats</span>
                    <div class="reservation-seats"></div>
                </div>
            `;

            const seatsContainer = div.querySelector(".reservation-seats");

            for (const ticket of reservationTickets) {
                const seat = await fetchSeatsById(ticket.seatId);
                const seatSpan = document.createElement("span");
                seatSpan.className = "reservation-seat";

                seatSpan.innerHTML = `
                    <span class="seat-name">${seat.seatRow}${seat.seatNumber}</span>
                    <button class="reservation-delete-btn">✕</button>
                `;

                const btn = seatSpan.querySelector("button");
                btn.addEventListener("click", () => {
                    deleteReservation(ticket.ticketId, reservation.reservationId, seat.seatId, main);
                    console.log({
                        ticketId: ticket.ticketId,
                        reservationId: reservation.reservationId,
                        seatId: seat.seatId
                    });
                });

                seatsContainer.appendChild(seatSpan);
            }

            wrapper.appendChild(div);
        }
    }

    searchInput.addEventListener("input", async () => {
        const searchValue = searchInput.value.trim();

        const filteredReservations = reservations.filter(reservation =>
            reservation.reservationId.toString().includes(searchValue)
        );

        await drawReservations(filteredReservations);
    });

    await drawReservations(reservations);
}


export async function deleteReservation(ticketId, reservationId, seatId, main) {
    try {
        try {
            const ticketResp = await fetch(`/ticket/tickets/${ticketId}`, {
                method: "DELETE",
                headers: { "Content-Type": "application/json" }
            });
            if (!ticketResp.ok) throw new Error(`Ticket delete failed: ${ticketResp.status}`);
            console.log(`Ticket ${ticketId} deleted `);
        } catch (err) {
            console.error("Error deleting ticket:", err);
        }

        try {
            const resResp = await fetch(`/reservations/${reservationId}`, {
                method: "DELETE",
                headers: { "Content-Type": "application/json" }
            });
            if (!resResp.ok) throw new Error(`Reservation delete failed: ${resResp.status}`);
            console.log(`Reservation ${reservationId} deleted `);
        } catch (err) {
            console.error("Error deleting reservation:", err);
        }

        try {
            const seatResp = await fetch(`/seats/${seatId}`, {
                method: "DELETE",
                headers: { "Content-Type": "application/json" }
            });
            if (!seatResp.ok) throw new Error(`Seat delete failed: ${seatResp.status}`);
            console.log(`Seat ${seatId} deleted`);
        } catch (err) {
            console.error("Error deleting seat:", err);
        }

    } catch (err) {
        console.error("Unexpected error in deleteReservation:", err);
    }
    await renderReservations(main)
}