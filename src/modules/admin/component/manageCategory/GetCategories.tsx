import axios from 'axios';
import React, { useEffect, useState } from 'react'
import '../../../../assets/css/style.css'
import AddCategory from './AddCategory';
import DeleteCategory from './DeleteCategory';
import UpdateCategory from './UpdateCategory';
const GetCategories = () => {
    const [categories, setCategories] = useState<any>([]);
    const [statusPage, setStatusPage] = useState(false);
    useEffect(() => {
        getListCate();
    },[statusPage]);

    const getListCate = async () => {
       try {
        const data = await axios.get('http://localhost:8080/api/v1/category?page=0&page_size=10');
        setCategories([...data.data.data])
       } catch (error) {
        console.log(error);
        
       }
    }
    const changeStatusPage = (changeStatus: boolean) => {
        setStatusPage(changeStatus);
    }



    const displayCate = (data: any) => {
        const listCate = data.map((item: any, index: any) => (
            <tr key={index}>
                <td>{index+1}</td>
                <td>{item.title}</td>
                <td>{item.content}</td>
                <td>{item.code}</td>
                <td className='d-flex justify-content-center'>
                    <UpdateCategory data={item} statusPage={statusPage} changeStatus={changeStatusPage}/>
                    <DeleteCategory name={item.title} id={item.id} statusPage={statusPage} changeStatus={changeStatusPage}/>
                </td>
            </tr>
        ));
        return listCate;
    }
  return (
    <div>
        <div className='container'>
            <div className='d-flex justify-content-between mb-5'>
                <h1 className='text-center fw-bold mb-5 fs-1'>LIST CATEGORIES</h1>
                <AddCategory name="create" statusPage={statusPage} changeStatus={changeStatusPage}/>
            </div>
            <table className='table table-dark .table-striped text-center'>
                <thead>
                    <tr>
                        <th>STT</th>
                        <th>Title</th>
                        <th>Content</th>
                        <th>Code</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>{displayCate(categories)}</tbody>
            </table>
        </div>
    </div>
  )
}

export default GetCategories