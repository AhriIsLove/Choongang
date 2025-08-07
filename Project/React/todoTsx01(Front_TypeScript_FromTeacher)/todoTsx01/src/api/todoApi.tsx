import axios from "axios"
export const API_SERVER_HOST = 'http://localhost:8481'
const prefix = `${API_SERVER_HOST}/api/todo`

// import axios from "axios"
// export const API_SERVER_HOST = 'http://localhost:8481'
// const prefix = `${API_SERVER_HOST}/api/todo`

export const getList = async( pageParam: PageParam ) => {

    console.log('getList pageParam->',pageParam)

    const {page,size} = pageParam

    const res = await axios.get(`${prefix}/list`, {params: {page:page,size:size }})
  
    console.log('todo getList res.data->',res.data)

    return res.data

}

export const postAdd = async (todoObj:TodoAdd) => {
    console.log('postAdd todoObj->',todoObj)

    const res = await axios.post(`${prefix}/register` , todoObj)
    return res.data
}

//async 함수의 리턴은 무조건 Promise<Todo> 
export const getOne = async (tno: number | string ) => {
    console.log('getOne tno->',tno)
    const res = await axios.get(`${prefix}/${tno}` )
    return res.data
}

export const deleteOne = async (tno: number) => {
    console.log('deleteOne tno->',tno)
    const res = await axios.delete(`${prefix}/remove/${tno}` )
    return res.data
}

export const putOne = async (todo: TodoModify) => {
    console.log('putOne todo->',todo)
    const res = await axios.put(`${prefix}/modify/${todo.tno}`, todo)
    return res.data
}
  