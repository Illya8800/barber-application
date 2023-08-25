import React from 'react';

const TableHeaderColumn = ({theadCaptionList}) => {

    return (
        <thead>
            <tr>
                {theadCaptionList.map((theadCaption) => (<th key={theadCaption.id}>{theadCaption.value}</th>))}
            </tr>
        </thead>
    );
};

export default TableHeaderColumn;