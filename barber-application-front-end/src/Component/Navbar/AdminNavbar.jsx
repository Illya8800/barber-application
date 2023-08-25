import React, {useState} from 'react';
import '../../style/styles.css';
import {
    AdminNav,
    MobileIcon,
    NavbarContainer,
    NavBtn,
    NavBtnLink,
    NavItem,
    NavLinks,
    NavLogo,
    NavMenu
} from "./NavbarElements";
import {FaBars} from "react-icons/fa";

import {BsFillPeopleFill, BsImages} from "react-icons/bs";
import {MdOutlinePayments} from "react-icons/md";
import {GiRazor} from "react-icons/gi";
import {LiaMoneyCheckAltSolid} from "react-icons/lia";
import {IoIosMan} from "react-icons/io";
import {FaScrewdriverWrench} from "react-icons/fa6";
import AdminSidebar from "../Sidebar/AdminSidebar";
import {StyleSheetManager} from "styled-components";

function AdminNavbar() {

    const [isOpenAdminSidebar, setIsOpenAdminSidebar] = useState(false);
    const navbarStyleProps = {maxWidthBeforeCollapse: 1100};
    const toggleAdminSidebar = () => setIsOpenAdminSidebar(!isOpenAdminSidebar);

    return (
        <>
            <StyleSheetManager shouldForwardProp={(prop) => prop !== 'maxWidthBeforeCollapse' && prop !== 'isOpen'}>
                <AdminNav {...navbarStyleProps}>
                    <NavbarContainer {...navbarStyleProps}>
                        <NavLogo to='/'><FaScrewdriverWrench size={48}/></NavLogo>
                        <MobileIcon onClick={toggleAdminSidebar} {...navbarStyleProps}>
                            <FaBars/>
                        </MobileIcon>
                        <NavMenu {...navbarStyleProps}>
                            <NavItem>
                                <NavLinks to="/images">Images<span className="navbar-logo"><BsImages/></span></NavLinks>
                            </NavItem>
                            <NavItem>
                                <NavLinks to="/barbers">Barbers<span className="navbar-logo">{
                                    <IoIosMan/>}</span></NavLinks>
                            </NavItem>
                            <NavItem>
                                <NavLinks to="/payments">Payments<span className="navbar-logo">{
                                    <MdOutlinePayments/>}</span></NavLinks>
                            </NavItem>
                            <NavItem>
                                <NavLinks to="/haircuts">Haircuts<span className="navbar-logo">{
                                    <GiRazor/>}</span></NavLinks>
                            </NavItem>
                            <NavItem>
                                <NavLinks to="/clients">Clients<span className="navbar-logo">{
                                    <BsFillPeopleFill/>}</span></NavLinks>
                            </NavItem>
                            <NavItem>
                                <NavLinks to="/orders">Orders<span className="navbar-logo">{
                                    <LiaMoneyCheckAltSolid/>}</span></NavLinks>
                            </NavItem>
                        </NavMenu>
                        <NavBtn {...navbarStyleProps}>
                            <NavBtnLink to='/'>Log out</NavBtnLink>
                        </NavBtn>
                    </NavbarContainer>
                </AdminNav>
                <AdminSidebar isOpen={isOpenAdminSidebar} toggle={toggleAdminSidebar}/>
            </StyleSheetManager>
        </>
    );
}

export default AdminNavbar;