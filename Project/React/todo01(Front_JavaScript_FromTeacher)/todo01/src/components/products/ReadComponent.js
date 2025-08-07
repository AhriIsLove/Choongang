import { useEffect, useState } from "react"
import useCustomMove from "../../hooks/useCustomMove"
import { API_SERVER_HOST } from "../../api/todoApi"
import { getOne } from "../../api/productsApi"

const initState = {
    pno:0,
    pname: '',
    pdesc: '',
    price: 0,
    uploadFileNames:[]
}

const host = API_SERVER_HOST

const ReadComponent = ({pno}) => {

    console.log('ReadComponent pno->', pno)

    const [product, setProduct] = useState(initState)

    //화면 이동용 함수
    const {moveToList,moveToModify} =useCustomMove()

    //fetching
    const [fetching, setFetching] = useState(false)

    useEffect(()=>{
        setFetching(true)
        getOne(pno).then(productDto=>{
            console.log('getOne(pno)->',productDto)
            setProduct(productDto)
            setFetching(false)
        })
    },[pno])

    return (
        <div className="border-2 border-sky-200 mt-10 m-2 p-4">
            {makediv('PNO'     , product.pno)}
            {makediv('PNAME'   , product.pname)}
            {makediv('PRICE'   , product.price)}
            {makediv('PDESC'   , product.pdesc)}
            <div className="w-full justify-center flex  flex-col m-auto items-center">
                {product.uploadFileNames.map( (imgFile, i) => 
                    <img 
                    alt ="product"
                    key={i}
                    className="p-4 w-1/2" 
                    src={`${host}/api/products/view/${imgFile}`}/>
                )}
            </div>

            {/* buttons.........start */}
            <div className="flex justify-end p-4">
                <button
                    type="button"
                    className="rounded p-4 m-2 text-xl w-32 text-white bg-blue-500 "
                    onClick={()=>moveToList()}
                >
                    Product List
                </button>
                <button
                    type="button"
                    className="rounded p-4 m-2 text-xl w-32 text-white bg-red-500"
                    onClick={() => moveToModify(pno)}
                >
                    Product Modify
                </button>

            </div>
        </div>

    )

}

const makediv =(title , value) => 
    <div className="flex justify-center" >
        <div className="relative mb-4 flex w-full flex-wrap items-stretch">
            <div className="w-1/5 p-6 text-right font-bold">{title}</div>
            <div className="w-4/5 p-6 rounded-r border border-solid shadow-md">{value}</div>
        </div>

    </div>

export default ReadComponent
