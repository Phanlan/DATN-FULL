import React from 'react';
import '../../style.css'
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';
import 'primeflex/primeflex.css';
import { HomeItem } from './HomeItem';
import '../../App.css'

export const Home =() => {
    const companies = [
        { name: "Chi tiết", code: 'companies' },
    ];
    const buildingEmployee = [
        { name: "Chi tiết", code: 'building-employee' },
    ];
    const salary = [
        { name: "Chi tiết", code: 'salary' },
    ];
    const buildingService = [
        { name: "Chi tiết", code: 'building-service' },
    ];
    const infrastructure = [
        { name: "Chi tiết", code: 'building-infrastructure' },
    ];
    const vehicle = [
        { name: "Chi tiết", code: 'building-vehicle' },
    ];

    const invoice = [
        { name: "Chi tiết", code: 'invoice' },
    ];

    const electricWater = [
        { name: "Chi tiết", code: 'electric-water' },
    ];
        return (
            <div>
                <div id="layoutSidenav_content">
                    <main>
                        <div className="container-fluid px-4">
                            <h1 className="mt-4">Quản lí tòa nhà</h1>
                            <br/>
                            <br/>
                            <div className="row">
                                <div className="col-xl-3 col-md-6">
                                    <div className="text-white mb-4" style={{backgroundColor: '#6D92D0'}}>
                                    <HomeItem options = {buildingEmployee} header ="Quản lí nhân viên tòa nhà" />
                                    </div>
                                </div>
                                <div className="col-xl-3 col-md-6">
                                    <div className="text-white mb-4" style={{backgroundColor: '#6D92D0'}}>
                                        <HomeItem options = {companies} header ="Quản lí công ty thuê" />
                                    </div>
                                </div>
                                <div className="col-xl-3 col-md-6">
                                    <div className="text-white mb-4" style={{backgroundColor: '#6D92D0'}}>
                                        <HomeItem options = {salary} header ="Quản lí lương nhân viên" />
                                    </div>
                                </div>
                                <div className="col-xl-3 col-md-6">
                                    <div className="text-white mb-4" style={{backgroundColor: '#6D92D0'}}>
                                        <HomeItem options = {buildingService} header ="Quản lí dịch vụ tòa nhà" />
                                    </div>
                                </div>
                                <div className="col-xl-3 col-md-6">
                                    <div className="text-white mb-4" style={{backgroundColor: '#6D92D0'}}>
                                        <HomeItem options = {infrastructure} header ="Quản lí trang thiết bị" />
                                    </div>
                                </div>
                                <div className="col-xl-3 col-md-6">
                                    <div className="text-white mb-4" style={{backgroundColor: '#6D92D0'}}>
                                        <HomeItem options = {vehicle} header ="Quản lí xe" />
                                    </div>
                                </div>
                                <div className="col-xl-3 col-md-6">
                                    <div className="text-white mb-4" style={{backgroundColor: '#6D92D0'}}>
                                        <HomeItem options = {invoice} header ="Hóa đơn" />
                                    </div>
                                </div>
                                <div className="col-xl-3 col-md-6">
                                    <div className="text-white mb-4" style={{backgroundColor: '#6D92D0'}}>
                                        <HomeItem options = {electricWater} header ="Điện nước" />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </main>

                </div>
            </div>
        );
    }
