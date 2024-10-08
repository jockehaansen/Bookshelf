const apiUrl = import.meta.env.VITE_API_URL;

export const postNewBookFromBookshelf = async (book) => {
    const response = await fetch(`${apiUrl}/bookshelf/add` , {
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
        const response = await fetch(`${apiUrl}/bookshelf`, {
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
        const response = await fetch(`https://www.googleapis.com/books/v1/volumes?q=${input}&maxResults=40`, {
            method: 'GET',
        });
        return await response.json();
    } catch (error) {
        console.error("Error fetching data", error)
        return [];
    }
}

export const updateBookFromBookshelf = async (bookToUpdate) => {
        const response = await fetch(`${apiUrl}/bookshelf/update`, {
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
    const response = await fetch(`${apiUrl}/bookshelf/delete`, {
        method: "DELETE",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(bookToDelete)
    });
    if (!response.ok){
        throw new Error('Failed to delete book')
    }
    return await response.json();
}

export const saveBookFromGoogleBooks = async (book) => {
    const response = await fetch(`${apiUrl}/books/save`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(book)
    })
    if (!response.ok) {
        throw new Error("Failed to save book")
    }
}