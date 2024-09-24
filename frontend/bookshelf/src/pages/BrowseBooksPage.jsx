import React, {useEffect, useState} from 'react';
import BookTable from "../components/BookTable.jsx";
import {fetchGoogleBooksBySearch} from "../utility/bookactions.js";
import {useParams} from "react-router-dom";

const BrowseBooksPage = () => {
    const { search } = useParams()
    const [data, setData] = useState([]);

    useEffect(() => {
        return () => {
            const loadData = async () => {
                const books = await fetchGoogleBooksBySearch(search);
                setData(books.items);
                console.log(books.items)
            }
            loadData();
        };
    }, [search]);

    return (
        <div>
            <BookTable books={data} />
        </div>
    );
};

export default BrowseBooksPage;
