import React from 'react';

const BookCard = ({ book }) => {
    return (
        <div className="card card-side bg-base-100 shadow-xl">
            <figure>
                <img
                    src="https://img.daisyui.com/images/stock/photo-1635805737707-575885ab0820.webp"
                    alt="Book"/>
            </figure>
            <div className="card-body">
                <h2 className="card-title">{book.volumeInfo.title}</h2>
                <h6 className={"card-title"}>{book.volumeInfo.authors}</h6>
                <p>{book.volumeInfo.description}</p>
                <div className="card-actions justify-end">
                    <button className="btn btn-primary">Button</button>
                </div>
            </div>
        </div>
    );
};

export default BookCard;
