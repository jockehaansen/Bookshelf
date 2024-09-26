import React from "react";
import NavigationDropdown from "./NavigationDropdown.jsx";
import {useNavigate} from "react-router-dom";

const Navbar = () => {
    const navigate = useNavigate()

    const handleHomeClick = () => {
        navigate("/")
    }
    return (
        <div className="navbar bg-base-100 mb-4">
            <div className="flex-none">
                <NavigationDropdown/>
            </div>
            <div className="flex-1">
                <button className="btn btn-ghost text-xl" onClick={handleHomeClick}>Home</button>
            </div>
            <div className="divider divider-vertical divider-primary"></div>
        </div>
    );
};

export default Navbar;
