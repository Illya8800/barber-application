import {combineReducers} from "redux";
import imageReducer from "./image/reducer";

const rootReducer = combineReducers({image: imageReducer});

export type RootState = ReturnType<typeof rootReducer>;
export default rootReducer;