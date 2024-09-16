import React from 'react';
import PropTypes from "prop-types";

const BookCard = ({ book }) => {
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
                <div className="card-actions justify-end">
                    <button className="btn btn-primary">Mark as read</button>
                    <button className={"btn btn-circle text-sm"}>X</button>
                </div>
            </div>
        </div>
    );
};

BookCard.propTypes = {
    book: PropTypes.shape ({
        volumeInfo: PropTypes.shape ({
            title: "String",
            authors: PropTypes.arrayOf(PropTypes.string),
            description: PropTypes.string,
        })
    })
}

BookCard.deafaultProps = {
    book: {
        volumeInfo: {
            title: "No Title",
            authors: ["No Authors"],
            description: "No Description"
        }
    }
}
export default BookCard;
