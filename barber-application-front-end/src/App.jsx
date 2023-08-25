import React from 'react';
import {BrowserRouter, Route, Routes} from 'react-router-dom';
import HomePage from './Page/HomePage';
import ErrorPage from './Page/ErrorPage';
import ImagesPage from './Page/image/ImagesPage';
import AdminNavbar from './Component/Navbar/AdminNavbar';
import './App.css';
import GuestNavbar from "./Component/Navbar/GuestNavbar";

function App() {


    return (
        <BrowserRouter>
            <GuestNavbar />
            <Routes>
                <Route path="/" element={<HomePage />} />
                <Route path="/images" element={<ImagesPage />} />
                <Route path="/barbers" element={<HomePage />} />
                <Route path="/payments" element={<ImagesPage />} />
                <Route path="/haircuts" element={<HomePage />} />
                <Route path="/clients" element={<ImagesPage />} />
                <Route path="/orders" element={<HomePage />} />

                <Route path="*" element={<ErrorPage />} />
            </Routes>
            <AdminNavbar />
        </BrowserRouter>
    );
}

export default App;