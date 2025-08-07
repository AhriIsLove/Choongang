import { useRef, useState } from "react"
import useCustomMove from "../../hooks/useCustomMove"
import { productAdd } from "../../api/productsApi"
import FetchingModal from "../common/FetchingModal"
import ResultModal from "../common/ResultModal"

const initState = {
  pname:'',
  pdesc: '',
  price: 0,
  files: []
}

const AddComponent = () => {

  const [product, setProduct] = useState({...initState})    
  const uploadRef = useRef()
  const [fetching, setFetching] = useState(false)
  const [result, setResult] = useState(null) //결과 상태 

  const {moveToList} = useCustomMove() //useCustomMove 활용 

  const handleChangeProduct = (e) => {

    console.log('handleChangeProduct e.target.value->',e.target.value)
    console.log('handleChangeProduct e.target.name->',e.target.name)


    product[e.target.name] = e.target.value
    setProduct({...product})
  }

  const handleClickAdd = () => {
    const files = uploadRef.current.files
    const formData = new FormData()
    
    for(let i=0;i<files.length;i++){
        formData.append("files",files[i])
    }

    //other data
    formData.append("pname", product.pname)
    formData.append("pdesc", product.pdesc)
    formData.append("price", product.price)

    console.log("formData",formData)

    setFetching(true)


    productAdd(formData).then(data => {
       console.log('productAdd data->', data)

       setResult(data.result) //결과 데이터 변경 
       setFetching(false)
    }).catch(e => {
       console.error(e)
    })
  }

  const closeModal = () => {
    setResult(null)
    moveToList({page:1}) //moveToList( )호출 --> 모달 창이 닫히면 이동 

  }

  return (
    <div className="border-2 border-sky-200 mt-10 m-2 p-4">

        {fetching?<FetchingModal/>:<></>}

        {result ? <ResultModal title={'Product Add result'} content={`New ${result} Add 완료`} callbackFn={closeModal}/> :<></>  }

        <div className="flex justify-center">
            <div className="relative mb-4 flex w-full flex-wrap items-stretch">
                <div className="w-1/5 p-6 text-right font-bold">Product name</div>
                <input className="w-4/5 p-6 rounded-r border border-solid border-neutral-500 shadow-md"
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
                <textarea className="w-4/5 p-6 rounded-r border border-solid border-neutral-500 shadow-md"
                 name="pdesc" 
                 rows="4"
                 value={product.pdesc}
                 onChange={handleChangeProduct}
                 >
                </textarea>
            </div>
        </div>
        <div className="flex justify-center">
            <div className="relative mb-4 flex w-full flex-wrap items-stretch">
                <div className="w-1/5 p-6 text-right font-bold">Price</div>
                <input className="w-4/5 p-6 rounded-r border border-solid border-neutral-500 shadow-md"
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
                    <div className="w-1/5 p-6 text-right font-bold">Files</div>
                    <input ref={uploadRef} 
                        className="w-4/5 p-6 rounded-r border border-solid border-neutral-300 shadow-md" 
                        type={'file'} 
                        multiple={true}
                    >    
                    </input>
                </div>
        </div>

       <div className="flex justify-end">
            <div className="relative mb-4 flex w-full flex-wrap items-stretch">
                <button 
                        type="button"
                        className="rounded p-4 w-36 bg-blue-500 text-xl text-white"
                        onClick={handleClickAdd}
                >
                    Add Product
                </button>
            </div>
        </div>

    </div>

  )
}

export default AddComponent;