import { lazy, Suspense } from "react";
import { Navigate } from "react-router-dom";

const Loading = <div>Loading....</div>
const ProductsList   =  lazy(() => import("../pages/products/ListPage"))
const ProductsAdd    =  lazy(() => import("../pages/products/AddPage"))
const ProductsRead   =  lazy(() => import("../pages/products/ReadPage"))
const ProductsModify =  lazy(() => import("../pages/products/ModifyPage"))
// Suspense : 비동기 작업이 끝날 때까지 기다려주는 역할
// Navigate : 마치 링크를 클릭한 것처럼 다른 페이지로 이동
//            어떤 조건이 만족되었을 때 특정 페이지로 강제로 보내버리고 싶을 때 
const productsRouter = () => {
    return [
        {
            path: "list",
            element: <Suspense fallback={Loading}><ProductsList/></Suspense>

        },
        {
            path: "",
            element: <Navigate replace to="list"></Navigate>
        },
        {
            path: "add",
            element: <Suspense fallback={Loading}><ProductsAdd/></Suspense>

        },
        {
            path: "read/:pno",
            element: <Suspense fallback={Loading}><ProductsRead/></Suspense>

        },
        {
            path: "modify/:pno",
            element: <Suspense fallback={Loading}><ProductsModify/></Suspense> 
        }


    ]

}

export default productsRouter;