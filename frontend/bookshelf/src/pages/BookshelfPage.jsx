import React, {useEffect, useState} from 'react';
import BookTable from "../components/BookTable.jsx";
import {
    deleteBookFromBookshelf,
    fetchUserBookshelfOnLoad,
    postNewBookFromBookshelf,
    updateBookFromBookshelf
} from "../utility/bookactions.js";
import AddBookFromBookshelfModal from "../components/AddBookFromBookshelfModal.jsx";

const BookshelfPage = () => {
    const [data, setData] = useState({
        books: [],
        totalPagesRead: 0,
        totalBooksRead: 0,
        totalBooksInBookshelf:0,
    });
    const [isModalOpen, setIsModalOpen] = useState(false);

    useEffect(() => {
        const loadData = async () => {
            const bookshelfDTO = await fetchUserBookshelfOnLoad();
            setData(bookshelfDTO);
        }
        loadData();
    }, []);

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

    return (
        <div className={"flex flex-row"}>
            <div className={"w-1/3 p-4"}>
                <div className={"bg-green-100 min-w-screen min-h-24 text-center py-2 border"}>
                    <h2 className={"text-2xl"}>Pages Read</h2>
                    <h2 className={"text-2xl"}>{data.totalPagesRead}</h2>
                </div>
                <div className={"bg-cyan-100 min-w-screen min-h-24 text-center py-2 border"}>
                    <h2 className={"text-2xl"}>Books Read</h2>
                    <h2 className={"text-2xl"}>{data.totalBooksRead}</h2>
                </div>
                <div className={"bg-orange-100 min-w-screen min-h-24 text-center py-2 border"}>
                    <h2 className={"text-2xl"}>Books in Bookshelf</h2>
                    <h2 className={"text-2xl"}>{data.totalBooksInBookshelf}</h2>
                </div>
                <div className={"text-center pt-4"}>
                    <button className={"btn min-h-12 py-2 border"} onClick={() => setIsModalOpen(true)}>Add new Book</button>
                </div>
            </div>
            <div className="divider divider-horizontal divider-primary"></div>
            <div className={"w-2/3 p-4"}>
                <BookTable
                    books={data.books}
                    handleUpdateBook={handleUpdateBook}
                    handleDeleteBook={handleDeleteBook}
                    isBookshelfPage={true}
                />
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
