import React from 'react';
import TableHeaderColumn from "./TableHeaderColumn";

const Table = ({caption, tableColumns, TableDataComponent}) => {
    return (
        <table>
            <caption>{caption}</caption>
            <TableHeaderColumn theadCaptionList={tableColumns}/>
            {TableDataComponent}
        </table>
    );
};

export default Table;