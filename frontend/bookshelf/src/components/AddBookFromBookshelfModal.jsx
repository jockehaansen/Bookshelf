import React, {useEffect, useState} from 'react';

const AddBookFromBookshelfModal = ({ onSave, isOpen }) => {

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

    useEffect(() => {
        const modal = document.getElementById('my_modal_5');
        if (modal) {
            if (isOpen) modal.showModal();
            else modal.close();
        }
    }, [isOpen]);

    if (!isOpen) return null;

    return (
        <dialog id="my_modal_5" className="modal modal-bottom sm:modal-middle">
            <div className={"modal-box"}>
                <h3>Add New Book</h3>
                <label htmlFor={"title"}>Title:</label>
                <input type={"text"} name={"title"} value={volumeInfo.title} onChange={handleInputChange}/>
                <label htmlFor={"subtitle"}>Subtitle:</label>
                <input type={"text"} name={"subtitle"} value={volumeInfo.subtitle} onChange={handleInputChange}/>
                <label htmlFor={"authors"}>Authors:</label>
                <input type={"list"} name={"authors"} value={volumeInfo.authors.join(',')} onChange={(e) =>
                    setVolumeInfo({
                        ...volumeInfo,
                        authors: e.target.value.split(','),
                    })}/>
                <label htmlFor={"publishedDate"}>Published::</label>
                <input type={"text"} name={"publishedDate"} value={volumeInfo.publishedDate} onChange={handleInputChange}/>
                <label htmlFor={"description"}>Description:</label>
                <input type={"text"} name={"description"} value={volumeInfo.description} onChange={handleInputChange}/>
                <label htmlFor={"pageCount"}>Pages:</label>
                <input type={"text"} name={"pageCount"} value={volumeInfo.pageCount} onChange={handleInputChange}/>
                <button onClick={handleSave}>Save</button>
            </div>
        </dialog>
    );
};

export default AddBookFromBookshelfModal;
