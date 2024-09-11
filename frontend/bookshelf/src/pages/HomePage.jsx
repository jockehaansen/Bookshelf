import React from 'react';
import { useNavigate } from "react-router-dom";

const HomePage = () => {
    const navigate = useNavigate();

    const handleBookshelfClick = () => {
        navigate("/bookshelf")
    }

    const handleFindNewBooksClick = () => {
        navigate("/books")
    }
    return (
        <div className={"text-center"}>
            <h1 className={"text-4xl"}>Hello!</h1>
            <button className={"btn btn-primary"} onClick={handleBookshelfClick}>Your Bookshelf</button>
            <button className={"btn btn-secondary"} onClick={handleFindNewBooksClick}>Find New Books</button>
        </div>
    );
};

export default HomePage;
