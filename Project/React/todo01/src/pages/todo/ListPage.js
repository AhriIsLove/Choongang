import ListComponent from "../../components/todo/ListComponent";

const ListPage = () => {
    return (
        <div className="p-4 w-full bg-yellow-100">
            <div className="text-3xl font-extrabold">
                Todo ListPage component
            </div>
            <ListComponent/>
        </div>
    );
};

export default ListPage;