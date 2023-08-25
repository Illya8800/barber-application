import styled from 'styled-components';
import {Link, NavLink} from "react-router-dom";

export const GuestNav = styled.nav`
  position: absolute;
  top: 0;
  left: 0;
  background: #000;
  height: 80px;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 1rem;
  z-index: 10;
  padding: 0.5rem calc((100vw - 1000px) / 2);
  width: 100%;
  @media screen and (max-width: ${props => `${props.maxWidthBeforeCollapse}px`}) {
    transition: 0.8s all ease;
  }
`;

export const AdminNav = styled.nav`
  background: #000;
  height: 80px;
  display: flex;
  bottom: 0;
  left: 0;
  position: fixed;
  width: 100%;
  justify-content: flex-start;
  z-index: 10;
  padding: 0.5rem calc((100vw - 1000px) / 2);

  @media screen and (max-width: ${props => `${props.maxWidthBeforeCollapse}px`}) {
    transition: 0.8s all ease;
  }
`;

export const NavbarContainer = styled.div`
  display: flex;
  justify-content: space-between;
  height: 80px;
  z-index: 1;
  width: 100%;
  padding: 0 24px;
  max-width: ${props => `${props.maxWidthBeforeCollapse}px`};
`;

export const NavLogo = styled(NavLink)`
  color: #fff;
  justify-self: flex-start;
  cursor: pointer;
  font-size: 1.5rem;
  display: flex;
  align-items: center;
  margin-left: -15px;
  margin-right: 15px;
  font-weight: bold;
  text-decoration: none;

  &.active {
    color: #15cdfc;
  }
`

export const MobileIcon = styled.div`
  display: none;

  @media screen and (max-width: ${props => `${props.maxWidthBeforeCollapse}px`}) {
    display: block;
    position: absolute;
    top: 0;
    right: 0;
    transform: translate(-100%, 60%);
    font-size: 1.8rem;
    cursor: pointer;
    color: #fff;
  }
`

export const NavMenu = styled.ul`
  display: flex;
  align-items: center;
  list-style: none;
  text-align: center;
  margin-right: 22px;

  @media screen and (max-width: ${props => `${props.maxWidthBeforeCollapse}px`}) {
    display: none;
  }
`

export const NavItem  = styled.li`
    height: 80px;
`

export const NavLinks = styled(NavLink)`
  color: #fff;
  display: flex;
  margin-right: 15px;
  align-items: center;
  text-decoration: none;
  padding: 0 1rem;
  height: 100%;
  cursor: pointer;
  
  &.active {
    color: #15cdfc;
  }
`

export const NavBtn = styled.nav`
  display: flex;
  align-items: center;
  @media screen and (max-width: ${props => `${props.maxWidthBeforeCollapse}px`}){
    display: none;
  }
`
export const NavBtnLink = styled(Link)`
  border-radius: 50px;
  background: #01bf71;
  white-space: nowrap;
  padding: 10px 22px;
  color: #010606;
  font-size: 16px;
  outline: none;
  border: none;
  cursor: pointer;
  transition: all 0.2s ease-in-out;
  text-decoration: none;
  
  &:hover {
    transition: all 0.2s ease-in-out;
    background: #fff;
    color: #010606;
  }
`