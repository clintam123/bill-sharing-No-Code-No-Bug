import axios from "axios";
import React, { useState } from "react";
import { Modal } from "react-bootstrap";
import '../../../../assets/css/style.css';
import { alertError, alertSuccess } from "../../../../shared/toastify";

const DeleteCategory = (props: any) => {
  const [isShow, setIsShow] = useState(false);

  const show = () => setIsShow(!isShow);
  const close = () => setIsShow(false);
  const deleteCategory = async () => {
    try {
        const res = await axios.delete(`http://localhost:8080/api/v1/category/${props.id}`);
        if(res.status === 200) {
            alertSuccess("Delete Successfully!")
           props.changeStatus(!props.statusPage)
        }
    } catch (error) {
        alertError('Delete Failed!')
    }
    setIsShow(!isShow);
  };
  return (
    <div>
      <div>
        <button className="btn btn-danger px-4 py-2" onClick={show}>
          Delete
        </button>
        <Modal show={isShow}>
          <div className="dialogDelCate p-5">
            <h5>Bạn có chắc muốn xóa: {props.name}</h5>
            <div className="d-flex justify-content-end pt-2">
              <button className="px-3 py-2 fw-bold btn btn-warning me-4" onClick={deleteCategory}>
                Xóa
              </button>
              <button className="px-3 py-2 fw-bold btn btn-danger" onClick={close}>
                Hủy
              </button>
            </div>
          </div>
        </Modal>
      </div>
    </div>
  );
};

export default DeleteCategory;
