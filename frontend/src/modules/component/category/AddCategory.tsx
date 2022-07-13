import axios from 'axios';
import React, { useRef, useState } from 'react'

const AddCategory = () => {
  const [cate, setCate] = useState<any>({});
  const resetForm = useRef<any>(null);
  const callApiPostCate =async () => {
    try {
      const data = await axios.post('http://localhost:8080/api/v1/category', cate,  {headers: {
       "Content-Type": "application/json"
      }});
      if(data.status === 200) {
        
      }
      
    } catch (error) {
      console.log(error);
      
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
    <div>
      <form ref={resetForm} onSubmit={submit}>
        <div>
          <label htmlFor="">Title</label>
          <input className='title' type="text" onChange={changeTitle}/>
        </div>
        <div>
          <label htmlFor="">Content</label>
          <input className='content' type="text" onChange={changeContent}/>
        </div>
        <div>
          <label htmlFor="">Code</label>
          <input className='code' type="text" onChange={changeCode}/>
        </div>
        <button type='submit' onClick={callApiPostCate}>Submit</button>
        <button type='reset'>Reset</button>
      </form>
    </div>
  )
}

export default AddCategory