import React from 'react';
import {CloseIcon, GuestIcon, SidebarContainer, SidebarLink, SidebarMenu, SidebarWrapper} from "./SidebarElements";

function GuestSidebar({isOpen, toggle}) {
    return (
        <SidebarContainer isOpen={isOpen} onClick={toggle}>
            <GuestIcon onClick={toggle}>
                <CloseIcon/>
            </GuestIcon>
            <SidebarWrapper>
                <SidebarMenu>
                    <SidebarLink to="services">
                        services
                    </SidebarLink>
                    <SidebarLink to="SignUp">
                        SignUp
                    </SidebarLink>
                </SidebarMenu>
            </SidebarWrapper>
        </SidebarContainer>
    );
}

export default GuestSidebar;