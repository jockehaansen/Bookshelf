import React from 'react'

const Footer = () => {
    return (
        <footer>
            <div className="w-full bg-neutral-content text-center p-4">
                <div className="container mx-auto">
                    <div className={"grid grid-cols-3 gap-4"}>
                        <div>
                            <h4 className={"text-xl font-bold"}>Bookshelf</h4>
                            <ul>
                                <li><a href={"https://github.com/jockehaansen/Bookshelf"} target="_blank">GitHub</a>
                                </li>
                                <li><a href={"https://hub.docker.com/repositories/jockehansen"}
                                       target={"_blank"}>Docker</a></li>
                            </ul>
                        </div>
                        <div>
                            <ul>
                                <h4 className={"text-xl font-bold"}>Documentation</h4>
                                <li><a href={"https://developers.google.com/books"} target="_blank">Google Books API</a>
                                </li>
                            </ul>
                        </div>
                        <div>
                            <h4 className={"text-xl font-bold"}>Privacy Policy</h4>
                            <p>Saved books are stored in the cloud</p>
                        </div>
                    </div>
                    <div className="mt-4">
                        <p>© 2024 Bookshelf. All rights reserved.</p>
                        <p>Powered by Google Books API</p>
                        <div className="flex justify-center space-x-4 mt-2">
                            <a href="https://github.com/jockehaansen" target="_blank">GitHub</a>
                            <a href="https://www.linkedin.com/in/joakim-lykke-hansen/" target="_blank">LinkedIn</a>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
    )
}

export default Footer