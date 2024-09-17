import PropTypes from "prop-types";

const BookCard = ({ book, handleUpdateBook }) => {

    const handleMarkAsRead = () => {
            const updatedBookData = {
                ...book,
                markedAsRead: !book.markedAsRead
            };
            handleUpdateBook(updatedBookData);
        };
    return (
        <div className="card card-side bg-base-400 shadow-xl w-64 h-64">
            <figure>
                <img
                    src="https://img.daisyui.com/images/stock/photo-1635805737707-575885ab0820.webp"
                    alt="Book"/>
            </figure>
            <div className="card-body">
                <h2 className="card-title text-lg">{book.volumeInfo?.title}</h2>
                <h6 className={"text-sm"}>{book.volumeInfo.authors}</h6>
                <p className={"text-sm"}>{book.volumeInfo.description}</p>
                <p className={"text-sm"}>{book.volumeInfo.pageCount}</p>
                <div className="card-actions justify-end">
                    <button className="btn btn-primary" onClick={handleMarkAsRead}>{book.markedAsRead ? "Unmark as read" : "Mark as read"}</button>
                    <button className={"btn btn-circle text-sm"}>X</button>
                </div>
            </div>
        </div>
    );
};

BookCard.propTypes = {
    book: PropTypes.shape ({
        volumeInfo: PropTypes.shape ({
            title: PropTypes.string,
            authors: PropTypes.arrayOf(PropTypes.string),
            description: PropTypes.string,
            pageCount: PropTypes.number,
        }),
        markedAsRead: PropTypes.bool
    }).isRequired,
    handleUpdateBook: PropTypes.func.isRequired
}

BookCard.deafaultProps = {
    book: {
        volumeInfo: {
            title: "No Title",
            authors: ["No Authors"],
            description: "No Description",
            pageCount: 0,
        },
        markedAsRead : false
    },
    handleUpdateBook: () => {}
}
export default BookCard;
