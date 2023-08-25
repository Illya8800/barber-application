import {ImageActionTypes} from "./type";

interface IsShowModalPopupAddImage {
    type: ImageActionTypes.SHOW_MODAL_POPUP_ADD_IMAGE;
    payload: boolean;
}

export const setIsShowModalPopupAddImage = (isShow: boolean) => ({
    type: ImageActionTypes.SHOW_MODAL_POPUP_ADD_IMAGE,
    payload: isShow,
})

export type ImageAction = IsShowModalPopupAddImage
