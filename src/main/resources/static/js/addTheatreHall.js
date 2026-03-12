let theatre = null;

export async function renderViewAddTheatreHall(main) {
    main.innerHTML = "";

    main.innerHTML = `
    <div id="theatreHallSection">
        <div id="TheatreForm" style="border:1px solid #ccc; padding:15px; max-width:500px;">
            <h3>Create Theatre Hall</h3>
    
            <label><strong>Theatre Name:</strong></label><br>
            <input id="theatreName" type="text"><br><br>
    
            <label><strong>Rows:</strong></label><br>
            <input id="rowsInTheatre" type="number"><br><br>
    
            <label><strong>Seats per Row:</strong></label><br>
            <input id="seatsInTheatre" type="number"><br><br>
           
            <button id="create-theatre-button" class="btn btn-green">Create Hall</button>
        </div>
    </div>
    `;

    document.getElementById("create-theatre-button").addEventListener("click", createTheatreHall);

}



async function createTheatreHall() {
    let capacity = document.getElementById("rowsInTheatre").value * document.getElementById("seatsInTheatre").value;

    theatre = {
        theatreName: document.getElementById("theatreName").value,
        rowsInTheatre: document.getElementById("rowsInTheatre").value,
        seatsInTheatre: document.getElementById("seatsInTheatre").value,
        capacity: capacity
    };

    const response = await fetch("/theatre", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(theatre)
    });

    if (response.ok) {
        alert("Hall created!");
    } else {
        alert("Error creating hall.");
    }
}

