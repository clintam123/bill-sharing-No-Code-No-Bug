import axios from 'axios'
import React, { useRef, useState } from 'react'

const RegisterVendor = () => {

    const [vendor, setVendor] = useState<any>({});
    const resetForm = useRef<any>(null);
    const registerVendor = async () => {
        const res = axios.post('http://localhost:8080/api/v1/vendor/add-vendor', vendor, {
            headers: {
                "Content-Type": "application/json"
            }
        })
    }

    const changeTitle = (e: any) => {
        setVendor({...vendor, title: e.target.value})
        console.log(vendor);
        
    }

    const submit = (e: any) => {
        e.preventDefault();
        resetForm.current.reset();
    }
  return (
    <div>
        <form ref={resetForm} onSubmit={submit} className="formAddCate p-5">
          <div className='row mt-3'>
            <label htmlFor="" className='col-2'>Title</label>
            <input className='title btn border-1 border-primary col-5 ' type="text" onChange={changeTitle}/>
          </div>
          
          <div className='d-flex me-5 justify-content-center mt-5'>
            <button className='btn btn-primary me-3' type='submit' onClick={registerVendor}>Submit</button>
            <button className='btn btn-warning me-3' type='reset'>Reset</button>
          </div>
          
        </form>
    </div>
  )
}

export default RegisterVendor