import React from 'react';
import {useNavigate} from "react-router-dom";
import { RiMenu2Fill } from 'react-icons/ri'


const NavigationDropdown = () => {
    const navigate = useNavigate()

    const handleHomeClick = () => {
        navigate("/")
        document.activeElement.blur();
    }

    const handleBookshelfClick = () => {
        navigate("/bookshelf")
        document.activeElement.blur();
    }

    const handleFindNewBooksClick = () => {
        navigate("/books")
        document.activeElement.blur();
    }
    return (
        <div className="dropdown dropdown-bottom">
            <div tabIndex={0} role={"button"} className={"btn btn-square btn-ghost"}><RiMenu2Fill /></div>
            {/*tabindex for workaround in safari browsers that do not support focus due to current bug*/}
            <ul tabIndex="0" className="dropdown-content menu bg-base-100 rounded-box z-[1] w-52 p-2 shadow">
                <li>
                    <button onClick={handleHomeClick}>Home</button>
                </li>
                <li>
                    <button onClick={handleBookshelfClick}>Bookshelf</button>
                </li>
                <li>
                    <button onClick={handleFindNewBooksClick}>Find New Books</button>
                </li>
            </ul>
        </div>
    );
};

export default NavigationDropdown;
