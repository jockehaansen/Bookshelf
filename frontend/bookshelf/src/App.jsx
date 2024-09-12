import HomePage from "./pages/HomePage.jsx";
import { BrowserRouter as Router, Route, Routes} from "react-router-dom";
import BookshelfPage from "./pages/BookshelfPage.jsx";
import NewBooksPage from "./pages/NewBooksPage.jsx";
import {useEffect, useState} from "react";


function App() {
    //fetch the User books
    const [data, setData] = useState([]);
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

    //fetch the Google books
  return (
    <Router>
       <Routes>
           <Route path={"/bookshelf"} element={<BookshelfPage books={data} />} />
           <Route path={"/books"} element={<NewBooksPage />} />
           <Route path={"/"} element={<HomePage />} />
       </Routes>
    </Router>
  )
}

export default App
