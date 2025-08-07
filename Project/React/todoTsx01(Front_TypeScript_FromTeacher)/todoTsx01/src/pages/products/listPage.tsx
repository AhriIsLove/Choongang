import axios from "axios";
import { createSearchParams, type LoaderFunctionArgs, useLoaderData } from "react-router";
import ListComponent from "../../components/products/listComponent";
import { API_SERVER_HOST } from "../../api/todoApi";


export async function loadProducts({request}: LoaderFunctionArgs) {
 
    const url = new URL(request.url);
    const page = url.searchParams.get('page') || "1"; 
    const size = url.searchParams.get('size') || "10";
    const queryStr = createSearchParams({page,size}).toString()
    const res = await axios.get(`${API_SERVER_HOST}/api/products/list?${queryStr}`)
    // Check res  
    console.log('ListPage fetch response->', res);

  return res.data
}

  
const ListPage = () => {

  const pageResponse = useLoaderData()

  return ( 
  <div className="w-full mt-4 border border-solid border-neutral-300 shadow-md">
    <div className="text-2xl m-4 font-extrabold">
        Products List Page
    </div>
    <ListComponent serverData={pageResponse}></ListComponent>
  </div>
  );
}
  
  export default ListPage;