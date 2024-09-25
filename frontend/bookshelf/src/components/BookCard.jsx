import PropTypes from "prop-types";

const BookCard = ({ book, handleUpdateBook, handleDeleteBook }) => {

    const handleMarkAsRead = () => {
            const updatedBookData = {
                ...book,
                markedAsRead: !book.markedAsRead
            };
            handleUpdateBook(updatedBookData);
        };


    return (
        <div className="card card-side bg-base-400 shadow-xl w-96 h-64 overflow-hidden mr-2 my-2">
            <figure className="w-2/5 h-full">
                <img
                    className="object-cover w-full h-full"
                    src={book.volumeInfo.imageLinks ? book.volumeInfo.imageLinks.thumbnail : "https://images.pexels.com/photos/2422178/pexels-photo-2422178.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"}
                    alt="Book Thumbnail"
                />
            </figure>
            <div className="card-body w-3/5 p-4 flex flex-col justify-between">
                <div>
                    <h2 className="card-title text-lg font-bold">{book.volumeInfo?.title}</h2>
                    <h6 className="text-sm font-bold">{book.volumeInfo.authors}</h6>
                    <p className="text-sm">
                        {book.volumeInfo.description?.length > 120
                            ? book.volumeInfo.description.substring(0, 120) + "..."
                            : book.volumeInfo.description}
                    </p>
                    <p className="text-sm">{book.volumeInfo.pageCount} pages</p>
                </div>
                <div className="card-actions justify-end space-x-2">
                    <button className="btn btn-primary" onClick={handleMarkAsRead}>
                        {book.markedAsRead ? "Unmark as read" : "Mark as read"}
                    </button>
                    <button className="btn btn-circle btn-error text-sm" onClick={() => handleDeleteBook(book)}>
                        X
                    </button>
                </div>
            </div>
        </div>
    );
};

BookCard.propTypes = {
    book: PropTypes.shape({
        volumeInfo: PropTypes.shape({
            title: PropTypes.string,
            authors: PropTypes.arrayOf(PropTypes.string),
            description: PropTypes.string,
            pageCount: PropTypes.number,
            imageLinks: PropTypes.shape({
                thumbnail: PropTypes.string,
            })
        }),
        markedAsRead: PropTypes.bool
    }),
    handleUpdateBook: PropTypes.func.isRequired,
    handleDeleteBook: PropTypes.func.isRequired
}

BookCard.defaultProps = {
    book: {
        volumeInfo: {
            title: "No Title",
            authors: ["No Authors"],
            description: "No Description",
            pageCount: 0,
        },
        markedAsRead: false
    }
}
export default BookCard;
