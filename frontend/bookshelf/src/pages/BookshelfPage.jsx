import React, {useEffect, useState} from 'react';
import BookTable from "../components/BookTable.jsx";
import {useNavigate} from "react-router-dom";

const BookshelfPage = () => {
    const [data, setData] = useState([]);
    const navigate = useNavigate();
    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await fetch("http://localhost:8080/bookshelf", {
                    method: "GET",
                });
                const jsonData = await response.json();
                setData(jsonData);
            } catch (error) {
                console.error("Error fetching data", error);
            }
        };

        fetchData();
    }, []);

    const handleHomeClick = () =>{
        navigate("/");
    }
    const handleAddNewBookClick = () => {
        //TODO open window to add a new book and then post
    }

    const handleFindNewBooksClick = () => {
        navigate("/books")
    }
    return (
        <div className={"flex flex-row w-screen"}>
            <div className={"w-1/3 p-4"}>
                <ul className={"menu bg-base-200 rounded-box w-56"}>
                    <li><a onClick={handleHomeClick}>Home</a></li>
                    <li><a onClick={handleAddNewBookClick}>Add New Book</a></li>
                    <li><a onClick={handleFindNewBooksClick}>Find New Books</a></li>
                </ul>
                <div className={"mt-4"}>
                    <p>pages read</p>
                    <p>books read</p>
                </div>
            </div>
            <div className="divider divider-horizontal divider-primary"></div>
            <div className={"w-2/3 p-4"}>
                <BookTable books={data}/>
            </div>
        </div>
    );
};

export default BookshelfPage;
