import { Column } from 'primereact/column';
import React, { useContext, useEffect, useState } from 'react';
import { ToastContext } from '../../App';
import { DataTable } from '../utils';
import { NumberFormat } from '../utils/NumberFormat';
import CompanyStatService from './CompanyStatService';

export const CompanyStat =() => {
    const toast = useContext(ToastContext);
    const [listStatitics, setListStatitics] = useState();
    const [month, setMonth] = useState(7);
    const [year, setYear] = useState(2022);
   
    const getMonth = (event) => {
        const value = event.target.value;
        setMonth(value)
    } 

    const getYear = (event) => {
        const value = event.target.value;
        setYear(value)
    } 

    useEffect(() => {
        init();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    },[])

    const linkTemplate = (data, props) => {
        return (
          <>
            <a
                href={`#/company-detail/${data?.companyResponse?.id}`}
                style={{ color: "#2196f3" }}
            >
                {data.companyResponse.name}
            </a>
          </>
        );
    };
    const init =() => {
        CompanyStatService.getAll(month, year).then((response) => {
            if(response.data.data[0] === null ) {
                toast.error('No statitic in this time !!');
                console.log(response);
            }else{
                setListStatitics(response?.data?.data);
            }
        });

    }

    const numberBody = (data, props) => {
        return(
            <NumberFormat value={Number(data[props.field])} />
        )
    }

    const areaBodyTemplate = (data) => (
        <NumberFormat value={data?.companyResponse?.area} />
    )

    const employeeTemplate = (data) => (
        <NumberFormat value={data?.companyResponse?.numberOfEmployee} />
    )
    
    return (
        <div>
            <div class="col-lg-12 ml-auto" id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-1">Bảng thống kê công ty</h1>
                        <br />
                        <div class="card mb-4">
                            <div class="card-body">
                            <div className="row">
                                    <div class="col-lg-2">
                                        <select className="form-select" aria-label="Default select example"
                                            onChange={(event) => getMonth(event)}>
                                            <option value="01">1</option>
                                            <option value="02">2</option>
                                            <option value="03">3</option>
                                            <option value="04">4</option>
                                            <option value="05">5</option>
                                            <option value="06">6</option>
                                            <option value="07"selected>7</option>
                                            <option value="08">8</option>
                                            <option value="09">9</option>
                                            <option value="10">10</option>
                                            <option value="11">11</option>
                                            <option value="12">12</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-2">
                                        <select className="form-select" aria-label="Default select example"
                                            onChange={(event) => getYear(event)}>
                                            <option value="2017">2017</option>
                                            <option value="2018">2018</option>
                                            <option value="2019">2019</option>
                                            <option value="2020">2020</option>
                                            <option value="2021" >2021</option>
                                            <option value="2022" selected>2022</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-2">
                                        <button type="button" class="btn btn-success" onClick={()=> init()}>Tìm</button>
                                    </div>
                                </div>

                            </div>
                        </div>
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-table me-1"></i>
                                Bảng thống kê công ty
                            </div>
                            <div class="card-body">
                               
                                <DataTable value={listStatitics} rows={10} scrollable >
                                    <Column field="name" header="Tên công ty" filter filterMatchMode="contains" style={{width: '160px'}} body={linkTemplate}></Column>
                                    <Column field="area" header="Diện tích" sortable style={{width: '130px'}} body={areaBodyTemplate} ></Column>
                                    <Column field="numberOfEmployee" header="SL nhân viên" sortable style={{width: '140px'}} body={employeeTemplate} ></Column>
                                    <Column field="rentalPrice" header="Tiền thuê phòng" sortable style={{width: '160px'}} body={numberBody}></Column>
                                    <Column field="servicePrice" header="Tiền dịch vụ" sortable body={numberBody} style={{width: '130px'}} ></Column>
                                    <Column field="waterPrice" header="Tiền nước" sortable  style={{width: '130px'}} body={numberBody}></Column>
                                    <Column field="electricPrice" header="Tiền điện" style={{width: '130px'}} sortable body={numberBody}></Column>
                                    <Column field="totalPrice" header="Tổng" body={numberBody}  sortable style={{width: '130px'}} ></Column>
                                    
                                </DataTable>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
        </div>
    );
}
