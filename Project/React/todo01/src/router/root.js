import { lazy, Suspense } from "react";
import { createBrowserRouter } from "react-router-dom";
// const { createBrowserRouter } = "react-router-dom";

//로딩 페이지 생성
const Loading = <div>Loading...</div>
//메인 페이지 가져오기
const Main = lazy(() => import("../pages/MainPage"));
const About = lazy(() => import("../pages/AboutPage"));

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
        element:<Suspense><About></About></Suspense>
    }
])

//외부 참조 허락
export default root;