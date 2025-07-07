import axios from "axios";

export const API_SERVER_HOST = 'http://localhost:8481';

const prefix = `${API_SERVER_HOST}/api/products`;

export const getList = async (pageParam) => {
    const { page, size } = pageParam;

    const res = await axios.get(`${prefix}/list`, { params: { page: page, size: size } });

    return res.data;
};

export const productAdd = async (product) => {
    //파일 전송할 경우 Header에 Content-Type을 multipart/form-data로 설정 필요
    const header = { headers: { "Content-Type": "multipart/form-data" } };

    const res = await axios.post(`${prefix}/register`, product, header);

    return res.data;
};

export const getOne = async (pno) => {
    console.log("getone");

    const res = await axios.get(`${prefix}/${pno}`);

    console.log("getone", res);

    return res.data;
};

export const putOne = async (pno, product) => {
    const header = { headers: { "Content-Type": "multipart/form-data" } };

    const res = await axios.put(`${prefix}/modify/${pno}`, product, header);

    return res.data;
};

export const deleteOne = async (pno) => {
    const res = await axios.delete(`${prefix}/remove/${pno}`);

    return res.data;
};