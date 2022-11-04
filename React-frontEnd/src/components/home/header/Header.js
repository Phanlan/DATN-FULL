/* eslint-disable jsx-a11y/anchor-is-valid */
/* eslint-disable jsx-a11y/alt-text */
import React from 'react';

export const  Header = () => {
        return (
            <div>
                <nav className="sb-topnav navbar navbar-expand navbar-dark bg-dark" style={{justifyContent: 'space-between' }}>
                    <div>
                        <img className="App-logo" src="/assets/img/office-building.png" width="40px" height="40px"/>
                        <a className="navbar-brand ps-3" href="/">Building Manage</a>
                    </div>
                    {/* <button className= "btn btn-link btn-sm order-1 order-lg-0 me-3 me-lg-4 ms-auto" >
                        <a href="#/login" ><i className="fas fa-user fa-fw" id="sidebarToggle" style={{fontSize: '1.4rem'}} /> </a>
                    </button> */}
                    <ul className="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
                        <li className="nav-item dropdown">
                            <a className="nav-link dropdown-toggle" id="navbarDropdown" href="" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i className="fas fa-user fa-fw"/></a>
                            <ul className="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                                <li><a className="dropdown-item" href="#/signup">Thêm tài khoản</a></li>
                                <li><a className="dropdown-item" href="#/login">Đăng xuất</a></li>
                            </ul>
                        </li>
                    </ul>
                </nav>
            </div>
        );
    
}

