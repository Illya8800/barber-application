import React from 'react';
import {
    AdminIcon,
    CloseIcon,
    SidebarContainer,
    SidebarLink,
    SidebarMenu,
    SidebarRoute,
    SidebarWrapper,
    SideBtnWrap
} from "./SidebarElements";

function AdminSidebar({isOpen, toggle}) {
    return (
        <SidebarContainer isOpen={isOpen} onClick={toggle}>
            <AdminIcon onClick={toggle}>
                <CloseIcon/>
            </AdminIcon>
            <SidebarWrapper>
                <SidebarMenu>
                    <SidebarLink to="/images">Images</SidebarLink>
                    <SidebarLink to="/barbers">Barbers</SidebarLink>
                    <SidebarLink to="/payments">Payments</SidebarLink>
                    <SidebarLink to="/haircuts">Haircuts</SidebarLink>
                    <SidebarLink to="/clients">Clients</SidebarLink>
                    <SidebarLink to="/orders">Orders</SidebarLink>
                </SidebarMenu>
                <SideBtnWrap>
                    <SidebarRoute to="/logout">
                        Log out
                    </SidebarRoute>
                </SideBtnWrap>
            </SidebarWrapper>
        </SidebarContainer>
    );
}

export default AdminSidebar;