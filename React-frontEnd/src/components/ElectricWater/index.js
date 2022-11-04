import { Button } from "primereact/button";
import { Column } from "primereact/column"
import { Toolbar } from "primereact/toolbar"
import { useContext } from "react";
import { useEffect, useState } from "react";
import Moment from "react-moment";
import { ToastContext } from "../../App";
import { DataTable, NumberFormat } from "../utils"
import ElectricWaterService from "./ElectricWaterService";
import { UsedElectricWaterForm } from "./UsedElectricWaterForm";

export const ElectricWater = (props) => {
    const [flagChange, setFlagChange] = useState(false);
    const toast = useContext(ToastContext);
    const [loading, setLoading] = useState(false);
    const [displayUsedElectricWaterNew, setDisplayUsedElectricWaterNew] = useState();
    const [data, setData] = useState();

    useEffect(() => {
        init();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    },[flagChange]);

    const init =() => {
        try{
            setLoading(true);
            ElectricWaterService.getAllElectricWater().then((response) => {
                console.log(response.data.data)
                setData(response.data.data)
            })
        }catch(error) {
            console.log(error)
            toast.current.show({
                severity: "error",
                summary: 'error',
                detail: error?.response?.data?.errors,
                life: 5000,
            });
        }finally{
            setLoading(false)
        }

    }
    const leftContent = (
        <>
            <h5 className='p-m-0'>Danh sách dịch vụ điện nước</h5>
        </>
    );

    const rightContent = (
        <>
            <Button
            label='Thêm mới'
            icon="pi pi-plus"
            className="p-button-outlined p-mr-2"
            onClick={() => {setDisplayUsedElectricWaterNew(true);}}
            />
        </>
    );

    const handleHideUsedElectricWaterNewDialog = () => {
        setDisplayUsedElectricWaterNew(false);
    }

    const numberBody = (data, props) => {
        return(
            <NumberFormat value={Number(data[props.field])} />
        )
    }

    const timeTemplate = (data, props) => {
        return (
            <>
                <Moment format="DD/MM/YYYY">{data[props.field] ? data[props.field] : ""}</Moment>
            </>
        );
    };

    const linkTemplate = (data, props) => {
        return (
          <>
            <a
                href={`#/company-detail/${data?.company_id}`}
                style={{ color: "#2196f3" }}
            >
                {data?.companyName}
            </a>
          </>
        );
    };

    return(
        <>
            {displayUsedElectricWaterNew && 
                <UsedElectricWaterForm 
                    display={displayUsedElectricWaterNew} 
                    setDisplay={setDisplayUsedElectricWaterNew} 
                    handleHide={handleHideUsedElectricWaterNewDialog}
                    toast={toast}
                    flagChange={flagChange}
                    setFlagChange={setFlagChange}
                    setLoading={setLoading}
                    loading={loading}
                    data={data}
                />
            }
            <div className="card">
                <Toolbar left={leftContent} right={rightContent} />
                <DataTable value={data} loading={loading} key='id' rows={10} >
                    <Column field="companyName" header="Công ty" body={linkTemplate}  />
                    <Column field="electricNumber" header="Số điện" sortable body={numberBody} />
                    <Column field="waterNumber" header="Số nước" sortable body={numberBody} />
                    <Column field="month" header="Ngày chốt số" sortable body={timeTemplate} />
                </DataTable>
            
            </div>
        </>
    )
}