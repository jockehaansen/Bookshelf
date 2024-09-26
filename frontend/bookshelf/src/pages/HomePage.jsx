import React from 'react';
import { useNavigate } from "react-router-dom";
import BookshelfHero from "../components/BookshelfHero.jsx";
import FindBooksHero from "../components/FindBooksHero.jsx";

const HomePage = () => {
    const navigate = useNavigate();

    const handleBookshelfClick = () => {
        navigate("/bookshelf")
    }

    const handleFindNewBooksClick = () => {
        navigate("/books")
    }
    return (
        <div className={"text-center flex flex-col max-h-screen h-screen-80"}>
            <BookshelfHero handleBookshelfClick={handleBookshelfClick}/>
            <FindBooksHero handleFindNewBooksClick={handleFindNewBooksClick}/>
        </div>
    );
};

export default HomePage;
