import React from 'react';
import {PopupBody, PopupClose, PopupContainer, PopupContent, PopupTitle} from "./ImagePopupElemnts";
import Popup from "reactjs-popup";

const ImagePopup = ({isShow}) => {
    return (
        <>
            {isShow && (
                <Popup trigger={<button>dwadawda</button>}>
                    <PopupContainer>
                        <PopupBody>
                            <PopupContent>
                                <PopupClose>X</PopupClose>
                                <PopupTitle>Add Image</PopupTitle>
                                ANY CONTENT
                            </PopupContent>
                        </PopupBody>
                    </PopupContainer>
                </Popup>
            )}
        </>
    )
};

export default ImagePopup;