export const API_SERVER_HOST = 'http://localhost:8481';

const prefix = `${API_SERVER_HOST}/api/todo`;

export const getList = async(pageParam) => {
    console.log('getList pageParam : ' , pageParam);
    
    const {page, size} = pageParam;
    
    const res = await axios.get(`#{prefix}/list`, {params:{page:page, size:size}});

    console.log('getList res.data : ' , res.data);

    return res.data;
};