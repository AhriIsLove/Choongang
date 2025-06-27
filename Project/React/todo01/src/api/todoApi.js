import axios from "axios";

export const API_SERVER_HOST = 'http://localhost:8481';

const prefix = `${API_SERVER_HOST}/api/todo`;

//서버에게 요청
export const getList = async(pageParam) => {
    // console.log('getList pageParam : ' , pageParam);
    
    //?page=1&size=10
    const {page, size} = pageParam;
    
    // axios : ajax와 비슷한 역할
    const res = await axios.get(`${prefix}/list`, {params:{page:page, size:size}});

    console.log('getList res.data : ' , res.data);

    return res.data;
};