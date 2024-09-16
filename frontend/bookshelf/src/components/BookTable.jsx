import React, {useState} from 'react';
import BookCard from "./BookCard.jsx";

const BookTable = ({ books }) => {
    const [currentPage, setCurrentPage] = useState(1);
    const booksPerPage = 12;

    const totalPages = Math.ceil(books.length / booksPerPage);
    const indexOfLastBook = currentPage * booksPerPage;
    const indexOfFirstBook = indexOfLastBook - booksPerPage;
    const currentBooks = books.slice(indexOfFirstBook, indexOfLastBook);
    const paginate = (pageNumber) => setCurrentPage(pageNumber);
    return (
        <>
            <div className="join grid grid-cols-2 max-w-md">
                <button className="join-item btn btn-outline" onClick={() => paginate(currentPage - 1)} disabled={currentPage === 1}>
                    Previous page
                </button>
                <button className="join-item btn btn-outline" onClick={() => paginate(currentPage + 1)} disabled={currentPage === totalPages}>
                    Next
                </button>
            </div>
            <div className={"flex flex-wrap backdrop-blur-2xl"}>
                {currentBooks.map((book, index) => (
                    <BookCard key={book.id} book={book}/>
                ))}
            </div>
        </>
    );
};

export default BookTable;
