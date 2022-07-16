import axios from 'axios';
import React, { useRef, useState } from 'react'
import { Modal } from 'react-bootstrap';
import { alertError, alertSuccess } from '../../../../shared/toastify';

const AddCategory = (props: any) => {
  const [cate, setCate] = useState<any>({});
  const [isShow, setIsShow] = useState(false);
  const resetForm = useRef<any>(null);
  const show = () => setIsShow(!isShow);
  const close = () => {
    setIsShow(false);
  }
  

  const callApiPostCate =async () => {
    try {
      const data = await axios.post('http://localhost:8080/api/v1/category', cate,  {headers: {
       "Content-Type": "application/json"
      }});
      if(data.status === 200) {
        setIsShow(!isShow);
        alertSuccess("Add Category Successfully!");
        props.changeStatus(!props.statusPage);
      }
      
    } catch (error) {
      console.log(error);
      alertError('Add Category Failed!')
      
    }
  }

  const changeTitle = (e: any) => {
    setCate({...cate, title: e.target.value})
    console.log(cate);
    
  }
  const changeContent = (e: any) => {
    setCate({...cate, content: e.target.value})
  }
  const changeCode = (e: any) => {
    setCate({...cate, code: e.target.value})
  }

  const submit = (e: any) => {
    e.preventDefault();
    resetForm.current.reset();
  }
  return (
    <div className=''>
      <button className="btn btn-danger px-4 py-2" onClick={show}>
          Create Category
      </button>
      <Modal show={isShow}>
        <form ref={resetForm} onSubmit={submit} className="formAddCate p-5">
          <div className='row mt-3'>
            <label htmlFor="" className='col-2'>Title</label>
            <input className='title btn border-1 border-primary col-5 ' type="text" onChange={changeTitle}/>
          </div>
          <div className='row mt-3'>
            <label htmlFor="" className='col-2'>Content</label>
            <input className='content btn border-1 border-primary col-5' type="text" onChange={changeContent}/>
          </div>
          <div className='row mt-3'>
            <label htmlFor="" className='col-2'>Code</label>
            <input className='code btn border-1 border-primary col-5' type="text" onChange={changeCode}/>
          </div>
          <div className='d-flex me-5 justify-content-center mt-5'>
            <button className='btn btn-primary me-3' type='submit' onClick={callApiPostCate}>Submit</button>
            <button className='btn btn-warning me-3' type='reset'>Reset</button>
            <button className="px-3 py-2 fw-bold btn btn-danger me-3" onClick={close}>Close</button>
          </div>
          
        </form>
      </Modal>
     
    </div>
  )
}

export default AddCategory