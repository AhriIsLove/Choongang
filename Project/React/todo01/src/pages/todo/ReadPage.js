import { useParams } from "react-router-dom"
import ReadComponent from "../../components/todo/ReadComponent";

const ReadPage = () => {
    //useParams : URL에 입력된 파라미터 추출
    const { tno } = useParams();

    return (
        <div className="font-extrabold w-full bg-white mt-6">
            <div className="text-2xl">
                Todo ReadPage component {tno}
            </div>
            <ReadComponent tno={tno}></ReadComponent>
        </div>
    );
};

export default ReadPage;