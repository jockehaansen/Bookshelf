import HomePage from "./pages/HomePage.jsx";
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import BookshelfPage from "./pages/BookshelfPage.jsx";
import NewBooksPage from "./pages/NewBooksPage.jsx";
import BrowseBooksPage from "./pages/BrowseBooksPage.jsx";
import Navbar from "./components/Navbar.jsx";
import Footer from "./components/Footer.jsx";

function App() {
  return (
      <Router>
          <div className="flex flex-col min-h-screen min-w-screen">
              <Navbar />
              <main className="flex-grow">
                  <Routes>
                      <Route path={"/bookshelf"} element={<BookshelfPage />} />
                      <Route path={"/books"} element={<NewBooksPage />} />
                      <Route path="/books/:search" element={<BrowseBooksPage />} />
                      <Route path={"/"} element={<HomePage />} />
                  </Routes>
              </main>
              <Footer />
          </div>
      </Router>
  )
}

export default App
