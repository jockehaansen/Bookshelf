import React from 'react';
import PropTypes from "prop-types";

const BookshelfHero = ({ handleBookshelfClick }) => {
    return (
        <div
            className="hero flex-1"
            style={{
                backgroundImage: "url(https://images.pexels.com/photos/694740/pexels-photo-694740.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1)",
            }}>
            <div className="hero-overlay bg-opacity-60"></div>
            <div className="hero-content text-neutral-content text-center">
                <div className="max-w-md">
                    <h1 className="mb-5 text-5xl font-bold">Bookshelf</h1>
                    <p className="mb-5">
                        Your personal Bookshelf is where you can store and organize all your favorite books.
                        Easily add new books, update details, or remove ones you no longer need. Keep track of your
                        reading progress and revisit your most cherished titles anytime. Start building your collection now!
                    </p>
                    <button className="btn btn-primary" onClick={handleBookshelfClick}>Visit your Bookshelf</button>
                </div>
            </div>
        </div>
    );
};

BookshelfHero.propTypes = {
    handleBookshelfClick: PropTypes.func,
}

export default BookshelfHero;
