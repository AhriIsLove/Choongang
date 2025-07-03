import { useParams } from "react-router-dom"

const ReadPage = () => {
    const { pno } = useParams();

    return (
        <div className="font-extrabold w-full bg-white mt-6">
            <div className="text-2xl">
                Products ReadPage component {pno}
            </div>
        </div>
    );
};

export default ReadPage;