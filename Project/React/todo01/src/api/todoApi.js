import axios from "axios";

export const API_SERVER_HOST = 'http://localhost:8481';

const prefix = `${API_SERVER_HOST}/api/todo`;

//서버에게 조회 요청
export const getOne = async(tno) => {
    const res = await axios.get(`${prefix}/${tno}`);
    // TodoDTO
    // - tno : 1
    // - title : 'title'
    // - writer : 'writer'
    // - complete : false
    // - due_date : null

    return res.data;
}

//서버에게 목록 요청
export const getList = async(pageParam) => {
    // console.log('getList pageParam : ' , pageParam);
    
    //?page=1&size=10
    const {page, size} = pageParam;
    
    // axios : ajax와 비슷한 역할
    // get방식 API 요청
    // get방식은 URL로 요청됨
    const res = await axios.get(`${prefix}/list`, {params:{page:page, size:size}});
    // PageResponseDTO<TodoDTO>
    // - dtoList: [TodoDTO, TodoDTO, TodoDTO, ],
    // - pageNumList: [1, 2],
    // - pageRequestDTO: page=1&size=10,
    // - prev:false,
    // - next:false,
    // - totalCount: 11,
    // - prevPage: 0,
    // - nextPage: 0,
    // - totalPage: 2,
    // - current: 1

    console.log('getList res.data : ' , res.data);

    return res.data;
};

//서버에게 추가 요청
export const postAdd = async(todoObj) => {

    console.log('postAdd todoObj : ' , todoObj);

    // post방식 API 요청
    // post방식은 responseBody로 요청됨
    const res = await axios.post(`${prefix}/register`, todoObj);
    // Map<String, Long>
    // - TNO : 1

    return res.data;
};

export const putOne = async(todoObj) => {
    const res = await axios.post(`${prefix}/modify`, todoObj);

    return res.data;
};
export const deleteOne = async(todoObj) => {
    const res = await axios.post(`${prefix}/delete`, todoObj);

    return res.data;
};