import React from 'react';

const ImagesTableContent = ({cellsContent, tableActionButton}) => {
    return (
        <tbody>
        {cellsContent.map(imageDto => (
            <tr key={imageDto.id}>
                <td>{imageDto.id}</td>
                <td>{imageDto.name}</td>
                <td><img width={139} height={139} src={`data:image/jpeg;base64,${imageDto.image.bytes}`}/></td>
                {/*
                // <td className={btnGroup}>
                    <CustomLink url={getEditDepartmentPageUri(department.id)} textLink="Edit"/>
                    <ActionButton action={() => tableActionButton(department.id)} textButton="Delete" />
                    <CustomLink url={getAllEmployeesByDepartmentPageUri(department.id)} textLink="List of employees"/>
                </td>*/}
            </tr>
        ))}
        </tbody>
    );
}

export default ImagesTableContent;