import React from 'react';
import BookCard from "./BookCard.jsx";

const BookTable = ({ books }) => {
    return (
        <div style={{display: 'flex', flexWrap: 'wrap'}}>
            {books.map((book, index) => (
                <BookCard key={index} book={book}/>
            ))}
        </div>
    );
};

export default BookTable;
