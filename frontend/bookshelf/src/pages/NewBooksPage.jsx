import React, {useState} from 'react';
import {useNavigate} from "react-router-dom";

const NewBooksPage = () => {
    const navigate = useNavigate();
    const [ input, setInput ] = useState('')
    const handleBookSearch = () => {
        if(input){
            navigate(`/books/${encodeURIComponent(input)}`)
        }
    }

    return (
        <div>
            <h1 className={"text-4xl"}>New Books</h1>
            <label htmlFor={'book-search'}>Search:</label>
            <input
                type={"text"}
                name={"book-search"}
                placeholder={"Search for title, author or genre"}
                value={input}
                onChange={(e) => setInput(e.target.value)}
            ></input>
            <button className={"btn btn-primary"} onClick={handleBookSearch}>Search</button>
        </div>
    );
};

export default NewBooksPage;
