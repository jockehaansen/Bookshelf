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
                    <h1 className="mb-5 text-5xl font-bold">Hello there</h1>
                    <p className="mb-5">
                        Provident cupiditate voluptatem et in. Quaerat fugiat ut assumenda excepturi exercitationem
                        quasi. In deleniti eaque aut repudiandae et a id nisi.
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
