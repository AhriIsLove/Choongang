import { lazy, Suspense } from "react"
import { Navigate } from "react-router-dom";

const Loading = <div>Loading...</div>
const TodoList = lazy(() => import("../pages/todo/ListPage"));
const TodoAdd = lazy(() => import("../pages/todo/AddPage"));
const TodoRead = lazy(() => import("../pages/todo/ReadPage"));

const todoRouter = () => {
    return [
        {
            // Suspense : 비동기 작업이 끝날 때까지 기다려주는 역할
            path:"list",
            element:<Suspense fallback={Loading}><TodoList/></Suspense>
        },
        {
            // Navigate : 마치 링크를 클릭한 것처럼 다른 페이지로 이동
            //            어떤 조건이 만족되었을 때 특정 페이지로 강제로 보내버리고 싶을 때
            path:"",
            element:<Navigate replace to="list"></Navigate>
        },
        {
            path:"add",
            element:<Suspense fallback={Loading}><TodoAdd/></Suspense>
        },
        {
            path:"read/:tno",
            element:<Suspense fallback={Loading}><TodoRead/></Suspense>
        }
    ];
};

//한번만 가능
//todoRouter.js를 export하는것이 아닌
//todoRouter 변수/함수를 export하는것
export default todoRouter;