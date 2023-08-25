import React, {useState} from 'react';
import '../../style/styles.css';
import {GuestNav, MobileIcon, NavbarContainer, NavItem, NavLinks, NavLogo, NavMenu} from "./NavbarElements";
import {FaBars} from "react-icons/fa";
import logoImage from '../../images/logo.svg';

import {BsFillPeopleFill} from "react-icons/bs";
import {MdOutlinePayments} from "react-icons/md";
import {GiRazor} from "react-icons/gi";
import {LiaMoneyCheckAltSolid} from "react-icons/lia";
import {IoIosMan} from "react-icons/io";

import GuestSidebar from "../Sidebar/GuestSidebar";
import {StyleSheetManager} from "styled-components";

function GuestNavbar() {
    const [isOpenGuestSidebar, setIsOpenGuestSidebar] = useState(false);
    const toggleGuestSidebar = () => setIsOpenGuestSidebar(!isOpenGuestSidebar);
    const navbarStyleProps = {maxWidthBeforeCollapse: 865}

    return (
        <>
            <StyleSheetManager shouldForwardProp={(prop) => prop !== 'maxWidthBeforeCollapse' && prop !== 'isOpen'} >
                <GuestNav {...navbarStyleProps}>
                    <NavbarContainer {...navbarStyleProps}>
                        <NavLogo to='/'><img src={logoImage} alt="Logo"/></NavLogo>
                        <MobileIcon onClick={toggleGuestSidebar} {...navbarStyleProps}>
                            <FaBars/>
                        </MobileIcon>
                        <NavMenu {...navbarStyleProps}>

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
                    </NavbarContainer>
                </GuestNav>
                <GuestSidebar isOpen={isOpenGuestSidebar} toggle={toggleGuestSidebar}/>
            </StyleSheetManager>
        </>
    );
}

export default GuestNavbar;