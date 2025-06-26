import BasicMenu from "../components/BasicMenu";

const BasicLayout = ({ children }) => {
    return (
        <>
            {/* 기존 헤더 대신 BasicMenu */}
            <div>
                <BasicMenu></BasicMenu>
            </div>

            {/* 
            bg-white : background white
            my-5 : margin Y축 5로 설정
            w-full : width 여백없이
            
            그리고 이 요소 안에 있는 내용물들은 기본적으로는 세로로 나열하고 
            그 사이에 작은 간격을 두는데, 화면이 커지면(태블릿 이상) 가로로 나열하고 가로 간격
            flex: 이 클래스는 요소를 Flexbox 컨테이너로 만들어줘. Flexbox는 웹에서 요소들을 유연하게 배치
            flex-col: flex와 함께 쓰이는 건데, 자식 요소들을 세로 방향(column)으로 배치
            space-y-1: space-y는 flex-col처럼 세로로 배치된 요소들 사이에 간격

            md : medium(width:768) 사이즈 이상일 경우
            md:flex-row: 여기서 md:는 "medium" 사이즈 화면 이상일 때 적용
                        화면이 어느 정도 커지면, flex-col 대신 자식 요소들을 가로 방향(row)으로 배치
            
            
            
            2xl: 1536 이상
            xl : 1280 이상
            lg : large(width:1024) 사이즈 이상일 경우
            md : 768 이상
            sm : 640 이상
            그외 : 
            */}
            <div className="bg-white my-5 w-full flex flex-col space-y-1 
            md:flex-row md:space-x-1 md:space-y-0 xs">
                <main className="bg-sky-300 md:w-4/5 lg:w-3/4 px-5 py-5">
                    {children}
                </main>
                {/* 새로운 flex 부여 */}
                <aside className="bg-green-300 md:w-1/5 lg:w-1/4 px-5 py-5 flex">
                    <h1 className="text-2xl md:text-4xl">SideBar</h1>
                </aside>
                {/* <footer></footer> */}
            </div>
        </>
    );
};

export default BasicLayout;