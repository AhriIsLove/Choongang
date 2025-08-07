
import axios from "axios";

import { API_SERVER_HOST } from "./todoApi";

const host = `${API_SERVER_HOST}/api/products`

export const productAdd = async (product) => {
  
    const header = {headers: {"Content-Type": "multipart/form-data"}}
    // 경로 뒤 '/' 주의 
    const res = await axios.post(`${host}/register`, product, header)

    console.log('product productAdd res.data->',res.data)

    return res.data    
}

export const getList = async ( pageParam ) => {

    const {page,size} = pageParam
  
    const res = await axios.get(`${host}/list`, {params: {page:page,size:size }})
    
     console.log('product getList res.data->',res.data)

    return res.data
  
}

export const getOne = async (pno) => {
  const res = await axios.get(`${host}/${pno}` )
  console.log('product getOne res.data->',res.data)

  return res.data

}

export const putOne = async (pno, product) => {
  const header = {headers: {"Content-Type": "multipart/form-data"}}
  console.log('putOne pno->',pno)
  console.log('putOne product->',product)

  const res = await axios.put(`${host}/modify/${pno}`, product, header)

  return res.data
}

export const deleteOne = async (pno) => {
  const res = await axios.delete(`${host}/remove/${pno}`)

  return res.data
}