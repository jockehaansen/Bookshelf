import React from 'react'
import { FaReact } from 'react-icons/fa'
import { IoLogoJavascript } from 'react-icons/io'

const Footer = () => {
    return (
        <footer>
            <div className="w-full">
                <div className="bg-slate-700 bg-opacity-40 inset-x-0 bottom-0 h-12 w-screen text-black flex justify-center items-center md:flex-row flex-col">
                    <div>
                        Made with <FaReact className="inline text-cyan-500 md:h-6 md:w-6" />{' '}
                        and
                        <IoLogoJavascript className="inline text-yellow-300 ml-1 md:mr-10 md:h-6 md:w-6" />
                    </div>
                    <p>Copyright &copy; 2024 - Joakim Hansen</p>
                </div>
            </div>
        </footer>
    )
}

export default Footer
