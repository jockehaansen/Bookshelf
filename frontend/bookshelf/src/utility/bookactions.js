export const postNewBookFromBookshelf = (newBook) => {
    fetch("http://localhost:8080/bookshelf/add", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(newBook),
    })
        .then(response => response.json())
        .then(data => {
            console.log("Book added successfully:", data);
        })
        .catch(error => {
            console.error("Error adding the book:", error);
        });
};

export const fetchUserBookshelfOnLoad = async () => {
    try {
        const response = await fetch("http://localhost:8080/bookshelf", {
            method: "GET",
        });
        const jsonData = await response.json();
        return(jsonData);
    } catch (error) {
        console.error("Error fetching data", error);
        return [];
    }
}