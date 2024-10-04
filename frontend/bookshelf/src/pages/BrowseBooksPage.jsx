import React, {useState} from 'react'
import { toast, ToastContainer } from 'react-toastify'
import 'react-toastify/dist/ReactToastify.css'
import BookTable from "../components/BookTable.jsx"
import {fetchGoogleBooksBySearch, saveBookFromGoogleBooks} from "../utility/bookactions.js"

const BrowseBooksPage = () => {
    const [data, setData] = useState([])
    const [ input, setInput ] = useState('')

    const handleBookSearch = async () => {
        try{
            const data = await fetchGoogleBooksBySearch(input)
            setData(data.items || [])
        } catch (error){
            toast.error("Failed to search books")
        }

    }

    const handleAddBookToBookshelf = async (book) => {
        try{
            await saveBookFromGoogleBooks(book)
            toast.success("Book saved successfully!")
        } catch (error){
            toast.error("Failed to save book", book)
        }
    }

    return (
        <div className={"flex flex-row"}>
            <div className={"w-1/3 p-4 flex flex-col "}>
                <input
                    type={"text"}
                    name={"book-search"}
                    id={"book-search"}
                    className={"h-12"}
                    placeholder={"Search for title, author or genre"}
                    value={input}
                    onChange={(e) => setInput(e.target.value)}
                ></input>
                <div className={"text-center pt-4"}>
                    <button className={"btn min-h-12 py-2 border"} onClick={handleBookSearch}>Search</button>
                </div>
            </div>
            <div className={"divider divider-horizontal divider-primary"}></div>
            <div className={"w-2/3 p-4"}>
                <BookTable
                    books={data}
                    handleAddBookToBookshelf={handleAddBookToBookshelf}
                    isBookshelfPage={false}
                />
            </div>
            <ToastContainer />
        </div>
    );
};

export default BrowseBooksPage;
