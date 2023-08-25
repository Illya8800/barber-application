import {ImageActionTypes} from "./type";
import {ImageAction} from "./action";

const initialState = {
    isOpenAddPopup: false,
    isLoading: false,
    images: [],
    image: null,
    error: null,
}


const imageReducer = (state = initialState, action: ImageAction) => {
    switch (action.type) {
        case ImageActionTypes.SHOW_MODAL_POPUP_ADD_IMAGE:
            return {
                ...state,
                isOpenAddPopup: action.payload,
            }
        default:
            return state;
    }
}

export default imageReducer;