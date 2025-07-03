import { lazy, Suspense } from "react";
import { createBrowserRouter } from "react-router-dom";
import todoRouter from "./todoRouter";
import productsRouter from "./productsRouter";
// const { createBrowserRouter } = "react-router-dom";

//로딩 페이지 생성
const Loading = <div>Loading...</div>
//페이지 선언
const Main = lazy(() => import("../pages/MainPage"));
const About = lazy(() => import("../pages/AboutPage"));
const TodoIndex = lazy(() => import("../pages/todo/IndexPage"));
const ProductsIndex = lazy(() => import("../pages/products/IndexPage"));

//createBrowserRouter : DispatcherServlet의 Controller 같은 친구
const root = createBrowserRouter([
    {
        //path : @mapping 같은 친구
        path:"",
        //element : return 같은 친구
        element:<Suspense fallback={Loading}><Main></Main></Suspense>
        //Suspense : 로딩창 구현 및 로딩 개선
        // - fallback : 로딩창 호출
        // - lazy : 로딩 개선
    },
    {
        path:"about",
        element:<Suspense fallback={Loading}><About></About></Suspense>
    },
    {
        path:"todo",
        element:<Suspense fallback={Loading}><TodoIndex></TodoIndex></Suspense>,
        //TodoIndex에서 리턴되는 BasicLayout의 내용(Outlet)으로 들어감
        //todoRouter 내부의 path는 'todo/'로 시작됨
        children:todoRouter()
    },
    {
        path:"products",
        element:<Suspense fallback={Loading}><ProductsIndex></ProductsIndex></Suspense>,
        children:productsRouter()
    },
]);

//외부 참조 허락
export default root;