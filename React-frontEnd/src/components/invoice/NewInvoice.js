
import React, { useEffect, useCallback, useState} from 'react';
import { useForm } from 'react-hook-form';
import { yupResolver } from "@hookform/resolvers/yup";
import * as yup from "yup";
import { Dialog } from 'primereact/dialog';
import { Button } from 'primereact/button';
import { DropdownController } from '../common/DropdownController';
import { CalendarController } from '../common/CalendarController';
import CompanyService from '../company-table/CompanyService';
import InvoiceService from './InvoiceService';
import moment from 'moment';

export const NewInvoice = (props) => {
    const { display, toast, flagChange, setLoading, handleHide, setFlagChange, listInvoice } = props;
    const msgError = 'Trường bắt buộc';
    const [company, setCompany] = useState();
    const [companyIdList, setCompanyIdList] = useState([]);
    const [invoiceDateList, setInvoiceDateList] = useState([]);

    const getFormErrorMessage = (name) => {
        return errors[name] && <small className="p-error">{errors[name]?.message}</small>
    };

    const defaultValues = {
        companyId: '',
        // placeOfUse: '',
        invoiceDate: null,
    };

    const schema = yup.object().shape({
        companyId: yup.string().required(msgError),
        invoiceDate: yup.date().required(msgError),

    });
    const { control, formState: { errors, isSubmitting }, handleSubmit, reset, setValue, getValues} = useForm({ defaultValues, resolver: yupResolver(schema) });

    useEffect(() => {
        const getData = async() => {
           
            await CompanyService.getAllCompany().then((response) => {
                console.log(response)
                setCompany(response.data.data.map((item) => ({
                    name: item.name,
                    value: item.id
                })))
            });
            setCompanyIdList(listInvoice?.map(item => item.companyId));
            setInvoiceDateList(listInvoice?.map(item => new Date(item?.invoiceDate).getMonth() ))

        }
        getData();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);
    console.log(invoiceDateList[0])
    const renderFooter = () => {
        return (
          <>
            <Button label="Lưu" icon="pi pi-check" disabled={isSubmitting}
              onClick={handleSubmit(onSubmit)}/>
          </>
        );
    }

    const validateCompany = (e) => {
        setValue('companyId', e.target.value)
        const _date = getValues('invoiceDate')
        const index = companyIdList?.findIndex(item => item === parseInt(e.target.value));
        const _index = invoiceDateList?.findIndex(item => item === new Date(_date).getMonth())
        if(index>-1 && _index>-1) {
            toast.current.show({ severity: 'error', summary: 'Thông báo lỗi', detail: 'Không được lập 2 hóa đơn cùng 1 tháng cho 1 công ty', life: 5000 });
            handleHideDialog()
        }
    }

    const validateDate = (e) => {
        setValue('invoiceDate', e.target.value)
        const _companyId = getValues('companyId')
        const index = companyIdList?.findIndex(item => item === parseInt(_companyId));
        const _index = invoiceDateList?.findIndex(item => item === new Date(e.target.value).getMonth())
        if(index>-1 && _index>-1) {
            toast.current.show({ severity: 'error', summary: 'Thông báo lỗi', detail: 'Không được lập 2 hóa đơn cùng 1 tháng cho 1 công ty', life: 5000 });
            handleHideDialog()
        }
    }
    const onSubmit = useCallback(async (data) => {
        // console.log(parseInt(data.companyId))
        try {
            setLoading(true);
            const params = {
                month: data?.invoiceDate.getMonth(),
                year: data.invoiceDate.getFullYear(),
                companyId: parseInt(data.companyId),
                invoiceDate:  moment(data?.invoiceDate).format('YYYY-MM-DD')
            };
            console.log(params)
            
            await InvoiceService.createInvoice(params)
            toast.current.show({ severity: 'success', summary: 'Thông báo thành công', detail: 'Thêm mới thành công !!!', life: 5000 });
            handleHideDialog();
            setFlagChange(!flagChange);
            
        } catch (error) {
            toast.current.show({ severity: 'error', summary: 'Thông báo lỗi', detail: error?.response?.data?.errors, life: 5000 });
            console.log(error)
        }
        finally {
            setLoading(false);
        }
    }, []);// eslint-disable-line react-hooks/exhaustive-deps

    const handleHideDialog = () => {
        handleHide();
        reset()
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
                                <CalendarController 
                                    id='invoiceDate' label='Ngày xuất hóa đơn'
                                    checkErr getFormErrorMessage={getFormErrorMessage}
                                    showIcon control={control} monthNavigator 
                                    yearNavigator yearRange="1990:2100"
                                    onChange={(e) => validateDate(e)}
                                />

                                {/* <InputNumberController id='unitAmount' label='Đơn giá'
                                    checkErr getFormErrorMessage={getFormErrorMessage} 
                                    control={control} 
                                /> */}
                                
                            </div>
                        </div>
                    </div>
                </form>
            </Dialog>
        </>
    )
}