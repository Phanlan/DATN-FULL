
import React, { useCallback} from 'react';
import { useForm } from 'react-hook-form';
import { yupResolver } from "@hookform/resolvers/yup";
import * as yup from "yup";
import { Dialog } from 'primereact/dialog';
import { Button } from 'primereact/button';
import { InputNumberController } from '../common/InputNumberController';
import { CalendarController } from '../common/CalendarController';
import moment from 'moment/moment';
import { DropdownController } from '../common/DropdownController';
import { useEffect } from 'react';
import CompanyService from '../company-table/CompanyService';
import { useState } from 'react';
import ElectricWaterService from './ElectricWaterService';

export const UsedElectricWaterForm = (props) => {
    const { display, toast, flagChange, setLoading, handleHide, setFlagChange, data } = props;
    const msgError = 'Trường bắt buộc';
    const [company, setCompany] = useState();
    const [companyIdList, setCompanyIdList] = useState([]);
    const [dateList, setDateList] = useState([]);

    const getFormErrorMessage = (name) => {
        return errors[name] && <small className="p-error">{errors[name]?.message}</small>
    };

    const defaultValues = {
        electricNumber: null,
        waterNumber: null,
        month: null,
        companyId: ''
    };

    const schema = yup.object().shape({
        companyId: yup.string().nullable().required(msgError),
        electricNumber: yup.number().nullable().required(msgError),
        waterNumber: yup.number().nullable().required(msgError),
        month: yup.date().required(msgError),
    });
    const { control, formState: { errors, isSubmitting }, handleSubmit, reset, setValue, getValues} = useForm({ defaultValues, resolver: yupResolver(schema) });

    useEffect(() => {
       
        const getData = async() => {
            await CompanyService.getAllCompany().then((response) => {
                setCompany(response.data.data.map((item) => ({
                    value: item.id,
                    name: item.name
                })))
            });
            console.log(data)
            setCompanyIdList(data?.map(item => item.company_id));
            setDateList(data?.map(item => new Date(item?.month).getMonth() ))
        }
        getData();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);
    const renderFooter = () => {
        return (
          <>
            <Button label="Lưu" icon="pi pi-check" disabled={isSubmitting}
              onClick={handleSubmit(onSubmit)}/>
          </>
        );
    }
    const onSubmit = useCallback(async (data) => {
        try {
            setLoading(true);
            const params = {
                ...data,
                company_id: data.companyId,
                month:  moment(data?.month).format('YYYY-MM-DD')
            };
                await ElectricWaterService.createElectricWater(params)
                toast.current.show({ severity: 'success', summary: 'Thông báo thành công', detail: 'Thêm mới thành công!!!', life: 5000 });
                handleHideDialog();
          
        } catch (error) {
          toast.current.show({ severity: 'error', summary: 'Thông báo lỗi', detail: error?.response?.data?.errors, life: 5000 });
        console.log(error)
        }
        finally {
            setLoading(false);
        }
    }, []);// eslint-disable-line react-hooks/exhaustive-deps

    const handleHideDialog = () => {
        setFlagChange(!flagChange);
        handleHide();
        reset()
    }

    const validateCompany = (e) => {
        setValue('companyId', e.target.value)
        const _date = getValues('invoiceDate')
        const index = companyIdList?.findIndex(item => item === parseInt(e.target.value));
        const _index = dateList?.findIndex(item => item === new Date(_date).getMonth())
        if(index>-1 && _index>-1) {
            toast.current.show({ severity: 'error', summary: 'Thông báo lỗi', detail: 'Không được chốt điện nước 2 lần cùng 1 tháng cho 1 công ty', life: 5000 });
            handleHideDialog()
        }
    }

    const validateDate = (e) => {
        setValue('invoiceDate', e.target.value)
        const _companyId = getValues('companyId')
        const index = companyIdList?.findIndex(item => item === parseInt(_companyId));
        const _index = dateList?.findIndex(item => item === new Date(e.target.value).getMonth())
        if(index>-1 && _index>-1) {
            toast.current.show({ severity: 'error', summary: 'Thông báo lỗi', detail: 'Không được chốt điện nước 2 lần cùng 1 tháng cho 1 công ty', life: 5000 });
            handleHideDialog()
        }
    }
    return (
        <>
            <Dialog header='Thêm mới' visible={display} onHide={handleHideDialog}  
                style={{width: '35vw'}} position={'top'} modal
                footer={renderFooter}
            >
                <form>
                    <div className="p-fluid">
                        <div className="p-grid">
                            <div className="p-col-12">
                                <DropdownController id='companyId' label='Công ty' 
                                    checkErr getFormErrorMessage={getFormErrorMessage} 
                                    control={control} 
                                    options={company}
                                    optionLabel = 'name'
                                    optionValue = 'value' 
                                    onChange={(e) => validateCompany(e)}
                                />
                                <InputNumberController id='electricNumber'
                                    label='Số điện' checkErr getFormErrorMessage={getFormErrorMessage}
                                    control={control}  min={0}
                                    showButtons
                                />
                                <InputNumberController id='waterNumber'
                                    label='Số nước' checkErr getFormErrorMessage={getFormErrorMessage}
                                    control={control} 
                                    showButtons
                                />
                                <CalendarController 
                                    id='month' label='Ngày chốt số'
                                    checkErr getFormErrorMessage={getFormErrorMessage}
                                    showIcon control={control} monthNavigator 
                                    yearNavigator yearRange="1940:2100"
                                    onChange={(e) => validateDate(e)}
                                />
                            </div>
                        </div>
                    </div>
                </form>
            </Dialog>
        </>
    )
}