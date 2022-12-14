import { Column } from 'primereact/column';
import { DataTable } from '../utils'
import { Toolbar } from 'primereact/toolbar';
import React, { useContext, useEffect, useState } from 'react';
import { NumberFormat } from '../../components/utils/NumberFormat';
import { Button } from 'primereact/button';
import { ToastContext } from '../../App';
import { Loading } from '../../components/common/Loading';
import { confirmDialog } from 'primereact/confirmdialog';
import BuildingEmployeeService from './BuildingEmployeeService'
import { BuildingEmployeeForm } from './BuildingEmployeeForm';
import { useRef } from 'react';

export const  BuildingEmployee=() =>  {
    const ref=useRef();
    const [buildingEmployees, setBuildingEmployees] = useState([]);
    const [displayBuildingEmployeeNew, setDisplayBuildingEmployeeNew] = useState(false);
    const toast = useContext(ToastContext);
    const [flagChange, setFlagChange] = useState(false);
    const [loading, setLoading] = useState(false);
    const [loadingPage, setLoadingPage] = useState(false);
    const [isUpdate, setIsUpdate] = useState(false);
    const [buildingEmployeeToUpdate, setBuildingEmployeeToUpdate] = useState(null);
    
    useEffect(() => {
        try{
            setLoading(true);
            BuildingEmployeeService.getAllBuildingEmployee().then((response) => {
                setBuildingEmployees(response.data.data)
            })
        }catch(error) {
            toast.current.show({
                severity: "error",
                summary: 'error',
                detail: error?.response?.data?.errors,
                life: 5000,
            });
        }finally{
            setLoading(false)
        }
        
    }, [flagChange]) // eslint-disable-line react-hooks/exhaustive-deps

    const numberBody = (data, props) => {
        const value = data?.salaryResponse.salary
        return(
            <NumberFormat value={Number(value)} />
        )
    }

    const levelBody = (data, props) => {
        return(
            <>
                <span>{data?.salaryResponse.level}</span>
            </>
        )
    }

    const positionBody = (data, props) => {
        return(
            <>
                <span>{data?.salaryResponse.position}</span>
            </>
        )
    }

    const leftContents = (
        <>
            <h5 className='p-m-0'>Danh s??ch nh??n vi??n t??a nh??</h5>
        </>
    );

    const rightContents = (
        <>
            <Button
            label='Th??m m???i'
            icon="pi pi-plus"
            className="p-button-outlined p-mr-2"
            onClick={() => {setDisplayBuildingEmployeeNew(true);
                setIsUpdate(false);
                setBuildingEmployeeToUpdate(null)}}
            />
        </>
    );

    const handleHideBuildingEmployeeNewDialog = () => {
        setDisplayBuildingEmployeeNew(false);
    }
    const editBuildingEmployee = (buildingEmployee) => {
        setBuildingEmployeeToUpdate({ ...buildingEmployee });
        setDisplayBuildingEmployeeNew(true);
        setIsUpdate(true)
    }

    const confirmDeleteBuildingEmployee = (rowData) => {
        confirmDialog({
          message: <p>B???n c?? mu???n x??a b???n ghi n??y kh??ng?</p>,
          header: <h6>X??c nh???n x??a b???n ghi</h6>,
          icon: 'pi pi-info-circle',
          acceptClassName: 'p-button-danger',
          accept: () => {deleteBuildingEmployee(rowData)},
        });
    };

    const deleteBuildingEmployee = async(params) => {
        try {
            setLoading(true);
                await BuildingEmployeeService.deleteBuildingEmployee(parseInt(params?.id)).then(() => {
                    BuildingEmployeeService.getAllBuildingEmployee().then((response) => {
                        setBuildingEmployees(response.data.data)
                    })
                });
                setFlagChange(!flagChange);
                toast.current.show({ severity: 'success', summary: 'Th??ng b??o th??nh c??ng', detail: 'Delete Success', life: 5000 });
        } catch(error) {
            toast.current.show({
                severity: "error",
                summary: 'error',
                detail: error?.response?.data?.errors,
                life: 5000,
            });
        } finally {
            setLoading(false);
        } 
    }   

    const actionBodyTemplate = (rowData) => {
        return (
            <div className="actions" >
                <Button icon="pi pi-pencil" 
                    className="p-button-rounded p-button-success p-mr-2" 
                    onClick={() => editBuildingEmployee(rowData)} 
                />
                <Button icon="pi pi-trash" 
                    className="p-button-rounded p-button-warning" 
                    onClick={() => confirmDeleteBuildingEmployee(rowData)} 
                />
            </div>
          
      );
    }
    
        return (
            <div>
                {displayBuildingEmployeeNew && 
                    <BuildingEmployeeForm 
                        display={displayBuildingEmployeeNew} 
                        setDisplay={setDisplayBuildingEmployeeNew} 
                        handleHide={handleHideBuildingEmployeeNewDialog}
                        toast={toast}
                        flagChange={flagChange}
                        setFlagChange={setFlagChange}
                        setLoading={setLoadingPage}
                        loading={loadingPage}
                        setBuildingEmployees={setBuildingEmployees}
                        isUpdate={isUpdate}
                        buildingEmployeeToUpdate={buildingEmployeeToUpdate}
                    />
                }
                <div className="card">
                    <Toolbar left={leftContents} right={rightContents} />
                    <DataTable value={buildingEmployees} loading={loading} rows={10} ref={ref} scrollable >
                        <Column field="code" header="M?? nh??n vi??n" filter filterMatchMode="contains" style={{width: '160px'}}></Column>
                        <Column field="name" header="T??n" filter filterMatchMode="contains" style={{width: '160px'}}></Column>
                        <Column field="dateOfBirth" header="Ng??y sinh" filter filterMatchMode="contains" style={{width: '170px'}}></Column>
                        <Column field="address" header="?????a ch???" filter filterMatchMode="contains"  style={{width: '160px'}}></Column>
                        <Column field="phone" header="S??? ??i???n tho???i" style={{width: '160px'}} filter filterMatchMode="contains"></Column>
                        <Column field="" header="Th??? b???c" filter filterMatchMode="contains" body={levelBody} style={{width: '160px'}}></Column>
                        <Column field="" header="V??? tr??" filter filterMatchMode="contains" body={positionBody} style={{width: '160px'}}></Column>
                        <Column field="" header="L????ng" body={numberBody}  filter filterMatchMode="contains" style={{width: '160px'}}></Column>
                        <Column  body={actionBodyTemplate} style={{ width: '120px' }} />
                    </DataTable>
                </div>
                <Loading visible={loadingPage} onHide={() => setLoadingPage(false)} />
            </div>
        );
    }

