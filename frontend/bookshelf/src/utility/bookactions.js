export const postNewBookFromBookshelf = async (book) => {
    const response = await fetch('http://localhost:8080/bookshelf/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(book),
    });

    if (!response.ok) {
        throw new Error('Failed to save the book');
    }

    return await response.json();
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

export const fetchGoogleBooksBySearch = async (input) => {
    try {
        const response = await fetch(`https://www.googleapis.com/books/v1/volumes?q=${input}`, {
            method: 'GET',
        });
        return await response.json();
    } catch (error) {
        console.error("Error fetching data", error)
        return [];
    }
}

export const updateBookFromBookshelf = async (bookToUpdate) => {
        const response = await fetch("http://localhost:8080/bookshelf/update", {
            method:"PUT",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(bookToUpdate)
        });
        if (!response.ok){
            throw new Error('Failed to update the book')
        }
        return await response.json();
};

export const deleteBookFromBookshelf = async (bookToDelete) => {
    const response = await fetch("http://localhost:8080/bookshelf/delete", {
        method: "DELETE",
        headers: {
            'Content-Type': 'Application/json'
        },
        body: JSON.stringify(bookToDelete)
    });
    if (!response.ok){
        throw new Error('Failed to delete book')
    }
    return await response.json();
}