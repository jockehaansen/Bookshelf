import HomePage from "./pages/HomePage.jsx";
import { BrowserRouter as Router, Route, Routes} from "react-router-dom";
import BookshelfPage from "./pages/BookshelfPage.jsx";
import NewBooksPage from "./pages/NewBooksPage.jsx";


function App() {
  return (
    <Router>
       <Routes>
           <Route path={"/bookshelf"} element={<BookshelfPage />} />
           <Route path={"/books"} element={<NewBooksPage />} />
           <Route path={"/"} element={<HomePage />} />
       </Routes>
    </Router>
  )
}

export default App
