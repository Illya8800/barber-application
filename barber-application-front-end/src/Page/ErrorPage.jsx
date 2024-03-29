import React from 'react';
import {Helmet} from "react-helmet";

function ErrorPage() {
    return (
        <>
            <Helmet>
                <title>Error page</title>
            </Helmet>
            <div className="d-flex align-items-center justify-content-center vh-100 bg-primary">
                <h1 className="display-1 fw-bold text-white">404</h1>
            </div>
        </>
    );
}

export default ErrorPage;