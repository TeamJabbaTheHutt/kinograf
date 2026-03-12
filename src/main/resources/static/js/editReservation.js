import {BASE_URL} from "./config.js";

export async function fetchAllReservations() {
    const response = await fetch(BASE_URL + "/reservations");
    return await response.json();
}

export async function fetchAllTickets() {
    const response = await fetch(BASE_URL + "/ticket/tickets");
    return await response.json();
}

export async function fetchAllShowing() {
    const response = await fetch(BASE_URL + "/showTimes");
    return await response.json();
}

export async function fetchMovieById(id) {
    const response = await fetch(BASE_URL + "/movies/" + id);
    return await response.json();
}

export async function fetchTheatreById(id) {
    const response = await fetch(BASE_URL + "/home/theatres/" + id);
    return await response.json();
}

export async function fetchSeatsById(id) {
    const response = await fetch(BASE_URL + "/seats/" + id);
    return await response.json();
}
export async function renderReservations(main) {
    const reservations = await fetchAllReservations();
    const tickets = await fetchAllTickets();
    const showings = await fetchAllShowing();

    main.innerHTML = "";

    for (const reservation of reservations) {
        const reservationTickets = tickets.filter(
            ticket => ticket.reservationId === reservation.reservationId
        );

        const totalPrice = reservationTickets.reduce(
            (sum, ticket) => sum + ticket.price,
            0
        );

        let showingInfo = "No showing";
        if (reservationTickets.length > 0) {
            const showing = showings.find(
                s => s.showTimesId === reservationTickets[0].showingId
            );
            if (showing) {
                const movie = await fetchMovieById(showing.movieId);
                const theatre = await fetchTheatreById(showing.theatreId);
                showingInfo = `Movie: ${movie.name} | Theatre: ${theatre.theatreName} | Time: ${showing.timeOfDay}`;
            }
        }

        const div = document.createElement("div");

        div.innerHTML = `
            ID: ${reservation.reservationId} | 
            Name: ${reservation.customerName} | 
            Phone: ${reservation.phoneNumber} | 
            Tickets: ${reservationTickets.length} | 
            Total Price: ${totalPrice} | 
            Showing: ${showingInfo} | 
            Seats:
        `;

        for (const ticket of reservationTickets) {
            const seat = await fetchSeatsById(ticket.seatId);
            const seatSpan = document.createElement("span");

            seatSpan.innerHTML = `
                ${seat.seatRow}${seat.seatNumber} 
                <button>X</button>
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

            div.appendChild(seatSpan);
        }

        main.appendChild(div);
    }
}


export async function deleteReservation(ticketId, reservationId, seatId, main) {
    try {
        try {
            const ticketResp = await fetch(`${BASE_URL}/ticket/tickets/${ticketId}`, {
                method: "DELETE",
                headers: { "Content-Type": "application/json" }
            });
            if (!ticketResp.ok) throw new Error(`Ticket delete failed: ${ticketResp.status}`);
            console.log(`Ticket ${ticketId} deleted `);
        } catch (err) {
            console.error("Error deleting ticket:", err);
        }

        try {
            const resResp = await fetch(`${BASE_URL}/reservations/${reservationId}`, {
                method: "DELETE",
                headers: { "Content-Type": "application/json" }
            });
            if (!resResp.ok) throw new Error(`Reservation delete failed: ${resResp.status}`);
            console.log(`Reservation ${reservationId} deleted `);
        } catch (err) {
            console.error("Error deleting reservation:", err);
        }

        try {
            const seatResp = await fetch(`${BASE_URL}/seats/${seatId}`, {
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