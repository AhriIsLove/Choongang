import { useEffect, useRef, useState } from "react"
import { API_SERVER_HOST } from "../../api/todoApi"
import useCustomMove from "../../hooks/useCustomMove"
import { deleteOne, getOne, putOne } from "../../api/productsApi"
import ResultModal from "../common/ResultModal"
import FetchingModal from "../common/FetchingModal"


const initState = {
    pno:0,
    pname: '',
    pdesc: '',
    price: 0,
    delFlag:false,
    uploadFileNames:[]
}

const host = API_SERVER_HOST

const ModifyComponent = ({pno}) => {

    console.log('ModifyComponent pno->', pno) 

    const [product, setProduct] = useState({...initState})
    //모달 창을 위한 상태 
    const [result, setResult] = useState(null)
    //이동을 위한 기능들 
    const {moveToList, moveToRead} = useCustomMove()
    const [fetching, setFetching] = useState(false)

    const  uploadRef = useRef()

    useEffect(() => {

        setFetching(true)

        getOne(pno).then(data => {
            console.log('getOne data->', data)
            setProduct(data)
            setFetching(false)
        } )

    },[pno])

    const handleChangeProduct = (e) => {
        product[e.target.name] = e.target.value
        setProduct({...product})
    }

    const deleteOldImages = (imageName) => {
        const resultFileNames = product.uploadFileNames.filter( fileName => fileName !== imageName)
        product.uploadFileNames = resultFileNames
        setProduct({...product})
    }


    const handleClickModify = () => { //버튼 클릭시 
        const files = uploadRef.current.files
        // 파일을 http 통신으로 전송 하려면 formData 객체를 이용
        // formData 객체는 key, value 형식으로 되어 있는 객체
        // FormData 객체는 데이터를 multipart/form-data 형식으로 전송
        // formData.append('key', value);
        const formData = new FormData()

        for (let i = 0; i < files.length; i++) {
            formData.append("files", files[i]);
        }
        //other data
        formData.append("pname", product.pname)
        formData.append("pdesc", product.pdesc)
        formData.append("price", product.price)
        formData.append("delFlag", product.delFlag)

        for( let i = 0; i < product.uploadFileNames.length ; i++){
            formData.append("uploadFileNames", product.uploadFileNames[i])
        }    
        //fetching
        setFetching(true)

        //Product 수정 처리
        putOne(pno,formData).then(data => {
            console.log("Product modify result: " + data)
            setResult('Modified')
        })
    }

    const handleClickDelete = () => { //버튼 클릭시 
        setFetching(true)

        deleteOne(pno).then( data => {
        console.log("Product delete result: " + data)
        setResult('Deleted')
        setFetching(false)
        })

    }

    //모달 창이 close될때 
    const closeModal = () => {
        if(result ==='Modified') {
            moveToRead(pno)
        }else if(result === 'Deleted') {
            moveToList({page:1})
        }
        setResult(null)
    }

 
    return ( 
    <div className = "border-2 border-sky-200 mt-10 m-2 p-4"> 
        {fetching? <FetchingModal/> :<></>}

        {result? 
            <ResultModal
            title={`${result}`}
            content={'정상적으로 처리되었습니다.'}  //결과 모달창 
            callbackFn={closeModal}
            />    
            :
            <></>
        }


        <div className="flex justify-center">
            <div className="relative mb-4 flex w-full flex-wrap items-stretch">
            <div className="w-1/5 p-6 text-right font-bold">Product Name</div>
            <input className="w-4/5 p-6 rounded-r border border-solid border-neutral-300 shadow-md" 
                name="pname"
                type={'text'} 
                value={product.pname}
                onChange={handleChangeProduct}
                >
                </input>

            </div>
        </div>
        <div className="flex justify-center">
            <div className="relative mb-4 flex w-full flex-wrap items-stretch">
            <div className="w-1/5 p-6 text-right font-bold">Desc</div>
                <textarea 
                className="w-4/5 p-6 rounded-r border border-solid border-neutral-300 shadow-md resize-y"
                name="pdesc"
                rows="4"
                onChange={handleChangeProduct}
                value={product.pdesc}>
                {product.pdesc}
                </textarea>
            </div>  
        </div>
        <div className="flex justify-center">
            <div className="relative mb-4 flex w-full flex-wrap items-stretch">
                <div className="w-1/5 p-6 text-right font-bold">Price</div>
                <input className="w-4/5 p-6 rounded-r border border-solid border-neutral-300 shadow-md" 
                    name="price"
                    type={'number'} 
                    value={product.price}
                    onChange={handleChangeProduct}
                    >
                    </input>
            </div>
        </div>

        <div className="flex justify-center">
            <div className="relative mb-4 flex w-full flex-wrap items-stretch">
                <div className="w-1/5 p-6 text-right font-bold">DELETE</div>
                <select 
                name="delFlag" value={product.delFlag}
                onChange={handleChangeProduct}
                className="w-4/5 p-6 rounded-r border border-solid border-neutral-300 shadow-md">
                <option value={false}>사용</option>
                <option value={true}>삭제</option>
                </select>
            </div>
        </div>

        <div className="flex justify-center">
            <div className="relative mb-4 flex w-full flex-wrap items-stretch">
                <div className="w-1/5 p-6 text-right font-bold">Files</div>
                <input ref={uploadRef} 
                    className="w-4/5 p-6 rounded-r border border-solid border-neutral-300 shadow-md" 
                    type={'file'} multiple={true}
                >    
                </input>
            </div>
        </div>
        <div className="flex justify-center">
            <div className="relative mb-4 flex w-full flex-wrap items-stretch">
                <div className="w-1/5 p-6 text-right font-bold">
                    Images          
                </div>
                <div className="w-4/5 justify-center flex flex-wrap items-start">
                    {product.uploadFileNames.map( (imgFile, i) => 
                        <div
                            className="flex justify-center flex-col w-1/3" 
                            key = {i}>
                            <button className="bg-blue-500 text-3xl text-white"
                            onClick={() => deleteOldImages(imgFile)}
                            >DELETE</button>
                            <img 
                            alt ="img" 
                            src={`${host}/api/products/view/s_${imgFile}`}/>
                            
                        </div>
                    )}
                </div>
            </div>
        </div>

        <div className="flex justify-end p-4">
            <button type="button" 
            className="rounded p-4 m-2 text-xl w-32 text-white bg-red-500"
            onClick={handleClickDelete}
            >
            Delete
            </button>

            <button type="button" 
            className="inline-block rounded p-4 m-2 text-xl w-32  text-white bg-orange-500"
            onClick={handleClickModify}
            >
            Modify
            </button>
            
            <button type="button" 
            className="rounded p-4 m-2 text-xl w-32 text-white bg-blue-500"
            onClick={moveToList}
            >
            List
            </button>

        </div>

    </div>
    );
}

export default ModifyComponent;
