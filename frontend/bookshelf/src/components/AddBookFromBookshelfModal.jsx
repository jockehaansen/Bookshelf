import React, {useEffect, useState} from 'react';
import PropTypes from "prop-types";

const AddBookFromBookshelfModal = ({ onSave, isOpen, onClose }) => {

    const [volumeInfo, setVolumeInfo] = useState({
        title:'',
        subtitle:'',
        authors:[],
        publishedDate:'',
        description:'',
        pageCount:0,
    });

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setVolumeInfo({
            ...volumeInfo,
            [name]: value,
        });
    };

    const handleSave = () => {
        onSave(volumeInfo);
    };

    const handleClose = () => {
        const modal = document.getElementById('add_book_modal');
        if (modal) modal.close();
        onClose();
    }

    useEffect(() => {
        const modal = document.getElementById('add_book_modal');
        if (modal) {
            if (isOpen) modal.showModal();
            else modal.close();
        }
    }, [isOpen]);

    if (!isOpen) return null;

    return (
        <dialog id="add_book_modal" className="modal modal-bottom sm:modal-middle">
            <div className={"modal-box flex flex-col p-4"}>
                <h3>Add New Book</h3>
                <label htmlFor={"title"} className={"label"}>Title:</label>
                <input type={"text"} name={"title"} value={volumeInfo.title} onChange={handleInputChange} className={"input input-bordered w-full "}/>
                <label htmlFor={"subtitle"} className={"label"}>Subtitle:</label>
                <input type={"text"} name={"subtitle"} value={volumeInfo.subtitle} onChange={handleInputChange} className={"input input-bordered w-full "}/>
                <label htmlFor={"authors"} className={"label"}>Authors:</label>
                <input type={"list"} name={"authors"} value={volumeInfo.authors.join(',')} onChange={(e) =>
                    setVolumeInfo({
                        ...volumeInfo,
                        authors: e.target.value.split(','),
                    })} className={"input input-bordered w-full "}/>
                <label htmlFor={"publishedDate"} className={"label"}>Published::</label>
                <input type={"date"} name={"publishedDate"} value={volumeInfo.publishedDate} onChange={handleInputChange} className={"input input-bordered w-full "}/>
                <label htmlFor={"description"} className={"label"}>Description:</label>
                <input type={"text"} name={"description"} value={volumeInfo.description} onChange={handleInputChange} className={"input input-bordered w-full "}/>
                <label htmlFor={"pageCount"} className={"label"}>Pages:</label>
                <input type={"text"} name={"pageCount"} value={volumeInfo.pageCount} onChange={handleInputChange} className={"input input-bordered w-full "}/>
                <div className={"m-auto"}>
                    <button className={"btn btn-square mx-8 my-2"} onClick={handleSave}>
                        <svg xmlns="http://www.w3.org/2000/svg" className={"h-6 w-6"} fill={"none"} stroke={"currentColor"} viewBox="0 0 24 24">
                            <path d="M20.285 2l-11.285 11.567-5.286-5.011-3.714 3.716 9 8.728 15-15.285z"/>
                        </svg>
                    </button>
                    <button className="btn btn-square mx-8 my-2" onClick={handleClose}>
                        <svg
                            xmlns="http://www.w3.org/2000/svg"
                            className="h-6 w-6"
                            fill="none"
                            viewBox="0 0 24 24"
                            stroke="currentColor">
                            <path
                                strokeLinecap="round"
                                strokeLinejoin="round"
                                strokeWidth="2"
                                d="M6 18L18 6M6 6l12 12"/>
                        </svg>
                    </button>
                </div>
            </div>
        </dialog>
    );
};

AddBookFromBookshelfModal.propTypes = {
    onSave: PropTypes.func.isRequired,
    isOpen: PropTypes.bool.isRequired,
    onClose: PropTypes.func.isRequired
}

export default AddBookFromBookshelfModal;
