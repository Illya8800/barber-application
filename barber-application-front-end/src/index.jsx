import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import rootReducer from "./redux/rootReducer";
import {configureStore} from '@reduxjs/toolkit'
import thunk from "redux-thunk";
import {Provider} from "react-redux";


const store = configureStore({
    reducer: rootReducer,
    middleware: [thunk],
})

// const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;

const root = ReactDOM.createRoot(
    document.getElementById('root')
);
root.render(
    <Provider store={store}>
        <App/>
    </Provider>
);

// reportWebVitals();
