import React, {useState} from 'react';
import BookTable from "../components/BookTable.jsx";
import {fetchGoogleBooksBySearch} from "../utility/bookactions.js";

const BrowseBooksPage = () => {
    const [data, setData] = useState([]);
    const [ input, setInput ] = useState('')

    const handleBookSearch = async () => {
        console.log("Clicked search with", input)
        const data = await fetchGoogleBooksBySearch(input)
        setData(data.items || [])
        console.log("Data returned to browse page", data)
    }

    return (
        <div className={"flex flex-row"}>
            <div className={"w-1/3 p-4 flex flex-col"}>
                <label htmlFor={'book-search'}></label>
                <input
                    type={"text"}
                    name={"book-search"}
                    className={"h-12"}
                    placeholder={"Search for title, author or genre"}
                    value={input}
                    onChange={(e) => setInput(e.target.value)}
                ></input>
                <button className={"btn btn-primary max-w-24"} onClick={handleBookSearch}>Search</button>
            </div>
            <div className={"divider divider-horizontal divider-primary"}></div>
            <div className={"w-2/3 p-4"}>
                <BookTable books={data}/>
            </div>
        </div>
    );
};

export default BrowseBooksPage;
