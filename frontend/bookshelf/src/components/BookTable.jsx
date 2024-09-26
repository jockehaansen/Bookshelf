import React, {useState} from 'react';
import BookCard from "./BookCard.jsx";
import PropTypes from "prop-types";

const BookTable = ({ books, handleUpdateBook, handleDeleteBook }) => {
    const [currentPage, setCurrentPage] = useState(1);
    const booksPerPage = 12;

    const totalPages = Math.ceil(books.length / booksPerPage);
    const indexOfLastBook = currentPage * booksPerPage;
    const indexOfFirstBook = indexOfLastBook - booksPerPage;
    const currentBooks = books.slice(indexOfFirstBook, indexOfLastBook);
    const paginate = (pageNumber) => setCurrentPage(pageNumber);

    return (
        <>
            <div className="join grid grid-cols-2 max-w-md mx-auto mb-4">
                <button className="join-item btn btn-outline"
                        onClick={() => paginate(currentPage - 1)}
                        disabled={currentPage === 1}>
                    Previous page
                </button>
                <button className="join-item btn btn-outline"
                        onClick={() => paginate(currentPage + 1)}
                        disabled={currentPage === totalPages}>
                    Next
                </button>
            </div>
            <div className={"flex flex-wrap backdrop-blur-2xl"}>
                {currentBooks.map((book) => (
                    <BookCard
                        key={book.id}
                        book={book}
                        handleUpdateBook={handleUpdateBook}
                        handleDeleteBook={handleDeleteBook}/>
                ))}
            </div>
        </>
    );
};

BookTable.propTypes = {
    books: PropTypes.array,
    handleUpdateBook: PropTypes.func,
    handleDeleteBook: PropTypes.func
}

export default BookTable;
