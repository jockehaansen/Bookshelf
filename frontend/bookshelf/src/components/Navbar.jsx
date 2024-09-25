import React from "react";
import { FaEnvelope, FaGithub, FaLinkedin } from "react-icons/fa";
import { IoDocumentText } from "react-icons/io5";

const Navbar = () => {
    const email = "jocke.hansen@outlook.com";
    return (
        <nav className=" mb-28 md:mb-5 lg:mb-5 flex items-center justify-center lg:justify-end my-5">
            <ul className="flex space-x-1 rounded-lg sm:space-x-0">
                <li></li>
                <li className="pr-8">
                    <a href={`mailto:${email}`}>
                        <FaEnvelope className="text-3xl" />
                    </a>
                </li>
                <li className="pr-8">
                    <a href="https://github.com/jockehaansen" target="_blank">
                        <FaGithub className="text-3xl" />
                    </a>
                </li>
                <li className="pr-8">
                    <a
                        href="https://www.linkedin.com/in/joakim-lykke-hansen/"
                        target="_blank"
                    >
                        <FaLinkedin className="text-3xl" />
                    </a>
                </li>
                <li className="lg:pr-8">
                    <a href="mycv.pdf" download="mycv.pdf">
                        <IoDocumentText className="text-3xl" />
                    </a>
                </li>
            </ul>
        </nav>
    );
};

export default Navbar;
