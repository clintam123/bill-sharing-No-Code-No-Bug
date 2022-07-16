import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { Link, Outlet } from 'react-router-dom'

const ManageVendor = () => {
  const [listVendor, setListVendor] = useState([]);
  useEffect(() => {
    getListVendor();
  })
  const getListVendor = async() => {
    try {
      const res = await axios.get('http://localhost:8080/api/v1.0/admin/vendor/get-all?page=0&page_size=2');
      console.log(res);
      
    } catch (error) {
      console.log(error);
      
    }
  }
  return (
    <div>
      
    </div>
  )
}

export default ManageVendor