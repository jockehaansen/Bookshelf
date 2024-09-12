import React from 'react';
import BookTable from "../components/BookTable.jsx";

const BookshelfPage = ({ books }) => {
    return (
        <div>
            <BookTable books={ books }/>
        </div>
    );
};

export default BookshelfPage;
