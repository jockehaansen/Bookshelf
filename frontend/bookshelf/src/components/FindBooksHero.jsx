import React from 'react';
import PropTypes from "prop-types";

const FindBooksHero = ({ handleFindNewBooksClick }) => {
    return (
        <div
            className="hero flex-1 my-2"
            style={{
                backgroundImage: "url(https://images.pexels.com/photos/207662/pexels-photo-207662.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1)",
            }}>
            <div className="hero-overlay bg-opacity-60"></div>
            <div className="hero-content text-neutral-content text-center">
                <div className="max-w-md">
                    <h1 className="mb-5 text-5xl font-bold">Hello there</h1>
                    <p className="mb-5">
                        Explore millions of books from around the world with the Google Books API.
                        Search by title, author, or genre to discover new reads. All book information
                        is provided directly from Google Books. Find new exciting books today!
                    </p>
                    <button className="btn btn-primary" onClick={handleFindNewBooksClick}>Find new Books</button>
                </div>
            </div>
        </div>
    );
};

FindBooksHero.propTypes = {
    handleFindNewBooksClick: PropTypes.func,
}

export default FindBooksHero;
