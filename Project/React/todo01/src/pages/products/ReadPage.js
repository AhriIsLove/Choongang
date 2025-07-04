import { useParams } from "react-router-dom"
import ReadComponent from "../../components/products/ReadComponent";

const ReadPage = () => {
    const { pno } = useParams();

    return (
        <div className="font-extrabold w-full bg-white mt-6">
            <div className="text-2xl">
                Products ReadPage component {pno}
            </div>
            <ReadComponent pno={pno}/>
        </div>
    );
};

export default ReadPage;