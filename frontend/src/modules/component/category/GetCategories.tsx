import axios from 'axios';
import React, { useEffect, useState } from 'react'

const GetCategories = () => {
    const [categories, setCategories] = useState<any>([]);
    useEffect(() => {
        getListCate();
    },[]);

    const getListCate = async () => {
       try {
        const data = await axios.get('http://localhost:8080/api/v1/category?page=0&page_size=10');
        setCategories([...data.data.data])
       } catch (error) {
        console.log(error);
        
       }
    }

    const updateCate = async(e: any) => {
        try {
            const data = await axios.put('http://localhost:8080/api/v1/category')
        } catch (error) {
            console.log(error);
            
        }
    }

    const delCate = async(id: any) => {
        try {
            const res = await axios.delete(`http://localhost:8080/api/v1/category/${id}`);
            console.log(res.status);
            
        } catch (error) {
            console.log(error);
            
        }
    }

    const preUpdate = (e: any) => {

    }



    const displayCate = (data: any) => {
        const listCate = data.map((item: any, index: any) => (
            <tr key={index}>
                <td>{index+1}</td>
                <td>{item.title}</td>
                <td>{item.content}</td>
                <td>{item.code}</td>
                <td>
                    <button className='updateCate' onClick={() => preUpdate({item})}>Update</button>
                    <button className='deleteCate' onClick={() => delCate(item.id)}>Delete</button>
                </td>
            </tr>
        ));
        return listCate;
    }
  return (
    <div>
        <h1>List Categories</h1>
        <table>
            <thead>
                <tr>
                <th>STT</th>
                <th>Title</th>
                <th>Content</th>
                <th>Code</th>
                <td>Action</td>
                </tr>
            </thead>
            <tbody>{displayCate(categories)}</tbody>
        </table>
        <form></form>
        
    </div>
  )
}

export default GetCategories