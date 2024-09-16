import React, {useEffect, useState} from 'react';
import BookTable from "../components/BookTable.jsx";
import {useNavigate} from "react-router-dom";
import {fetchUserBookshelfOnLoad, postNewBookFromBookshelf} from "../utility/bookactions.js";
import AddBookFromBookshelfModal from "../components/AddBookFromBookshelfModal.jsx";

const BookshelfPage = () => {
    const [data, setData] = useState([]);
    const navigate = useNavigate();
    const [isModalOpen, setIsModalOpen] = useState(false);


    useEffect(() => {
        const loadData = async () => {
            const books = await fetchUserBookshelfOnLoad();
            setData(books);
        }
        loadData();
    }, []);

    const handleHomeClick = () =>{
        navigate("/");
    }
    const handleAddNewBookClick = () => {
        setIsModalOpen(true)
    }
    const handleBookSave = async (newBook) => {
        const data = await postNewBookFromBookshelf(newBook);
        setData(data);
        setIsModalOpen(false)
    }

    const handleFindNewBooksClick = () => {
        navigate("/books")
    }
    return (
        <div className={"flex flex-row w-screen h-screen"}>
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
                <div>
                    <AddBookFromBookshelfModal
                        onSave={handleBookSave}
                        isOpen={isModalOpen}
                        onClose={() => setIsModalOpen(false)}
                    />
                </div>
            </div>
        </div>
    );
};

export default BookshelfPage;
