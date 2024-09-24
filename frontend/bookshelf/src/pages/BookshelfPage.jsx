import React, {useEffect, useState} from 'react';
import BookTable from "../components/BookTable.jsx";
import {useNavigate} from "react-router-dom";
import {
    deleteBookFromBookshelf,
    fetchUserBookshelfOnLoad,
    postNewBookFromBookshelf,
    updateBookFromBookshelf
} from "../utility/bookactions.js";
import AddBookFromBookshelfModal from "../components/AddBookFromBookshelfModal.jsx";

const BookshelfPage = () => {
    const [data, setData] = useState([]);
    const navigate = useNavigate();
    const [isModalOpen, setIsModalOpen] = useState(false);


    useEffect(() => {
        console.log("In useEffect")
        const loadData = async () => {
            const books = await fetchUserBookshelfOnLoad();
            console.log("Fetched books:", books)
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

    const handleUpdateBook = async (bookToUpdate) => {
        const data = await updateBookFromBookshelf(bookToUpdate);
        setData(data);
    }

    const handleDeleteBook = async (bookToDelete) => {
        const data = await deleteBookFromBookshelf(bookToDelete);
        setData(data);
    }

    const handleFindNewBooksClick = () => {
        navigate("/books")
    }
    return (
        <div className={"flex flex-row w-screen h-screen"}>
            <div className={"w-1/3 p-4"}>
                <ul className={"menu bg-base-200 rounded-box w-56"}>
                    <li><button onClick={handleHomeClick}>Home</button></li>
                    <li><button onClick={handleAddNewBookClick}>Add New Book</button></li>
                    <li><button onClick={handleFindNewBooksClick}>Find New Books</button></li>
                </ul>
                <div className={"mt-4"}>
                    <p>pages read</p>
                    <p>books read</p>
                </div>
            </div>
            <div className="divider divider-horizontal divider-primary"></div>
            <div className={"w-2/3 p-4"}>
                <BookTable books={data} handleUpdateBook={handleUpdateBook} handleDeleteBook={handleDeleteBook}/>
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
