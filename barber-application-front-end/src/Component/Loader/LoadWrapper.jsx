import React from 'react';

function LoadWrapper({isLoading, children}) {
    return isLoading ? <div className="loader"></div> : <>{children}</>;
}

export default LoadWrapper;